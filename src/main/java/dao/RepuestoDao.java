/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.ConexionDB;
import modelo.Repuesto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepuestoDao {

    public List<Repuesto> listar() {
        List<Repuesto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Repuesto";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Repuesto r = new Repuesto();
                r.setIdRepuesto(rs.getInt("id_repuesto"));
                r.setNombre(rs.getString("nombre"));
                r.setDescripcion(rs.getString("descripcion"));
                r.setPrecio(rs.getDouble("precio"));
                r.setIdProveedor(rs.getInt("id_proveedor"));

                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Repuesto buscarPorId(int id) {
        Repuesto r = null;
        String sql = "SELECT * FROM Repuesto WHERE id_repuesto = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    r = new Repuesto();
                    r.setIdRepuesto(rs.getInt("id_repuesto"));
                    r.setNombre(rs.getString("nombre"));
                    r.setDescripcion(rs.getString("descripcion"));
                    r.setPrecio(rs.getDouble("precio"));
                    r.setIdProveedor(rs.getInt("id_proveedor"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    public void insertar(Repuesto r) {
        String sql = "INSERT INTO Repuesto (nombre, descripcion, precio, id_proveedor) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getNombre());
            ps.setString(2, r.getDescripcion());
            ps.setDouble(3, r.getPrecio());
            ps.setInt(4, r.getIdProveedor());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Repuesto r) {
        String sql = "UPDATE Repuesto SET nombre=?, descripcion=?, precio=?, id_proveedor=? "
                   + "WHERE id_repuesto=?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getNombre());
            ps.setString(2, r.getDescripcion());
            ps.setDouble(3, r.getPrecio());
            ps.setInt(4, r.getIdProveedor());
            ps.setInt(5, r.getIdRepuesto());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM Repuesto WHERE id_repuesto = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

