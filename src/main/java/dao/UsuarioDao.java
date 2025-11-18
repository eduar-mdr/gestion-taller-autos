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

public class UsuarioDao {

    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        Usuario u = null;

        String sql = "SELECT id_usuario, nombre_usuario, contrasena, estado, id_rol "
                + "FROM Usuario WHERE nombre_usuario = ?";

        try (Connection conn = ConexionDB.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setContrasena(rs.getString("contrasena")); // ← contraseña normal
                u.setEstado(rs.getString("estado"));
                u.setIdRol(rs.getInt("id_rol"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
}
