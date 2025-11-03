/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Eduar Medrano
 */
import servicio.TipoVehiculoServicio;
import modelo.TipoVehiculo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class TipoVehiculoControlador extends HttpServlet {

    private TipoVehiculoServicio tipoServicio = new TipoVehiculoServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {

            case "crear":
                request.getRequestDispatcher("/vistas/tipos/crear.jsp")
                        .forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("idTipo"));
                TipoVehiculo tipo = tipoServicio.obtenerPorId(id);
                request.setAttribute("tipo", tipo);
                request.getRequestDispatcher("/vistas/tipos/editar.jsp")
                        .forward(request, response);
                break;

            case "listar":
            default:
                try {
                    List<TipoVehiculo> tipos = tipoServicio.obtenerTipos();
                    request.setAttribute("tipos", tipos);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/tipos/index.jsp")
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
                TipoVehiculo t = new TipoVehiculo();
                t.setNombreTipo(request.getParameter("nombreTipo"));

                try {
                    tipoServicio.registrarTipo(t);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect(request.getContextPath() + "/tipos?action=listar");
                break;
            }

            case "actualizar": {
                TipoVehiculo t = new TipoVehiculo();
                t.setIdTipo(Integer.parseInt(request.getParameter("idTipo")));
                t.setNombreTipo(request.getParameter("nombreTipo"));

                tipoServicio.actualizar(t);
                response.sendRedirect(request.getContextPath() + "/tipos?action=listar");
                break;
            }

            case "eliminar": {
                int id = Integer.parseInt(request.getParameter("idTipo"));
                tipoServicio.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/tipos?action=listar");
                break;
            }
        }
    }
}
