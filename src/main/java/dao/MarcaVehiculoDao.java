/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.ConexionDB;
import modelo.MarcaVehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduar Medrano
 */
public class MarcaVehiculoDao {
     // Index
    public List<MarcaVehiculo> listar() throws SQLException {
        List<MarcaVehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM MarcaVehiculo";

        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                MarcaVehiculo m = new MarcaVehiculo();
                m.setIdMarca(rs.getInt("id_marca"));
                m.setNombreMarca(rs.getString("nombre_marca"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar marcas de vehículo");
            e.printStackTrace();
        }
        return lista;
    }

    // Store
    public void insertar(MarcaVehiculo marca) {
        String sql = "INSERT INTO MarcaVehiculo (nombre_marca) VALUES (?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, marca.getNombreMarca());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar marca");
            e.printStackTrace();
        }
    }

    //Search by ID
    public MarcaVehiculo buscarPorId(int id) {
        MarcaVehiculo marca = null;
        String sql = "SELECT * FROM MarcaVehiculo WHERE id_marca = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    marca = new MarcaVehiculo();
                    marca.setIdMarca(rs.getInt("id_marca"));
                    marca.setNombreMarca(rs.getString("nombre_marca"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar marca por ID");
            e.printStackTrace();
        }
        return marca;
    }

    // Update
    public void actualizar(MarcaVehiculo marca) {
        String sql = "UPDATE MarcaVehiculo SET nombre_marca=? WHERE id_marca=?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, marca.getNombreMarca());
            ps.setInt(2, marca.getIdMarca());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar marca");
            e.printStackTrace();
        }
    }

    // Delete
    public void eliminar(int id) {
        String sql = "DELETE FROM MarcaVehiculo WHERE id_marca=?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar marca");
            e.printStackTrace();
        }
    }
}
