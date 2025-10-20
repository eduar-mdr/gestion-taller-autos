/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Eduar Medrano
 */
import modelo.Cliente;
import servicio.ClienteServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
@WebServlet("/clientes")
public class ClienteControlador extends HttpServlet{
    
    private ClienteServicio clienteServicio = new ClienteServicio();

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
                RequestDispatcher form_crear = request.getRequestDispatcher("/vistas/clientes/crear.jsp");
                form_crear.forward(request, response);
                break;

            //Por defecto redirije al index
            default:
                System.out.println("In the default case");
                try {
                    List<Cliente> lista = clienteServicio.obtenerClientes();
                    request.setAttribute("clientes", lista);
                    RequestDispatcher index = request.getRequestDispatcher("vistas/clientes/index.jsp");
                    index.forward(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Error al listar clientes", e);
                }
                break;
        }
        
        
    }
    
    //Store
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        System.out.println("DoPost requested: "+action);

        Cliente c = new Cliente(); //Instancia del modelo
        switch (action) {
            
            //Acciones en post para ocultar los datos
            
            //Show
            case "editar":
                
                int id = Integer.parseInt(request.getParameter("idCliente"));
                Cliente cliente = clienteServicio.obtenerPorId(id);
                request.setAttribute("cliente", cliente);
                RequestDispatcher formEditar = request.getRequestDispatcher("/vistas/clientes/actualizar.jsp");
                formEditar.forward(request, response);
                break;
            
            //Store
            case "guardar":
                
                c.setNombre(request.getParameter("nombre"));
                c.setApellido(request.getParameter("apellido"));
                c.setDocumento(request.getParameter("documento"));
                c.setTipoDocumento(request.getParameter("tipoDocumento"));
                c.setDireccion(request.getParameter("direccion"));
                c.setTelefono(request.getParameter("telefono"));
                c.setEmail(request.getParameter("email"));

                try {
                    clienteServicio.registrarCliente(c);
                    //Redirijo a la url principal
                    response.sendRedirect(request.getContextPath()+"/clientes");
                } catch (SQLException e) {
                    throw new ServletException("Error al registrar cliente", e);
                }
                break;
            
            //Update
            case "actualizar":
                 
                c.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                c.setNombre(request.getParameter("nombre"));
                c.setApellido(request.getParameter("apellido"));
                c.setDocumento(request.getParameter("documento"));
                c.setTipoDocumento(request.getParameter("tipoDocumento"));
                c.setDireccion(request.getParameter("direccion"));
                c.setTelefono(request.getParameter("telefono"));
                c.setEmail(request.getParameter("email"));

                clienteServicio.actualizar(c);
                response.sendRedirect(request.getContextPath()+"/clientes");

                break;  
                
            //Delete   
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("idCliente"));
                clienteServicio.eliminar(idEliminar);
                response.sendRedirect(request.getContextPath()+"/clientes");
                break; 
        }
    }
   
}
