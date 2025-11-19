/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author MINEDUCYT
 */
import conexion.ConexionDB;
import modelo.Empleado;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {

    public List<Empleado> listar() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Empleado";
        try (Connection conn = ConexionDB.conectar(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Empleado m = new Empleado();
                m.setIdEmpleado(rs.getInt("id_empleado"));
                m.setNombre(rs.getString("nombre"));
                m.setApellido(rs.getString("apellido"));
                m.setDui(rs.getInt("dui"));
                m.setTelefono(rs.getInt("telefono"));
                m.setDireccion(rs.getString("direccion"));
                m.setCargo(rs.getString("cargo"));
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setSalario(rs.getDouble("salario"));
                m.setEstado(rs.getString("estado"));

                Timestamp ts = rs.getTimestamp("fecha_contratacion");
                if (ts != null) {
                    m.setFechaContratacion(ts.toLocalDateTime());
                }
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("No fue posible obtener los registros");
            e.printStackTrace();
        }
        return lista;
    }
    //Store

    public void insertar(Empleado m) throws SQLException {
        String sql = "INSERT INTO Empleado(nombre, apellido, dui, telefono, direccion, cargo, id_usuario, salario, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getApellido());
            ps.setInt(3, m.getDui());
            ps.setInt(4, m.getTelefono());
            ps.setString(5, m.getDireccion());
            ps.setString(6, m.getCargo());
            ps.setInt(7, m.getIdUsuario());
            ps.setDouble(8, m.getSalario());
            ps.setString(9, m.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No fue posble insertar registro");
            e.printStackTrace();
        }

    }

    //Show
    // Buscar por ID 
    public Empleado buscarPorId(int id) {
        Empleado empleado = null;
        String sql = "SELECT * FROM Empleado WHERE id_empleado = ?";
        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    empleado = new Empleado();
                    empleado.setIdEmpleado(rs.getInt("id_empleado"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setApellido(rs.getString("apellido"));
                    empleado.setDui(rs.getInt("dui"));
                    empleado.setTelefono(rs.getInt("telefono"));
                    empleado.setDireccion(rs.getString("direccion"));
                    empleado.setCargo(rs.getString("cargo"));
                    empleado.setIdUsuario(rs.getInt("id_usuario"));
                    empleado.setSalario(rs.getDouble("salario"));
                    empleado.setEstado(rs.getString("estado"));
                    
                    Timestamp ts = rs.getTimestamp("fecha_contratacion");
                    if (ts != null) {
                        empleado.setFechaContratacion(ts.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al obtenr el registro");
        }
        return empleado;
    }

    //Update
    public void actualizar(Empleado empleado) {
        String sql = "UPDATE Empleado SET nombre=?, apellido=?, dui=?, telefono=?, direccion=?, cargo=?, id_usuario=?, salario=?, estado=? "
                + "WHERE id_empleado=?";
        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setInt(3, empleado.getDui());
            ps.setInt(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.setString(6, empleado.getCargo());
            ps.setInt(7, empleado.getIdUsuario());
            ps.setDouble(8, empleado.getSalario());
            ps.setString(9, empleado.getEstado());
            ps.setInt(10, empleado.getIdEmpleado());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al actualizar el registro");
            e.printStackTrace();
        }
    }

    //Delete
    public void eliminar(int id) {
        String sql = "DELETE FROM Empleado WHERE id_empleado = ?";
        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al eliminar registro");
            e.printStackTrace();
        }
    }

}
