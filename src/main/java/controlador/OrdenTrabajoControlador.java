/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author fuent
 */

import dao.OrdenTrabajoDao;
import modelo.OrdenTrabajo;
import modelo.DetalleServicio;
import modelo.DetalleRepuesto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/orden-trabajo")
public class OrdenTrabajoControlador extends HttpServlet {
    private OrdenTrabajoDao ordenDAO;
    
    @Override
    public void init() throws ServletException {
        ordenDAO = new OrdenTrabajoDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            switch (action != null ? action : "listar") {
                case "listar":
                    listarOrdenes(request, response);
                    break;
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "ver":
                    verDetalleOrden(request, response);
                    break;
                case "agregarServicio":
                    mostrarFormularioServicio(request, response);
                    break;
                case "agregarRepuesto":
                    mostrarFormularioRepuesto(request, response);
                    break;
                default:
                    listarOrdenes(request, response);
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
            switch (action != null ? action : "") {
                case "crear":
                    crearOrden(request, response);
                    break;
                case "actualizar":
                    actualizarOrden(request, response);
                    break;
                case "registrarServicio":
                    registrarServicio(request, response);
                    break;
                case "registrarRepuesto":
                    registrarRepuesto(request, response);
                    break;
                case "eliminarServicio":
                    eliminarServicio(request, response);
                    break;
                case "eliminarRepuesto":
                    eliminarRepuesto(request, response);
                    break;
                default:
                    listarOrdenes(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos: " + e.getMessage(), e);
        }
    }
    
    private void listarOrdenes(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        List<OrdenTrabajo> listaOrdenes = ordenDAO.listarOrdenes();
        request.setAttribute("listaOrdenes", listaOrdenes);
        response.sendRedirect(request.getContextPath() + "/orden-trabajo?action=listar");

    }
    
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.sendRedirect(request.getContextPath() + "/orden-trabajo?action=listar");


    }
    
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        int idOrden = Integer.parseInt(request.getParameter("id"));
        OrdenTrabajo orden = ordenDAO.obtenerOrdenPorId(idOrden);
        
        if (orden != null) {
            request.setAttribute("orden", orden);
            request.getRequestDispatcher("/vistas/orden-trabajo/listar.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "No se encontró la orden de trabajo.");
            listarOrdenes(request, response);
        }
    }
    
    private void verDetalleOrden(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        int idOrden = Integer.parseInt(request.getParameter("id"));
        OrdenTrabajo orden = ordenDAO.obtenerOrdenPorId(idOrden);
        
        if (orden != null) {
            List<DetalleServicio> servicios = ordenDAO.listarServiciosOrden(idOrden);
            List<DetalleRepuesto> repuestos = ordenDAO.listarRepuestosOrden(idOrden);
            
            request.setAttribute("orden", orden);
            request.setAttribute("servicios", servicios);
            request.setAttribute("repuestos", repuestos);
            request.getRequestDispatcher("/vistas/orden-trabajo/detalle.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "No se encontró la orden de trabajo.");
            listarOrdenes(request, response);
        }
    }
    
    private void crearOrden(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        try {
            int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            String idEmpleadoParam = request.getParameter("idEmpleado");
            String diagnostico = request.getParameter("diagnostico");
            String observaciones = request.getParameter("observaciones");
            String tiempoEstimadoParam = request.getParameter("tiempoEstimado");
            String fechaEntregaParam = request.getParameter("fechaEntrega");
            String documentosUrl = request.getParameter("documentosUrl");
            
            OrdenTrabajo orden = new OrdenTrabajo();
            orden.setIdVehiculo(idVehiculo);
            orden.setIdCliente(idCliente);
            
            if (idEmpleadoParam != null && !idEmpleadoParam.isEmpty()) {
                orden.setIdEmpleado(Integer.parseInt(idEmpleadoParam));
            }
            
            orden.setDiagnostico(diagnostico);
            orden.setObservaciones(observaciones);
            
            if (tiempoEstimadoParam != null && !tiempoEstimadoParam.isEmpty()) {
                orden.setTiempoEstimado(new BigDecimal(tiempoEstimadoParam));
            }
            
            if (fechaEntregaParam != null && !fechaEntregaParam.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                orden.setFechaEntrega(new Timestamp(sdf.parse(fechaEntregaParam).getTime()));
            }
            
            orden.setDocumentosUrl(documentosUrl);
            orden.setEstado("Pendiente");
            
            boolean creado = ordenDAO.crearOrden(orden);
            
            if (creado) {
                request.getSession().setAttribute("mensaje", 
                    "Orden de trabajo #" + orden.getIdOrden() + " creada exitosamente.");
                response.sendRedirect("orden-trabajo?action=ver&id=" + orden.getIdOrden());
            } else {
                request.setAttribute("error", "No se pudo crear la orden de trabajo.");
                request.getRequestDispatcher("/vistas/orden-trabajo/nuevo.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al crear la orden: " + e.getMessage());
            request.getRequestDispatcher("/vistas/orden-trabajo/nuevo.jsp").forward(request, response);
        }
    }
    
    private void actualizarOrden(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        try {
            int idOrden = Integer.parseInt(request.getParameter("idOrden"));
            String idEmpleadoParam = request.getParameter("idEmpleado");
            String estado = request.getParameter("estado");
            String diagnostico = request.getParameter("diagnostico");
            String observaciones = request.getParameter("observaciones");
            String tiempoEstimadoParam = request.getParameter("tiempoEstimado");
            String fechaEntregaParam = request.getParameter("fechaEntrega");
            String documentosUrl = request.getParameter("documentosUrl");
            
            OrdenTrabajo orden = ordenDAO.obtenerOrdenPorId(idOrden);
            
            if (orden != null) {
                if (idEmpleadoParam != null && !idEmpleadoParam.isEmpty()) {
                    orden.setIdEmpleado(Integer.parseInt(idEmpleadoParam));
                }
                
                orden.setEstado(estado);
                orden.setDiagnostico(diagnostico);
                orden.setObservaciones(observaciones);
                
                if (tiempoEstimadoParam != null && !tiempoEstimadoParam.isEmpty()) {
                    orden.setTiempoEstimado(new BigDecimal(tiempoEstimadoParam));
                }
                
                if (fechaEntregaParam != null && !fechaEntregaParam.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    orden.setFechaEntrega(new Timestamp(sdf.parse(fechaEntregaParam).getTime()));
                }
                
                orden.setDocumentosUrl(documentosUrl);
                
                boolean actualizado = ordenDAO.actualizarOrden(orden);
                
                if (actualizado) {
                    request.getSession().setAttribute("mensaje", "Orden actualizada exitosamente.");
                    response.sendRedirect("orden-trabajo?action=ver&id=" + idOrden);
                } else {
                    request.setAttribute("error", "No se pudo actualizar la orden.");
                    mostrarFormularioEditar(request, response);
                }
            }
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al actualizar: " + e.getMessage());
            mostrarFormularioEditar(request, response);
        }
    }
    
    private void mostrarFormularioServicio(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        int idOrden = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("idOrden", idOrden);
        request.getRequestDispatcher("/vistas/orden-trabajo/agregar-servicio.jsp").forward(request, response);
    }
    
    private void mostrarFormularioRepuesto(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        int idOrden = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("idOrden", idOrden);
        request.getRequestDispatcher("/vistas/orden-trabajo/agregar-repuesto.jsp").forward(request, response);
    }
    
    private void registrarServicio(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        
        int idOrden = Integer.parseInt(request.getParameter("idOrden"));
        int idServicio = Integer.parseInt(request.getParameter("idServicio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        BigDecimal subtotal = new BigDecimal(request.getParameter("subtotal"));
        
        DetalleServicio detalle = new DetalleServicio(idOrden, idServicio, cantidad, subtotal);
        
        boolean agregado = ordenDAO.agregarServicio(detalle);
        
        if (agregado) {
            request.getSession().setAttribute("mensaje", "Servicio agregado exitosamente.");
        } else {
            request.getSession().setAttribute("error", "No se pudo agregar el servicio.");
        }
        
        response.sendRedirect("orden-trabajo?action=ver&id=" + idOrden);
    }
    
    private void registrarRepuesto(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        
        int idOrden = Integer.parseInt(request.getParameter("idOrden"));
        int idRepuesto = Integer.parseInt(request.getParameter("idRepuesto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        BigDecimal subtotal = new BigDecimal(request.getParameter("subtotal"));
        
        DetalleRepuesto detalle = new DetalleRepuesto(idOrden, idRepuesto, cantidad, subtotal);
        
        boolean agregado = ordenDAO.agregarRepuesto(detalle);
        
        if (agregado) {
            request.getSession().setAttribute("mensaje", "Repuesto agregado exitosamente.");
        } else {
            request.getSession().setAttribute("error", "No se pudo agregar el repuesto.");
        }
        
        response.sendRedirect("orden-trabajo?action=ver&id=" + idOrden);
    }
    
    private void eliminarServicio(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        
        int idDetalleServicio = Integer.parseInt(request.getParameter("idDetalle"));
        int idOrden = Integer.parseInt(request.getParameter("idOrden"));
        
        boolean eliminado = ordenDAO.eliminarServicio(idDetalleServicio, idOrden);
        
        if (eliminado) {
            request.getSession().setAttribute("mensaje", "Servicio eliminado.");
        }
        
        response.sendRedirect("orden-trabajo?action=ver&id=" + idOrden);
    }
    
    private void eliminarRepuesto(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        
        int idDetalleRepuesto = Integer.parseInt(request.getParameter("idDetalle"));
        int idOrden = Integer.parseInt(request.getParameter("idOrden"));
        
        boolean eliminado = ordenDAO.eliminarRepuesto(idDetalleRepuesto, idOrden);
        
        if (eliminado) {
            request.getSession().setAttribute("mensaje", "Repuesto eliminado.");
        }
        
        response.sendRedirect("orden-trabajo?action=ver&id=" + idOrden);
    }
}