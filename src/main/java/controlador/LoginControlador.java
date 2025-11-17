/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author fuent
 */
import dao.UsuarioDao;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import seguridad.Hash;

@WebServlet("/login")
public class LoginControlador extends HttpServlet {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("usuarioLogueado") != null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
            return;
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String password = req.getParameter("contrasena");

        Usuario u = usuarioDao.buscarPorNombreUsuario(usuario);

        if (u == null || !"Activo".equalsIgnoreCase(u.getEstado())) {
            req.setAttribute("error", "Usuario o contraseña incorrectos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        String hashIngresado = Hash.hashPassword(password, u.getSalt());
        System.out.println("hashIngresado = " + hashIngresado);
        System.out.println("hashBD        = " + u.getContrasena());

        if (!hashIngresado.equals(u.getContrasena())) {
            req.setAttribute("error", "Usuario o contraseña incorrectos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
    }
}
/*
INSERT INTO Usuario (
    nombre_usuario,
    email,
    contrasena,
    id_rol,
    salt,
    estado
)
VALUES (
    'admin',
    'admin@taller.com',
    'dc25ff605c3507cb240e2ea641d26328557e32ae4ff06db6badb990cc689d0cc',
    1,
    '43fc8fe2b05b311e0c940bf483318f0d',
    'Activo'
);
INSERT INTO Rol (nombre_rol, descripcion) VALUES ('admin', 'admin');
user: admin pass: 123456
*/