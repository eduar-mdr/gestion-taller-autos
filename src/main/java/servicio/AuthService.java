/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author fuent
 */
import dao.UsuarioDao;
import modelo.Usuario;
public class AuthService {
    private UsuarioDao usuarioDAO = new UsuarioDao();

    public Usuario autenticar(String email, String password) {
        return usuarioDAO.login(email, password);
    }
}
  

