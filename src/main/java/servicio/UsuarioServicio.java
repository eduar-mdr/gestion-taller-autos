/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author fuent
 */
import seguridad.Hash;
import dao.UsuarioDao;
import modelo.Usuario;
import java.util.List;

import java.sql.SQLException;

public class UsuarioServicio {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    /**
     * Registra un nuevo usuario:
     * - Hashea la contraseña con SHA-256
     * - Marca estado = 'Activo'
     * - Llama al DAO para insertar
     */
    public void registrarUsuario(Usuario u) throws SQLException {

        // Hashear contraseña en texto plano
        String hash = Hash.sha256(u.getContrasena());
        u.setContrasena(hash);

        if (u.getEstado() == null || u.getEstado().isBlank()) {
            u.setEstado("Activo");
        }

        usuarioDao.insertar(u);
    }
    public List<Usuario> obtenerUsuarios() throws SQLException {
        return usuarioDao.obtenerUsuarios();
    }
    
    // Show
    public Usuario obtenerPorId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del usuario no es válido");
        }
        return usuarioDao.obtenerPorId(id);
    }

}
