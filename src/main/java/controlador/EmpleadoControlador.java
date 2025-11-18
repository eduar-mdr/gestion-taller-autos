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
import modelo.Empleado;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

@WebServlet("/empleados")
public class EmpleadoControlador extends HttpServlet {

    private EmpleadoServicio empleadoServicio = new EmpleadoServicio();

    //Index
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "index";
        }

        switch (action) {
            case "crear":
                RequestDispatcher formCrear = request.getRequestDispatcher("/vistas/empleados/crear.jsp");
                formCrear.forward(request, response);
                break;

            default:
                try {
                    List<Empleado> lista = empleadoServicio.obtenerEmpleados();
                    request.setAttribute("empleados", lista);
                    RequestDispatcher index = request.getRequestDispatcher("/vistas/empleados/index.jsp");
                    index.forward(request, response);
                } catch (SQLException e) {
                    throw new ServletException("Error al listar empleados", e);
                }
                break;
        }
    }

    //Store / Update / Delete
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Empleado m = new Empleado();

        String fechaStr = request.getParameter("fechaContratacion");
        if (fechaStr != null && !fechaStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, formatter);
            m.setFechaContratacion(fecha);
        }

        switch (action) {
            case "editar":

                int id = Integer.parseInt(request.getParameter("idCliente"));
                Empleado empleado = empleadoServicio.obtenerPorId(id);
                request.setAttribute("empleado", empleado);
                RequestDispatcher formEditar = request.getRequestDispatcher("/vistas/empleados/actualizar.jsp");
                formEditar.forward(request, response);
                break;
            case "guardar":
                m.setNombre(request.getParameter("nombre"));
                m.setApellido(request.getParameter("apellido"));
                m.setDui(Integer.parseInt(request.getParameter("dui")));
                m.setTelefono(Integer.parseInt(request.getParameter("telefono")));
                m.setDireccion(request.getParameter("direccion"));
                m.setCargo(request.getParameter("cargo"));
                m.setSalario(Double.parseDouble(request.getParameter("salario")));
                m.setEstado(request.getParameter("estado"));

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
                m.setDui(Integer.parseInt(request.getParameter("dui")));
                m.setTelefono(Integer.parseInt(request.getParameter("telefono")));
                m.setDireccion(request.getParameter("direccion"));
                m.setCargo(request.getParameter("cargo"));
                m.setSalario(Double.parseDouble(request.getParameter("salario")));
                m.setEstado(request.getParameter("estado"));

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
