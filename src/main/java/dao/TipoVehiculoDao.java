/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.ConexionDB;
import modelo.TipoVehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Eduar Medrano
 */
public class TipoVehiculoDao {
    // Listar todos los tipos
    public List<TipoVehiculo> listar() throws SQLException {
        List<TipoVehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoVehiculo";

        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                TipoVehiculo t = new TipoVehiculo();
                t.setIdTipo(rs.getInt("id_tipo"));
                t.setNombreTipo(rs.getString("nombre_tipo"));
                lista.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar tipos de vehículo");
            e.printStackTrace();
        }
        return lista;
    }

    // Insertar un tipo
    public void insertar(TipoVehiculo tipo) {
        String sql = "INSERT INTO TipoVehiculo (nombre_tipo) VALUES (?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipo.getNombreTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar tipo de vehículo");
            e.printStackTrace();
        }
    }

    // Buscar por ID
    public TipoVehiculo buscarPorId(int id) {
        TipoVehiculo tipo = null;
        String sql = "SELECT * FROM TipoVehiculo WHERE id_tipo = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipo = new TipoVehiculo();
                    tipo.setIdTipo(rs.getInt("id_tipo"));
                    tipo.setNombreTipo(rs.getString("nombre_tipo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar tipo por ID");
            e.printStackTrace();
        }
        return tipo;
    }

    // Actualizar
    public void actualizar(TipoVehiculo tipo) {
        String sql = "UPDATE TipoVehiculo SET nombre_tipo=? WHERE id_tipo=?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipo.getNombreTipo());
            ps.setInt(2, tipo.getIdTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tipo de vehículo");
            e.printStackTrace();
        }
    }

    // Eliminar
    public void eliminar(int id) {
        String sql = "DELETE FROM TipoVehiculo WHERE id_tipo=?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tipo de vehículo");
            e.printStackTrace();
        }
    }
}
