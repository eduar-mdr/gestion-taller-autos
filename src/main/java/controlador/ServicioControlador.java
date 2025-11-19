/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Eduar Medrano
 */
import modelo.Servicio;
import servicio.ServicioServicio;
import dao.ServicioDao;
import dao.VehiculoDao;
import modelo.Vehiculo;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/servicios")
public class ServicioControlador extends HttpServlet {

    private ServicioServicio servicioServicio = new ServicioServicio();

    //Index
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Tomamos el parámetro "action" de la URL
        String action = request.getParameter("action");
        System.out.println("action:" + action);
        if (action == null) {
            action = "index"; // Acción por defecto
        }

        switch (action) {

            case "crear":
                VehiculoDao vehiculoDao = new VehiculoDao();
                List<Vehiculo> vehiculos = vehiculoDao.listar();
                request.setAttribute("vehiculos", vehiculos);
                // Redirige al JSP de creación
                RequestDispatcher form_crear = request.getRequestDispatcher("/vistas/servicios/crear.jsp");
                form_crear.forward(request, response);
                break;
            case "reporte":
                listarReporte(request, response);
                break;
            case "pdf":
                generarPdf(response);
                break;
            case "verPorCliente":
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));

                ServicioDao servicioDao = new ServicioDao();
                List<Servicio> servicios = servicioDao.listarPorCliente(idCliente);

                request.setAttribute("servicios", servicios);
                request.getRequestDispatcher("/vistas/servicios/servicioCliente.jsp")
                        .forward(request, response);
                break;

            //Por defecto redirije al index
            default:
                System.out.println("In the default case");
                try {
                    List<Servicio> lista = servicioServicio.obtenerServicios();
                    request.setAttribute("servicios", lista);
                    RequestDispatcher index = request.getRequestDispatcher("vistas/servicios/index.jsp");
                    index.forward(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Error al listar servicios", e);
                }
                break;
        }

    }

    //Store, show, update & delete
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("DoPost requested: " + action);

        Servicio m = new Servicio();
        switch (action) {
            case "editar":

                int id = Integer.parseInt(request.getParameter("idServicio"));
                Servicio servicio = servicioServicio.obtenerPorId(id);
                request.setAttribute("servicio", servicio);
                RequestDispatcher formEditar = request.getRequestDispatcher("/vistas/servicios/actualizar.jsp");
                formEditar.forward(request, response);
                break;

            //Store
            case "guardar":

                m.setNombre(request.getParameter("nombre"));
                m.setDescripcion(request.getParameter("descripcion"));
                m.setPrecio(Double.parseDouble(request.getParameter("precio")));
                m.setCategoria(request.getParameter("categoria"));
                m.setDuracionEstimada(request.getParameter("duracionEstimada"));
                m.setEstado(request.getParameter("estado"));
                m.setIdVehiculo(Integer.parseInt(request.getParameter("idVehiculo")));


                try {
                    servicioServicio.registrarServicio(m);
                    //Redirijo a la url principal
                    response.sendRedirect(request.getContextPath() + "/servicios");
                } catch (SQLException e) {
                    throw new ServletException("Error al registrar servicio", e);
                }
                break;

            //Update
            case "actualizar":

                m.setIdServicio(Integer.parseInt(request.getParameter("idServicio")));
                m.setNombre(request.getParameter("nombre"));
                m.setDescripcion(request.getParameter("descripcion"));
                m.setPrecio(Double.parseDouble(request.getParameter("precio")));
                m.setCategoria(request.getParameter("categoria"));
                m.setDuracionEstimada(request.getParameter("duracionEstimada"));
                m.setEstado(request.getParameter("estado"));

                servicioServicio.actualizar(m);
                response.sendRedirect(request.getContextPath() + "/servicios");

                break;

            //Delete   
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("idServicio"));
                servicioServicio.eliminar(idEliminar);
                response.sendRedirect(request.getContextPath() + "/servicios");
                break;
        }

    }

    private void listarReporte(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Servicio> lista = servicioServicio.obtenerServicios();
            request.setAttribute("listaServicios", lista);

            RequestDispatcher rd = request.getRequestDispatcher("/vistas/servicios/reporte.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al generar el reporte de servicios", e);
        }
    }

    private void generarPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=servicios.pdf");

        com.lowagie.text.Document documento = new com.lowagie.text.Document();

        try {
            com.lowagie.text.pdf.PdfWriter.getInstance(documento, response.getOutputStream());
            documento.open();

            // Fuente del título
            com.lowagie.text.Font fuenteTitulo = new com.lowagie.text.Font(
                    com.lowagie.text.Font.HELVETICA, 18, com.lowagie.text.Font.BOLD
            );
            com.lowagie.text.Font fuenteTabla = new com.lowagie.text.Font(
                    com.lowagie.text.Font.COURIER, 12
            );

            documento.add(new com.lowagie.text.Paragraph("Listado de Servicios", fuenteTitulo));
            documento.add(new com.lowagie.text.Paragraph(" "));

            com.lowagie.text.pdf.PdfPTable tabla = new com.lowagie.text.pdf.PdfPTable(7);
            tabla.setWidthPercentage(100);

            // Definir anchos relativos de las columnas
            float[] anchos = {0.5f, 2.5f, 4f, 1.5f, 2.5f, 2.5f, 1.5f};
            tabla.setWidths(anchos);

            // Cabecera
            tabla.addCell(new com.lowagie.text.Phrase("ID", fuenteTabla));
            tabla.addCell(new com.lowagie.text.Phrase("Servicio", fuenteTabla));
            tabla.addCell(new com.lowagie.text.Phrase("Descripción", fuenteTabla));
            tabla.addCell(new com.lowagie.text.Phrase("Precio", fuenteTabla));
            tabla.addCell(new com.lowagie.text.Phrase("Categoría", fuenteTabla));
            tabla.addCell(new com.lowagie.text.Phrase("Duración Estimada", fuenteTabla));
            tabla.addCell(new com.lowagie.text.Phrase("Estado", fuenteTabla));

            List<Servicio> lista = servicioServicio.obtenerServicios();
            for (Servicio s : lista) {
                tabla.addCell(new com.lowagie.text.Phrase(String.valueOf(s.getIdServicio()), fuenteTabla));
                tabla.addCell(new com.lowagie.text.Phrase(s.getNombre(), fuenteTabla));
                tabla.addCell(new com.lowagie.text.Phrase(s.getDescripcion(), fuenteTabla));
                tabla.addCell(new com.lowagie.text.Phrase("$ " + s.getPrecio(), fuenteTabla));
                tabla.addCell(new com.lowagie.text.Phrase(s.getCategoria(), fuenteTabla));
                tabla.addCell(new com.lowagie.text.Phrase(s.getDuracionEstimada(), fuenteTabla));
                tabla.addCell(new com.lowagie.text.Phrase(s.getEstado(), fuenteTabla));
            }

            documento.add(tabla);
            documento.close();

        } catch (com.lowagie.text.DocumentException | SQLException e) {
            e.printStackTrace();
        }

    }
}
