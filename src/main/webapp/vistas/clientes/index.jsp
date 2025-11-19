<%-- 
    Document   : index
    Created on : Oct 19, 2025, 11:33:23 PM
    Author     : Eduar Medrano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Set"%>
<%@page import="java.sql.Connection" %>
<%@page import="conexion.ConexionDB" %>
<%@page import="modelo.Usuario"%>
<%
    // Validar que hay usuario en sesión
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuarioLogueado == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    // Cargar permisos del usuario
    Set<String> permisos = (Set<String>) session.getAttribute("permisosUsuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Clientes</title>

        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>

        <!-- Datatables and Jquery -->
        <link  rel="stylesheet" href="datatables/datatables.css"/>
        <script src="datatables/jquery.js"></script>
        <script src="datatables/datatables.js"></script>

    </head>
    <body>

        <!-- Menú principal -->
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#1B3C53; position: sticky; top: 0; z-index: 1000;">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard.jsp" style="color:#fff; font-weight:bold;">
                    <i class="bi bi-house-door-fill"></i> Taller Mecánico
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="menuNav">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">

                        <!-- Personas -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                                <i class="bi bi-people-fill"></i> Personas
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/clientes?action=listar"><i class="bi bi-person-check-fill"></i> Clientes</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/empleados?action=listar"><i class="bi bi-person-badge"></i> Empleados</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios?action=listar"><i class="bi bi-person-circle"></i> Usuarios</a></li>
                            </ul>
                        </li>

                        <!-- Gestiones -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                                <i class="bi bi-gear-fill"></i> Gestiones
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/vehiculos?action=listar"><i class="bi bi-car-front-fill"></i> Vehículos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ordenes?action=listar"><i class="bi bi-receipt-cutoff"></i> Órdenes de trabajo</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/pagos?action=listar"><i class="bi bi-cash-stack"></i> Pagos y facturación</a></li>
                            </ul>
                        </li>

                        <!-- Reportes -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                                <i class="bi bi-clipboard-data-fill"></i> Reportes
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/servicios.jsp"><i class="bi bi-wrench-adjustable-circle"></i> Servicios</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/ingresos.jsp"><i class="bi bi-graph-up"></i> Ingresos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/repuestos.jsp"><i class="bi bi-tools"></i> Repuestos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/vehiculos.jsp"><i class="bi bi-truck-front"></i> Vehículos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ordenes?action=listar"><i class="bi bi-journal-text"></i> Órdenes</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/clientes?action=frecuentes.jsp"><i class="bi bi-star-fill"></i> Clientes frecuentes</a></li>
                            </ul>
                        </li>

                        <!-- Catálogos -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                                <i class="bi bi-collection-fill"></i> Catálogos
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/proveedores?action=listar"><i class="bi bi-building"></i> Proveedores</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/repuestos?action=listar"><i class="bi bi-tools"></i> Repuestos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/marcas?action=listar"><i class="bi bi-tags-fill"></i> Marcas de vehículos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/tipos?action=listar"><i class="bi bi-car-front"></i>  Tipos de vehículos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/servicios?action=listar"><i class="bi bi-wrench"></i> Servicios</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Estilos internos -->
        <style>
            .navbar {
                font-size: 16px;
                font-weight: 500;
            }
            .nav-link {
                color: #fff !important;
                padding: 8px 15px;
                transition: 0.3s;
            }
            .nav-link:hover {
                background-color: #456882;
                border-radius: 5px;
            }
            .dropdown-menu {
                background-color: #456882;
                border-radius: 8px;
                margin-top: 5px;
                max-height: 300px;
                overflow-y: auto;
            }
            .dropdown-item {
                color: #fff !important;
                transition: 0.3s;
            }
            .dropdown-item i {
                margin-right: 8px;
            }
            .dropdown-item:hover {
                background-color: #2f5473 !important;
            }
            body {
                overflow-x: hidden;
            }
        </style>

        <br>

        <div class="container mt-5">

            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/gestion-taller-autos">Inicio</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Clientes</li>
                </ol>
            </nav>

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2 class="mb-0">Catálogo de Clientes</h2>

                <a href="${pageContext.request.contextPath}/clientes?action=crear" 
                   class="btn btn-primary">
                    <i class="bi bi-plus-circle me-1"></i> Crear
                </a>
            </div>       
            <br>

            <div class="table-responsive">
                <table class="table table-bordered border-dark-subtle table-striped table-hover" id="tablaClientes">
                    <thead class="" >
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Documento</th>
                            <th>Tipo de documento</th>
                            <th>Dirección</th>
                            <th>Telefono</th>
                            <th>Email</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <c:forEach var="c" items="${clientes}">
                            <tr>
                                <td>${c.idCliente}</td>
                                <td>${c.nombre}</td>
                                <td>${c.apellido}</td>
                                <td>${c.documento}</td>
                                <td>${c.tipoDocumento}</td>
                                <td>${c.direccion}</td>
                                <td>${c.telefono}</td>
                                <td>${c.email}</td>
                                <td class="text-nowrap">
                                    <a href="${pageContext.request.contextPath}/vehiculos?action=verPorCliente&idCliente=${c.idCliente}" 
                                       class="btn btn-sm btn-info">
                                        <i class="bi bi-car-front"></i>
                                    </a>

                                    <a href="${pageContext.request.contextPath}/servicios?action=verPorCliente&idCliente=${c.idCliente}" 
                                       class="btn btn-sm btn-secondary">
                                        <i class="bi bi-wrench"></i>
                                    </a>

                                    <!-- Botón Editar -->
                                    <form action="${pageContext.request.contextPath}/clientes" 
                                          method="post" 
                                          class="d-inline">

                                        <input type="hidden" name="action" value="editar">
                                        <input type="hidden" name="idCliente" value="${c.idCliente}">

                                        <button type="submit" class="btn btn-sm btn-warning">
                                            <i class="bi bi-pencil-square"></i>
                                        </button>
                                    </form>

                                    <!-- Botón Eliminar -->
                                    <form action="${pageContext.request.contextPath}/clientes" method="post" class="d-inline" 
                                          onsubmit="return confirm('¿Estás seguro de eliminar este cliente?');"> 
                                        <input type="hidden" name="action" value="eliminar"> 
                                        <input type="hidden" name="idCliente" value="${c.idCliente}"> 
                                        <button type="submit" class="btn btn-sm btn-danger"> 
                                            <i class="bi bi-trash"></i> 
                                        </button> 
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>

    </body>
    <script>
        $(document).ready(function () {
            $('#tablaClientes').DataTable({
                responsive: true,
                autoWidth: false
            });
        });
    </script>
</html>