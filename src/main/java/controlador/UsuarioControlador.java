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
import servicio.UsuarioServicio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usuarios")
public class UsuarioControlador extends HttpServlet {

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "crear":
                
                RequestDispatcher formCrear = request.getRequestDispatcher("/vistas/usuarios/crear.jsp");
                formCrear.forward(request, response);
                break;

            case "editar":
                cargarUsuarioParaEditar(request, response);
                break;

            case "listar":
            default:
                listarUsuarios(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("UsuarioControlador POST action = " + action);

        if (action == null || action.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/usuarios?action=listar");
            return;
        }

        switch (action) {
            case "guardar":
                guardarUsuario(request, response);
                break;

            case "editar":
                cargarUsuarioParaEditar(request, response);
                break;

            case "eliminar":
                eliminarUsuario(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/usuarios?action=listar");
                break;
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Usuario> lista = usuarioDao.obtenerUsuarios();
            request.setAttribute("usuarios", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/vistas/usuarios/index.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al listar usuarios", e);
        }
    }

    private void cargarUsuarioParaEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("idUsuario");
        if (idStr == null || idStr.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/usuarios?action=listar&msg=errorIdVacio");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Usuario u = usuarioDao.obtenerPorId(id);
            if (u == null) {
                response.sendRedirect(request.getContextPath() + "/usuarios?action=listar&msg=noEncontrado");
                return;
            }
            request.setAttribute("usuario", u);
            RequestDispatcher rd = request.getRequestDispatcher("/vistas/usuarios/editar.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar usuario para edición", e);
        }
    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreUsuario = safeTrim(request.getParameter("nombreUsuario"));
        String email         = safeTrim(request.getParameter("email"));
        String idRolStr      = safeTrim(request.getParameter("idRol"));
        String pass          = safeTrim(request.getParameter("contrasena"));
        String pass2         = safeTrim(request.getParameter("confirmarContrasena"));

        if (nombreUsuario == null || email == null || idRolStr == null ||
            pass == null || pass2 == null ||
            nombreUsuario.isBlank() || email.isBlank() || idRolStr.isBlank() ||
            pass.isBlank() || pass2.isBlank()) {

            request.setAttribute("error", "Todos los campos son obligatorios.");
            RequestDispatcher rd = request.getRequestDispatcher("/vistas/usuarios/crear.jsp");
            rd.forward(request, response);
            return;
        }

        if (!pass.equals(pass2)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            RequestDispatcher rd = request.getRequestDispatcher("/vistas/usuarios/crear.jsp");
            rd.forward(request, response);
            return;
        }

        try {
            int idRol = Integer.parseInt(idRolStr);

            Usuario u = new Usuario();
            u.setNombreUsuario(nombreUsuario);
            u.setEmail(email);
            u.setIdRol(idRol);
            u.setContrasena(pass);   
            u.setEstado("Activo");

            // Verificar si ya existe el email
            Usuario existente = usuarioDao.buscarPorEmail(email);
            if (existente != null) {
                request.setAttribute("error", "Ya existe un usuario con ese correo.");
                RequestDispatcher rd = request.getRequestDispatcher("/vistas/usuarios/crear.jsp");
                rd.forward(request, response);
                return;
            }
           
            usuarioServicio.registrarUsuario(u);

            response.sendRedirect(request.getContextPath() + "/usuarios?action=listar&msg=creado");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al registrar usuario", e);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String idStr = request.getParameter("idUsuario");
        if (idStr == null || idStr.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/usuarios?action=listar&msg=errorIdVacio");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            usuarioDao.eliminar(id);  
            response.sendRedirect(request.getContextPath() + "/usuarios?action=listar&msg=eliminado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al eliminar usuario", e);
        }
    }

    private String safeTrim(String s) {
        return (s == null) ? null : s.trim();
    }
}
