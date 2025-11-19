/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Eduar Medrano
 */

import conexion.ConexionDB;
import modelo.Vehiculo;
import servicio.ClienteServicio;
import servicio.TipoVehiculoServicio;
import servicio.MarcaVehiculoServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDao {
    // Instancias de servicios de catálogos
    private ClienteServicio clienteServicio = new ClienteServicio();
    private TipoVehiculoServicio tipoVehiculoServicio = new TipoVehiculoServicio();
    private MarcaVehiculoServicio marcaVehiculoServicio = new MarcaVehiculoServicio();
    
    // LISTAR
    public List<Vehiculo> listar() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculo";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo v = new Vehiculo();

                v.setIdVehiculo(rs.getInt("id_vehiculo"));
                v.setModelo(rs.getString("modelo"));
                v.setAnio(rs.getInt("anio"));
                v.setPlaca(rs.getString("placa"));
                v.setNumMotor(rs.getString("num_motor"));
                v.setColor(rs.getString("color"));
                v.setKilometraje(rs.getInt("kilometraje"));
                v.setFechaIngreso(rs.getString("fecha_ingreso"));
                v.setNumChasis(rs.getString("num_chasis"));
                v.setHistorialServicioUrl(rs.getString("historial_servicio_url"));
                v.setEstadoVehiculo(rs.getString("estado_vehiculo"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdTipo(rs.getInt("id_tipo"));
                v.setIdMarca(rs.getInt("id_marca"));

                v.setNombreCliente(
                        clienteServicio.obtenerPorId(v.getIdCliente()).getNombre()
                        + ' ' +
                        clienteServicio.obtenerPorId(v.getIdCliente()).getApellido()
                );

                v.setNombreTipo(
                        tipoVehiculoServicio.obtenerPorId(v.getIdTipo()).getNombreTipo()
                );

                v.setNombreMarca(
                        marcaVehiculoServicio.obtenerPorId(v.getIdMarca()).getNombreMarca()
                );

                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // BUSCAR POR ID
    public Vehiculo buscarPorId(int id) {
        Vehiculo v = null;
        String sql = "SELECT * FROM Vehiculo WHERE id_vehiculo = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    v = new Vehiculo();

                    v.setIdVehiculo(rs.getInt("id_vehiculo"));
                    v.setModelo(rs.getString("modelo"));
                    v.setAnio(rs.getInt("anio"));
                    v.setPlaca(rs.getString("placa"));
                    v.setNumMotor(rs.getString("num_motor"));
                    v.setColor(rs.getString("color"));
                    v.setKilometraje(rs.getInt("kilometraje"));
                    v.setFechaIngreso(rs.getString("fecha_ingreso"));
                    v.setNumChasis(rs.getString("num_chasis"));
                    v.setHistorialServicioUrl(rs.getString("historial_servicio_url"));
                    v.setEstadoVehiculo(rs.getString("estado_vehiculo"));
                    v.setIdCliente(rs.getInt("id_cliente"));
                    v.setIdTipo(rs.getInt("id_tipo"));
                    v.setIdMarca(rs.getInt("id_marca"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }

    // INSERTAR
   public void insertar(Vehiculo v) {
    String sql = "INSERT INTO Vehiculo (modelo, anio, placa, num_motor, color, kilometraje, num_chasis, historial_servicio_url, estado_vehiculo, id_cliente, id_tipo, id_marca) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, v.getModelo());
        ps.setInt(2, v.getAnio());
        ps.setString(3, v.getPlaca());
        ps.setString(4, v.getNumMotor());
        ps.setString(5, v.getColor());
        ps.setInt(6, v.getKilometraje());
        ps.setString(7, v.getNumChasis());
        ps.setString(8, v.getHistorialServicioUrl());
        ps.setString(9, v.getEstadoVehiculo());
        ps.setInt(10, v.getIdCliente());
        ps.setInt(11, v.getIdTipo());
        ps.setInt(12, v.getIdMarca());

        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // ACTUALIZAR
    public void actualizar(Vehiculo v) {
        String sql = "UPDATE Vehiculo SET modelo=?, anio=?, placa=?, num_motor=?, color=?, kilometraje=?, fecha_ingreso=?, num_chasis=?, historial_servicio_url=?, estado_vehiculo=?, id_cliente=?, id_tipo=?, id_marca=? "
                   + "WHERE id_vehiculo=?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getModelo());
            ps.setInt(2, v.getAnio());
            ps.setString(3, v.getPlaca());
            ps.setString(4, v.getNumMotor());
            ps.setString(5, v.getColor());
            ps.setInt(6, v.getKilometraje());
            ps.setString(7, v.getNumChasis());
            ps.setString(8, v.getHistorialServicioUrl());
            ps.setString(9, v.getEstadoVehiculo());
            ps.setInt(10, v.getIdCliente());
            ps.setInt(11, v.getIdTipo());
            ps.setInt(12, v.getIdMarca());
            ps.setInt(13, v.getIdVehiculo());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ELIMINAR
    public void eliminar(int id) {
        String sql = "DELETE FROM Vehiculo WHERE id_vehiculo = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
