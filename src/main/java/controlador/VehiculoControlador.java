/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Eduar Medrano
 */
import modelo.Vehiculo;
import servicio.VehiculoServicio;
import servicio.ClienteServicio;
import servicio.TipoVehiculoServicio;
import servicio.MarcaVehiculoServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/vehiculos")
public class VehiculoControlador extends HttpServlet  {
    
    private VehiculoServicio vehiculoServicio = new VehiculoServicio();
    private ClienteServicio clienteServicio = new ClienteServicio();
    private TipoVehiculoServicio tipoServicio = new TipoVehiculoServicio();
    private MarcaVehiculoServicio marcaServicio = new MarcaVehiculoServicio();

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
                    request.setAttribute("clientes", clienteServicio.obtenerClientes());
                    request.setAttribute("tipos", tipoServicio.obtenerTipos());
                    request.setAttribute("marcas", marcaServicio.obtenerMarcas());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/vehiculos/crear.jsp")
                        .forward(request, response);
                break;

            default:
                try {
                    List<Vehiculo> vehiculos = vehiculoServicio.obtenerVehiculos();
                    request.setAttribute("vehiculos", vehiculos);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/vistas/vehiculos/index.jsp")
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
                int id = Integer.parseInt(request.getParameter("idVehiculo"));
                Vehiculo v = vehiculoServicio.obtenerPorId(id);
                request.setAttribute("vehiculo", v);

                try {
                     request.setAttribute("clientes", clienteServicio.obtenerClientes());
                    request.setAttribute("tipos", tipoServicio.obtenerTipos());
                    request.setAttribute("marcas", marcaServicio.obtenerMarcas());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                request.getRequestDispatcher("/vistas/vehiculos/actualizar.jsp")
                        .forward(request, response);
                break;
            }

            case "guardar": {
                Vehiculo v = new Vehiculo();

                v.setModelo(request.getParameter("modelo"));
                v.setAnio(Integer.parseInt(request.getParameter("anio")));
                v.setPlaca(request.getParameter("placa"));
                v.setNumMotor(request.getParameter("numMotor"));
                v.setColor(request.getParameter("color"));
                v.setKilometraje(Integer.parseInt(request.getParameter("kilometraje")));
                v.setFechaIngreso(request.getParameter("fechaIngreso"));
                v.setNumChasis(request.getParameter("numChasis"));
                v.setHistorialServicioUrl(request.getParameter("historialServicioUrl"));
                v.setEstadoVehiculo(request.getParameter("estadoVehiculo"));

                // FK
                v.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                v.setIdTipo(Integer.parseInt(request.getParameter("idTipo")));
                v.setIdMarca(Integer.parseInt(request.getParameter("idMarca")));

                try {
                    vehiculoServicio.registrarVehiculo(v);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect(request.getContextPath() + "/vehiculos?action=listar");
                break;
            }

            case "actualizar": {
                Vehiculo v = new Vehiculo();

                v.setIdVehiculo(Integer.parseInt(request.getParameter("idVehiculo")));
                v.setModelo(request.getParameter("modelo"));
                v.setAnio(Integer.parseInt(request.getParameter("anio")));
                v.setPlaca(request.getParameter("placa"));
                v.setNumMotor(request.getParameter("numMotor"));
                v.setColor(request.getParameter("color"));
                v.setKilometraje(Integer.parseInt(request.getParameter("kilometraje")));
                v.setFechaIngreso(request.getParameter("fechaIngreso"));
                v.setNumChasis(request.getParameter("numChasis"));
                v.setHistorialServicioUrl(request.getParameter("historialServicioUrl"));
                v.setEstadoVehiculo(request.getParameter("estadoVehiculo"));

                // FK
                v.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                v.setIdTipo(Integer.parseInt(request.getParameter("idTipo")));
                v.setIdMarca(Integer.parseInt(request.getParameter("idMarca")));

                vehiculoServicio.actualizar(v);

                response.sendRedirect(request.getContextPath() + "/vehiculos?action=listar");
                break;
            }

            case "eliminar": {
                int idEliminar = Integer.parseInt(request.getParameter("idVehiculo"));
                vehiculoServicio.eliminar(idEliminar);
                response.sendRedirect(request.getContextPath() + "/vehiculos?action=listar");
                break;
            }
        }
    }

}
