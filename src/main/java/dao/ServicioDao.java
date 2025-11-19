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
import modelo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDao {
    //Index
    public List<Servicio> listar() throws SQLException {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Servicio";
        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Servicio m = new Servicio();
                m.setIdServicio(rs.getInt("id_servicio"));
                m.setNombre(rs.getString("nombre"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setPrecio(rs.getDouble("precio"));
                m.setCategoria(rs.getString("categoria"));
                m.setDuracionEstimada(rs.getString("duracion_estimada"));
                m.setEstado(rs.getString("estado"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("No fue posible obtener los registros");
            e.printStackTrace();
        }
        return lista;
    }
    //Store
    public void insertar(Servicio m) throws SQLException {
        String sql = "INSERT INTO Servicio(nombre, descripcion, precio, categoria, duracion_estimada, estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.setDouble(3, m.getPrecio());
             ps.setString(4, m.getCategoria());
            ps.setString(5, m.getDuracionEstimada());
            ps.setString(6, m.getEstado());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No fue posble insertar registro");
            e.printStackTrace();
        }
    }
    
    //Show
    // Buscar por ID 
    public Servicio buscarPorId(int id) {
        Servicio servicio = null;
        String sql = "SELECT * FROM Servicio WHERE id_servicio = ?";
        try (Connection conn = ConexionDB.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    servicio = new Servicio();
                    servicio.setIdServicio(rs.getInt("id_servicio"));
                    servicio.setNombre(rs.getString("nombre"));
                    servicio.setDescripcion(rs.getString("descripcion"));
                    servicio.setPrecio(rs.getDouble("precio"));
                    servicio.setCategoria(rs.getString("categoria"));
                    servicio.setDuracionEstimada(rs.getString("duracion_estimada"));
                    servicio.setEstado(rs.getString("estado"));
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al obtenr el registro");
        }
        return servicio;
    }
    //Update
    public void actualizar(Servicio servicio) {
        String sql = "UPDATE Servicio SET nombre=?, descripcion=?, precio=?, categoria=?, duracion_estimada=?, estado=? "
           + "WHERE id_servicio=?";
        try (Connection conn = ConexionDB.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getPrecio());
            ps.setString(4, servicio.getCategoria());
            ps.setString(5, servicio.getDuracionEstimada());
            ps.setString(6, servicio.getEstado());
            ps.setInt(7, servicio.getIdServicio());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al actualizar el registro");
            e.printStackTrace();
        }
    }
    
    //Delete
    public void eliminar(int id) {
    String sql = "DELETE FROM Servicio WHERE id_servicio = ?";
    try (Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Ocurrio un error al eliminar registro");
        e.printStackTrace();
    }
}

}
