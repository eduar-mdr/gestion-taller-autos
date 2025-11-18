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
import seguridad.Hash;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import servicio.RBACService;

@WebServlet("/login")
public class LoginControlador extends HttpServlet {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        // OJO: aquí uso el mismo nombre que en el filtro: "usuarioLogueado"
        if (session != null && session.getAttribute("usuarioLogueado") != null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
            return;
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Obtener parámetros del formulario
        String nombreUsuario = safeTrim(req.getParameter("usuario"));       // nombre_usuario
        String password      = safeTrim(req.getParameter("contrasena"));   // contraseña ingresada

        if (nombreUsuario == null || password == null ||
            nombreUsuario.isBlank() || password.isBlank()) {

            req.setAttribute("error", "Usuario y contraseña son obligatorios.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        // 2) Buscar usuario en BD
        Usuario u = usuarioDao.buscarPorNombreUsuario(nombreUsuario);

        if (u == null || !"Activo".equalsIgnoreCase(u.getEstado())) {
            req.setAttribute("error", "Usuario o contraseña incorrectos.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        // 3) Verificar contraseña (hash SHA-256)
        String hashIngresado = Hash.sha256(password);

        System.out.println("hashIngresado = " + hashIngresado);
        System.out.println("hashBD        = " + u.getContrasena());

        if (!hashIngresado.equals(u.getContrasena())) {
            req.setAttribute("error", "Usuario o contraseña incorrectos.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        // 4) Login correcto → crear sesión y guardar usuario
        HttpSession session = req.getSession(true);
        session.setAttribute("usuarioLogueado", u);  // OJO: este nombre debe coincidir con el filtro

        // 5) Cargar permisos RBAC y guardarlos en sesión para usarlos en JSP
        RBACService rbac = new RBACService();
        Set<String> permisos = rbac.obtenerPermisosUsuario(u.getIdUsuario());
        session.setAttribute("permisosUsuario", permisos);

        // 6) Redirigir al dashboard
        resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
    }

    private String safeTrim(String s) {
        return (s == null) ? null : s.trim();
    }
}
