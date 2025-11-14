/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Eduar Medrano
 */
import servicio.ProveedorServicio;
import modelo.Proveedor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/proveedores")
public class ProveedorControlador extends HttpServlet {

    private ProveedorServicio proveedorServicio = new ProveedorServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        switch (action) {

            case "crear":
                request.getRequestDispatcher("/vistas/proveedores/crear.jsp")
                        .forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("idProveedor"));
                Proveedor proveedor = proveedorServicio.obtenerPorId(id);
                request.setAttribute("proveedor", proveedor);
                request.getRequestDispatcher("/vistas/proveedores/actualizar.jsp")
                        .forward(request, response);
                break;

            case "listar":
            default:
                try {
                    List<Proveedor> proveedores = proveedorServicio.obtenerProveedores();
                    request.setAttribute("proveedores", proveedores);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/proveedores/index.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "editar": {
                int id = Integer.parseInt(request.getParameter("idProveedor"));
                Proveedor proveedor = proveedorServicio.obtenerPorId(id);
                request.setAttribute("proveedor", proveedor);
                request.getRequestDispatcher("/vistas/proveedores/actualizar.jsp")
                        .forward(request, response);
                break;
            }
            case "guardar": {
                Proveedor p = new Proveedor();
                p.setNombre(request.getParameter("nombre"));
                p.setContacto(request.getParameter("contacto"));
                p.setTelefono(request.getParameter("telefono"));
                p.setEmail(request.getParameter("email"));
                p.setDireccion(request.getParameter("direccion"));
                p.setTipoProveedor(request.getParameter("tipoProveedor"));
                p.setEstado(request.getParameter("estado"));

                try {
                    proveedorServicio.registrarProveedor(p);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect(request.getContextPath() + "/proveedores?action=listar");
                break;
            }

            case "actualizar": {
                Proveedor p = new Proveedor();
                p.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));
                p.setNombre(request.getParameter("nombre"));
                p.setContacto(request.getParameter("contacto"));
                p.setTelefono(request.getParameter("telefono"));
                p.setEmail(request.getParameter("email"));
                p.setDireccion(request.getParameter("direccion"));
                p.setTipoProveedor(request.getParameter("tipoProveedor"));
                p.setEstado(request.getParameter("estado"));

                proveedorServicio.actualizar(p);
                response.sendRedirect(request.getContextPath() + "/proveedores?action=listar");
                break;
            }

            case "eliminar": {
                int id = Integer.parseInt(request.getParameter("idProveedor"));
                proveedorServicio.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/proveedores?action=listar");
                break;
            }
        }
    }
}
