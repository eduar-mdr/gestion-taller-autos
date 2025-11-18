/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author fuent
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import conexion.ConexionDB; 

public class RBACService {

    // 1) Para el filtro: revisa un permiso puntual
    public boolean tienePermiso(int idUsuario, String codigoPermiso) {
        String sql = "SELECT dbo.fn_TienePermiso(?, ?) AS TienePermiso";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, codigoPermiso);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("TienePermiso");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 2) Para los JSP: obtener todos los permisos del usuario
    public Set<String> obtenerPermisosUsuario(int idUsuario) {
        Set<String> permisos = new HashSet<>();

        String sql = "SELECT p.codigo_permiso " +
                     "FROM Usuario u " +
                     "JOIN Rol r ON u.id_rol = r.id_rol " +
                     "JOIN RolPermiso rp ON r.id_rol = rp.id_rol AND rp.permitido = 1 " +
                     "JOIN Permiso p ON rp.id_permiso = p.id_permiso " +
                     "WHERE u.id_usuario = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    permisos.add(rs.getString("codigo_permiso"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return permisos;
    }
}
