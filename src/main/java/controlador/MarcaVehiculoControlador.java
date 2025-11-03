/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Eduar Medrano
 */

import servicio.MarcaVehiculoServicio;
import modelo.MarcaVehiculo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/marcas")
public class MarcaVehiculoControlador extends HttpServlet {
     private MarcaVehiculoServicio marcaServicio = new MarcaVehiculoServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {

            case "crear":
                request.getRequestDispatcher("/vistas/marcas/crear.jsp")
                        .forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("idMarca"));
                MarcaVehiculo marca = marcaServicio.obtenerPorId(id);
                request.setAttribute("marca", marca);
                request.getRequestDispatcher("/vistas/marcas/editar.jsp")
                        .forward(request, response);
                break;

            case "listar":
            default:
                try {
                    List<MarcaVehiculo> marcas = marcaServicio.obtenerMarcas();
                    request.setAttribute("marcas", marcas);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/marcas/index.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {

            case "guardar": {
                MarcaVehiculo m = new MarcaVehiculo();
                m.setNombreMarca(request.getParameter("nombreMarca"));

                try {
                    marcaServicio.registrarMarca(m);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect(request.getContextPath() + "/marcas?action=listar");
                break;
            }

            case "actualizar": {
                MarcaVehiculo m = new MarcaVehiculo();
                m.setIdMarca(Integer.parseInt(request.getParameter("idMarca")));
                m.setNombreMarca(request.getParameter("nombreMarca"));

                marcaServicio.actualizar(m);
                response.sendRedirect(request.getContextPath() + "/marcas?action=listar");
                break;
            }

            case "eliminar": {
                int id = Integer.parseInt(request.getParameter("idMarca"));
                marcaServicio.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/marcas?action=listar");
                break;
            }
        }
    }
}
