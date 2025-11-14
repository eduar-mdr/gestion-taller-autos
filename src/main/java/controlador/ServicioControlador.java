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
public class ServicioControlador extends HttpServlet{
    
    private ServicioServicio servicioServicio = new ServicioServicio();
    
    //Index
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         // Tomamos el parámetro "action" de la URL
        String action = request.getParameter("action");
        System.out.println("action:"+action);
        if (action == null) {
            action = "index"; // Acción por defecto
        }
        
        switch (action) {
            
            case "crear":
                // Redirige al JSP de creación
                RequestDispatcher form_crear = request.getRequestDispatcher("/vistas/servicios/crear.jsp");
                form_crear.forward(request, response);
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
        System.out.println("DoPost requested: "+action);

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
                m.setDuracionEstimada(Double.parseDouble(request.getParameter("duracionEstimada")));
                m.setEstado(request.getParameter("estado"));

                try {
                    servicioServicio.registrarServicio(m);
                    //Redirijo a la url principal
                    response.sendRedirect(request.getContextPath()+"/servicios");
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
                m.setDuracionEstimada(Double.parseDouble(request.getParameter("duracionEstimada")));
                m.setEstado(request.getParameter("estado"));

                servicioServicio.actualizar(m);
                response.sendRedirect(request.getContextPath()+"/servicios");

                break;  
                
            //Delete   
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("idServicio"));
                servicioServicio.eliminar(idEliminar);
                response.sendRedirect(request.getContextPath()+"/servicios");
                break; 
        }
    }
   
}
