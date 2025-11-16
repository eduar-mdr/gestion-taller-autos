/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.ConexionDB;
import modelo.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduar Medrano
 */
public class ProveedorDao {

    //Index
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";

        try (Connection conn = ConexionDB.conectar(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setContacto(rs.getString("contacto"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                p.setDireccion(rs.getString("direccion"));
                p.setTipoProveedor(rs.getString("tipo_proveedor"));
                p.setEstado(rs.getString("estado"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar proveedores");
            e.printStackTrace();
        }

        return lista;
    }
    //Store

    public void insertar(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedor (nombre, contacto, telefono, email, direccion, tipo_proveedor, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getContacto());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getTipoProveedor());
            ps.setString(7, proveedor.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar proveedor");
            e.printStackTrace();
        }
    }

    //Search by ID
    public Proveedor buscarPorId(int id) {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM Proveedor WHERE id_proveedor = ?";

        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor();
                    proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre(rs.getString("nombre"));
                    proveedor.setContacto(rs.getString("contacto"));
                    proveedor.setTelefono(rs.getString("telefono"));
                    proveedor.setEmail(rs.getString("email"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setTipoProveedor(rs.getString("tipo_proveedor"));
                    proveedor.setEstado(rs.getString("estado"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar proveedor por ID");
            e.printStackTrace();
        }

        return proveedor;
    }

    //Update
    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE Proveedor SET nombre=?, contacto=?, telefono=?, email=?, direccion=?, tipo_proveedor=?, estado=? "
                + "WHERE id_proveedor=?";

        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getContacto());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getTipoProveedor());
            ps.setString(7, proveedor.getEstado());
            ps.setInt(8, proveedor.getIdProveedor());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor");
            e.printStackTrace();
        }
    }

    //Delete
    public void eliminar(int id) {
        String sql = "DELETE FROM Proveedor WHERE id_proveedor = ?";

        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor");
            e.printStackTrace();
        }
    }
}
