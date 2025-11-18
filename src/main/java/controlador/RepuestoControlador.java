/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Repuesto;
import modelo.Proveedor;
import servicio.RepuestoServicio;
import servicio.ProveedorServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/repuestos")
public class RepuestoControlador extends HttpServlet {

    private RepuestoServicio repuestoServicio = new RepuestoServicio();
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
                try {
                    // Para mostrar la lista de proveedores al crear un repuesto
                    request.setAttribute("proveedores", proveedorServicio.obtenerProveedores());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/repuestos/crear.jsp")
                        .forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("idRepuesto"));
                Repuesto repuesto = repuestoServicio.obtenerPorId(id);
                request.setAttribute("repuesto", repuesto); 
                request.getRequestDispatcher("/vistas/repuestos/actualizar.jsp").forward(request, response);
                break;

            case "listar":
            default:
                try {
                    List<Repuesto> repuestos = repuestoServicio.obtenerRepuestos();
                    request.setAttribute("repuestos", repuestos);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/repuestos/index.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "editar":
                int id = Integer.parseInt(request.getParameter("idRepuesto"));
                Repuesto repuesto = repuestoServicio.obtenerPorId(id);
                request.setAttribute("repuesto", repuesto); 
                request.getRequestDispatcher("/vistas/repuestos/actualizar.jsp").forward(request, response);
                break;

            case "guardar": {
                Repuesto r = new Repuesto();
                r.setNombre(request.getParameter("nombre"));
                r.setDescripcion(request.getParameter("descripcion"));
                r.setPrecio(Double.parseDouble(request.getParameter("precio")));
                r.setIdProveedor(Integer.parseInt(request.getParameter("idProveedor")));

                try {
                    repuestoServicio.registrarRepuesto(r);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect(request.getContextPath() + "/repuestos?action=listar");
                break;
            }

            case "actualizar": {
                Repuesto r = new Repuesto();
                r.setIdRepuesto(Integer.parseInt(request.getParameter("idRepuesto")));
                r.setNombre(request.getParameter("nombre"));
                r.setDescripcion(request.getParameter("descripcion"));
                r.setPrecio(Double.parseDouble(request.getParameter("precio")));

                repuestoServicio.actualizar(r);
                response.sendRedirect(request.getContextPath() + "/repuestos?action=listar");
                break;
            }

            case "eliminar": {
                int idEliminar = Integer.parseInt(request.getParameter("idRepuesto"));
                repuestoServicio.eliminar(idEliminar);
                response.sendRedirect(request.getContextPath() + "/repuestos?action=listar");
                break;
            }
        }
    }
}
