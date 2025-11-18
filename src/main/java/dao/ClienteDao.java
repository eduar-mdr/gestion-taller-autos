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
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao { //Dao es Data Access Object 
    
    //Index
    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDocumento(rs.getString("documento"));
                c.setTipoDocumento(rs.getString("tipo_documento"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("No fue posible obtener los registros");
            e.printStackTrace();
        }
        return lista;
    }
    //Store
    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO Cliente(nombre, apellido, documento, tipo_documento, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getDocumento());
            ps.setString(4, c.getTipoDocumento());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getTelefono());
            ps.setString(7, c.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No fue posble insertar registro");
            e.printStackTrace();
        }
    }
    
    //Show
    // Buscar por ID 
    public Cliente buscarPorId(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setDocumento(rs.getString("documento"));
                    cliente.setTipoDocumento(rs.getString("tipo_documento"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al obtenr el registro");
        }
        return cliente;
    }
    //Update
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre=?, apellido=?, documento=?, tipo_documento=?, direccion=?, telefono=?, email=? "
                   + "WHERE id_cliente=?";
        try (Connection conn = ConexionDB.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDocumento());
            ps.setString(4, cliente.getTipoDocumento());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getTelefono());
            ps.setString(7, cliente.getEmail());
            ps.setInt(8, cliente.getIdCliente());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al actualizar el registro");
            e.printStackTrace();
        }
    }
    
    //Delete
    public void eliminar(int id) {
    String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
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