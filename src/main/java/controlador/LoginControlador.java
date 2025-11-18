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

        if (!u.getContrasena().equals(password)) {
            req.setAttribute("error", "Usuario o contraseña incorrectos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("usuarioLogueado", u);

        resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
    }
}
/*
DELETE FROM Usuario WHERE nombre_usuario = 'admin';

INSERT INTO Usuario (nombre_usuario, email, contrasena, id_rol, estado)
VALUES ('admin', 'admin@taller.com', '123456', 1, 'Activo');

*/