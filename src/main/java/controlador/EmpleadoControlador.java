/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author MINEDUCYT
 */
import servicio.EmpleadoServicio;
import servicio.UsuarioServicio;
import modelo.Empleado;
import modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoControlador extends HttpServlet {

    private EmpleadoServicio empleadoServicio = new EmpleadoServicio();
    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "index";
        }

        switch (action) {

            case "crear":
                try {
                    request.setAttribute("usuarios", usuarioServicio.obtenerUsuarios());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/empleados/crear.jsp")
                        .forward(request, response);
                break;

            case "listar":
            default:
                try {
                    List<Empleado> lista = empleadoServicio.obtenerEmpleados();
                    request.setAttribute("empleados", lista);
                } catch (SQLException e) {
                    throw new ServletException("Error al listar empleados", e);
                }
                request.getRequestDispatcher("/vistas/empleados/index.jsp")
                        .forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        Empleado m = new Empleado();

        switch (action) {

            case "editar":
                try {
                    int idEditar = Integer.parseInt(request.getParameter("idEmpleado"));

                    Empleado empleado = empleadoServicio.obtenerPorId(idEditar);
                    List<Usuario> usuarios = usuarioServicio.obtenerUsuarios();

                    request.setAttribute("empleado", empleado);
                    request.setAttribute("usuarios", usuarios);

                    request.getRequestDispatcher("/vistas/empleados/actualizar.jsp")
                            .forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Redirigir a una página de error o mostrar mensaje
                    request.setAttribute("error", "Error al obtener los datos del empleado.");
                    request.getRequestDispatcher("/vistas/error.jsp").forward(request, response);
                }
                break;

            case "guardar":
                m.setNombre(request.getParameter("nombre"));
                m.setApellido(request.getParameter("apellido"));
                m.setDui(request.getParameter("dui"));
                m.setTelefono(Integer.parseInt(request.getParameter("telefono")));
                m.setDireccion(request.getParameter("direccion"));
                m.setCargo(request.getParameter("cargo"));
                m.setSalario(Double.parseDouble(request.getParameter("salario")));
                m.setEstado(request.getParameter("estado"));

                String idUsuarioStr = request.getParameter("idUsuario");
                if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
                    m.setIdUsuario(Integer.parseInt(idUsuarioStr));
                }

                try {
                    empleadoServicio.registrarEmpleado(m);
                    response.sendRedirect(request.getContextPath() + "/empleados");
                } catch (SQLException e) {
                    throw new ServletException("Error al registrar empleado", e);
                }
                break;

            case "actualizar":
                m.setIdEmpleado(Integer.parseInt(request.getParameter("idEmpleado")));
                m.setNombre(request.getParameter("nombre"));
                m.setApellido(request.getParameter("apellido"));
                m.setDui(request.getParameter("dui"));
                m.setTelefono(Integer.parseInt(request.getParameter("telefono")));
                m.setDireccion(request.getParameter("direccion"));
                m.setCargo(request.getParameter("cargo"));
                m.setSalario(Double.parseDouble(request.getParameter("salario")));
                m.setEstado(request.getParameter("estado"));

                String idUsuarioStrUpd = request.getParameter("idUsuario");
                if (idUsuarioStrUpd != null && !idUsuarioStrUpd.isEmpty()) {
                    m.setIdUsuario(Integer.parseInt(idUsuarioStrUpd));
                }

                empleadoServicio.actualizarEmpleado(m);
                response.sendRedirect(request.getContextPath() + "/empleados");
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("idEmpleado"));
                empleadoServicio.eliminar(idEliminar);
                response.sendRedirect(request.getContextPath() + "/empleados");
                break;
        }
    }
}
