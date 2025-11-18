/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fuent
 */
package com.taller.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import servicio.RBACService;

public class RBACFilter implements Filter {

    private RBACService rbacService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Se instancia una sola vez
        rbacService = new RBACService();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        // 1) RUTAS PÚBLICAS (NO PASAN POR RBAC)
        // Ajusta según tu app: login.jsp, LoginServlet, recursos estáticos, etc.
        if (uri.endsWith("login.jsp")
                || uri.endsWith("LoginServlet")
                || uri.contains("/resources/")
                || uri.contains("/css/")
                || uri.contains("/js/")
                || uri.contains("/img/")) {

            chain.doFilter(req, res);
            return;
        }

        // 2) VALIDAR USUARIO LOGUEADO
        Usuario usuario = null;
        if (session != null) {
            Object obj = session.getAttribute("usuarioLogueado");
            if (obj instanceof Usuario) {
                usuario = (Usuario) obj;
            }
        }

        if (usuario == null) {
            // No hay sesión → lo mandamos a login
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 3) MAPEAR URL → código de permiso
        String codigoPermisoNecesario = obtenerPermisoPorURL(uri);

        // Si no se requiere permiso especial para esa URL, se deja pasar
        if (codigoPermisoNecesario == null) {
            chain.doFilter(req, res);
            return;
        }

        // 4) Verificar permiso en BD usando la función fn_TienePermiso
        boolean tienePermiso = rbacService.tienePermiso(
                usuario.getIdUsuario(),
                codigoPermisoNecesario
        );

        if (!tienePermiso) {
            // No tiene permiso → mostramos página de acceso denegado
            request.setAttribute("mensaje", "No tiene permiso para acceder a esta opción.");
            request.getRequestDispatcher("/acceso_denegado.jsp").forward(request, response);
            return;
        }

        // 5) Todo ok → seguir con la cadena
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // Nada especial por ahora
    }

    /**
     * Mapea las URLs a códigos de permiso usados en la tabla Permiso
     * (codigo_permiso). Ajusta las rutas según tus Servlets / JSP reales.
     */
    private String obtenerPermisoPorURL(String uri) {

        // Ejemplos, AJUSTA a tus URLs reales:
        if (uri.contains("/ClienteListado") || uri.contains("/clientes.jsp")) {
            return "CLIENTE_VER";
        }
        if (uri.contains("/CrearCliente")) {
            return "CLIENTE_CREAR";
        }
        if (uri.contains("/EditarCliente")) {
            return "CLIENTE_EDITAR";
        }
        if (uri.contains("/EliminarCliente")) {
            return "CLIENTE_ELIMINAR";
        }
        if (uri.contains("/EmpleadoListado")) {
            return "EMPLEADO_VER";
        }
        if (uri.contains("/CrearEmpleado")) {
            return "EMPLEADO_CREAR";
        }
        if (uri.contains("/EditarEmpleado")) {
            return "EMPLEADO_EDITAR";
        }
        if (uri.contains("/EliminarEmpleado")) {
            return "EMPLEADO_ELIMINAR";
        }
        if (uri.contains("/VehiculoListado")) {
            return "VEHICULO_VER";
        }
        if (uri.contains("/CrearVehiculo")) {
            return "VEHICULO_CREAR";
        }

        if (uri.contains("/OrdenListado")) {
            return "ORDEN_VER";
        }
        if (uri.contains("/CrearOrden")) {
            return "ORDEN_CREAR";
        }

        if (uri.contains("/PagoListado")) {
            return "PAGO_VER";
        }
        if (uri.contains("/RegistrarPago")) {
            return "PAGO_CREAR";
        }

        // Si no requiere permiso específico, deja pasar
        return null;
    }
}
