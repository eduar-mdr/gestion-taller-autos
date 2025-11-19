/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author fuent
 */

import dao.PagoDao;
import modelo.Pago;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/pagos")
public class PagoControlador extends HttpServlet {
    private PagoDao pagoDao;
    
    @Override
    public void init() throws ServletException {
        pagoDao = new PagoDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            switch (action != null ? action : "listar") {
                case "listar":
                    listarPagos(request, response);
                    break;
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "verFactura":
                    verFactura(request, response);
                    break;
                case "calcularTotales":
                    calcularTotalesOrden(request, response);
                    break;
                default:
                    listarPagos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos: " + e.getMessage(), e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            if ("registrar".equals(action)) {
                registrarPago(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos: " + e.getMessage(), e);
        }
    }
    
    private void listarPagos(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        List<Pago> listaPagos = pagoDao.listarPagos();
        request.setAttribute("listaPagos", listaPagos);
        request.getRequestDispatcher("vistas/pagos/listar.jsp").forward(request, response);
    }
    
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        String idOrdenParam = request.getParameter("idOrden");
        
        if (idOrdenParam != null && !idOrdenParam.isEmpty()) {
            int idOrden = Integer.parseInt(idOrdenParam);
            Pago infoOrden = pagoDao.obtenerInfoOrdenParaPago(idOrden);
            
            if (infoOrden != null) {
                request.setAttribute("infoOrden", infoOrden);
            } else {
                request.setAttribute("error", "No se encontró la orden de trabajo especificada.");
            }
        }
        
        request.getRequestDispatcher("vistas/pagos/nuevo.jsp").forward(request, response);
    }
    
    private void calcularTotalesOrden(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        
        String idOrdenParam = request.getParameter("idOrden");
        
        if (idOrdenParam != null && !idOrdenParam.isEmpty()) {
            int idOrden = Integer.parseInt(idOrdenParam);
            Pago infoOrden = pagoDao.obtenerInfoOrdenParaPago(idOrden);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            if (infoOrden != null) {
                String json = String.format(
                    "{\"success\": true, \"totalRepuestos\": %.2f, \"totalManoObra\": %.2f, " +
                    "\"totalGeneral\": %.2f, \"montoPendiente\": %.2f, \"nombreCliente\": \"%s\", " +
                    "\"placaVehiculo\": \"%s\"}",
                    infoOrden.getTotalRepuestos(),
                    infoOrden.getTotalManoObra(),
                    infoOrden.getTotalGeneral(),
                    infoOrden.getMontoPendiente(),
                    infoOrden.getNombreCliente(),
                    infoOrden.getPlacaVehiculo()
                );
                response.getWriter().write(json);
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Orden no encontrada\"}");
            }
        } else {
            response.getWriter().write("{\"success\": false, \"message\": \"ID de orden no válido\"}");
        }
    }
    
    private void registrarPago(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        try {
            int idOrden = Integer.parseInt(request.getParameter("idOrden"));
            String metodoPago = request.getParameter("metodoPago");
            BigDecimal monto = new BigDecimal(request.getParameter("monto"));
            BigDecimal descuento = request.getParameter("descuento") != null && 
                                   !request.getParameter("descuento").isEmpty() 
                                   ? new BigDecimal(request.getParameter("descuento")) 
                                   : BigDecimal.ZERO;
            String tipoPago = request.getParameter("tipoPago");
            String observaciones = request.getParameter("observaciones");
            
            // Validar que el monto sea positivo
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                request.setAttribute("error", "El monto debe ser mayor a cero.");
                request.getRequestDispatcher("pagos/nuevo.jsp").forward(request, response);
                return;
            }
            
            // Validar que el descuento no sea mayor al monto
            if (descuento.compareTo(monto) > 0) {
                request.setAttribute("error", "El descuento no puede ser mayor al monto.");
                request.getRequestDispatcher("pagos/nuevo.jsp").forward(request, response);
                return;
            }
            
            // Obtener información de la orden
            Pago infoOrden = pagoDao.obtenerInfoOrdenParaPago(idOrden);
            
            if (infoOrden == null) {
                request.setAttribute("error", "No se encontró la orden de trabajo.");
                request.getRequestDispatcher("pagos/nuevo.jsp").forward(request, response);
                return;
            }
            
            // Validar que no se exceda el monto pendiente
            BigDecimal montoFinal = monto.subtract(descuento);
            if (montoFinal.compareTo(infoOrden.getMontoPendiente()) > 0) {
                request.setAttribute("error", "El monto a pagar excede el monto pendiente.");
                request.setAttribute("infoOrden", infoOrden);
                request.getRequestDispatcher("vistas/pagos/nuevo.jsp").forward(request, response);
                return;
            }
            
            // Crear objeto Pago
            Pago pago = new Pago();
            pago.setIdOrden(idOrden);
            pago.setMetodoPago(metodoPago);
            pago.setMonto(monto);
            pago.setDescuento(descuento);
            pago.setTipoPago(tipoPago);
            pago.setObservaciones(observaciones);
            
            // Determinar estado del pago
            BigDecimal montoRestante = infoOrden.getMontoPendiente().subtract(montoFinal);
            if (montoRestante.compareTo(BigDecimal.ZERO) <= 0) {
                pago.setEstado("Pagado");
            } else if ("Parcial".equals(tipoPago)) {
                pago.setEstado("En Crédito");
            } else {
                pago.setEstado("Pagado");
            }
            
            // Registrar el pago
            boolean registrado = pagoDao.registrarPago(pago);
            
            if (registrado) {
                request.getSession().setAttribute("mensaje", 
                    "Pago registrado exitosamente. Número de factura: " + pago.getNumeroFactura());
                response.sendRedirect("pagos?action=verFactura&idPago=" + pago.getIdPago());
            } else {
                request.setAttribute("error", "No se pudo registrar el pago.");
                request.setAttribute("infoOrden", infoOrden);
                request.getRequestDispatcher("pagos/nuevo.jsp").forward(request, response);
            }
            
        } catch (NumberFormatException e) {
                            request.setAttribute("error", "Datos numéricos inválidos: " + e.getMessage());
            request.getRequestDispatcher("vistas/pagos/nuevo.jsp").forward(request, response);
        }
    }
    
    private void verFactura(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        String idPagoParam = request.getParameter("idPago");
        
        if (idPagoParam != null && !idPagoParam.isEmpty()) {
            int idPago = Integer.parseInt(idPagoParam);
            Pago pago = pagoDao.obtenerPagoPorId(idPago);
            
            if (pago != null) {
                // Obtener información completa de la orden
                Pago infoOrden = pagoDao.obtenerInfoOrdenParaPago(pago.getIdOrden());
                
                // Obtener historial de pagos de esta orden
                List<Pago> historialPagos = pagoDao.listarPagosPorOrden(pago.getIdOrden());
                
                request.setAttribute("pago", pago);
                request.setAttribute("infoOrden", infoOrden);
                request.setAttribute("historialPagos", historialPagos);
                request.getRequestDispatcher("vistas/facturas/facturas.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No se encontró el pago especificado.");
                listarPagos(request, response);
            }
        } else {
            response.sendRedirect("pagos?action=listar");
        }
    }
}