/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author fuent
 */
import conexion.ConexionDB;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        Usuario u = null;

        String sql = "SELECT id_usuario, nombre_usuario, contrasena, estado, id_rol "
                   + "FROM Usuario WHERE nombre_usuario = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setContrasena(rs.getString("contrasena")); // hash SHA-256
                u.setEstado(rs.getString("estado"));
                u.setIdRol(rs.getInt("id_rol"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        Usuario u = null;

        String sql = "SELECT id_usuario, nombre_usuario, email, contrasena, estado, id_rol "
                   + "FROM Usuario WHERE email = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setEmail(rs.getString("email"));
                u.setContrasena(rs.getString("contrasena")); // hash en BD
                u.setEstado(rs.getString("estado"));
                u.setIdRol(rs.getInt("id_rol"));
            }
        }
        return u;
    }

    public List<Usuario> obtenerUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT u.id_usuario, u.nombre_usuario, u.email, u.estado, "
                   + "       u.id_rol, r.nombre_rol "
                   + "FROM Usuario u "
                   + "INNER JOIN Rol r ON u.id_rol = r.id_rol "
                   + "ORDER BY u.id_usuario";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setEmail(rs.getString("email"));
                u.setEstado(rs.getString("estado"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setRolNombre(rs.getString("nombre_rol"));

                lista.add(u);
            }
        }

        return lista;
    }

    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO Usuario "
                   + " (nombre_usuario, email, contrasena, id_rol, estado) "
                   + " VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getContrasena()); // hash SHA-256
            ps.setInt(4, u.getIdRol());
            ps.setString(5, u.getEstado());

            ps.executeUpdate();
        }
    }

    public Usuario obtenerPorId(int idUsuario) throws SQLException {
        Usuario u = null;

        String sql = "SELECT u.id_usuario, u.nombre_usuario, u.email, u.estado, "
                   + "       u.id_rol, r.nombre_rol "
                   + "FROM Usuario u "
                   + "INNER JOIN Rol r ON u.id_rol = r.id_rol "
                   + "WHERE u.id_usuario = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setEmail(rs.getString("email"));
                u.setEstado(rs.getString("estado"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setRolNombre(rs.getString("nombre_rol"));
            }
        }

        return u;
    }

    public void eliminar(int idUsuario) throws SQLException {
        String sql = "UPDATE Usuario SET estado = 'Inactivo' WHERE id_usuario = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        }
    }
    

    public Usuario login(String email, String contrasena) {
        String sql = "SELECT u.id_usuario, u.nombre_usuario, u.email, u.id_rol, r.nombre_rol " +
                     "FROM Usuario u " +
                     "JOIN Rol r ON u.id_rol = r.id_rol " +
                     "WHERE u.email = ? AND u.contrasena = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setNombreUsuario(rs.getString("nombre_usuario"));
                    u.setEmail(rs.getString("email"));
                    u.setIdRol(rs.getInt("id_rol"));
                    u.setRolNombre(rs.getString("nombre_rol"));
                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // login falló
    }

}
