<%-- 
    Document   : dashboard
    Created on : Oct 19, 2025, 7:46:54 PM
    Author     : Eduar Medrano
--%>

<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Sistema de Gestión de taller de autos y motos</title>
        <link rel="stylesheet" href="bs/css/estilo.css">
        <!-- <link rel="stylesheet" href="../../bs/fonts/iconos.css"> -->
        <script src="bs/js/accion.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <!-- Menú principal -->
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#1B3C53; position: sticky; top: 0; z-index: 1000;">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp" style="color:#fff; font-weight:bold;">
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
                                <% if (permisos == null || permisos.contains("CLIENTE_VER")) { %>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/clientes?action=listar">
                                        <i class="bi bi-person-check-fill"></i> Clientes
                                    </a>
                                </li>
                                <% } %>

                                <% if (permisos == null || permisos.contains("EMPLEADO_VER")) { %>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados?action=listar">
                                        <i class="bi bi-person-badge"></i> Empleados
                                    </a>
                                </li>
                                <% } %>

                                <% if (permisos == null || permisos.contains("USUARIO_VER")) { %>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios?action=listar">
                                        <i class="bi bi-person-circle"></i> Usuarios
                                    </a>
                                </li>
                                <% } %>
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
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/vistas/reportes/ReporteServicio.jsp"><i class="bi bi-wrench-adjustable-circle"></i> Servicios</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/ingresos.jsp"><i class="bi bi-graph-up"></i> Ingresos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/repuestos.jsp"><i class="bi bi-tools"></i> Repuestos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/vehiculos.jsp"><i class="bi bi-truck-front"></i> Vehículos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/ordenes.jsp"><i class="bi bi-journal-text"></i> Órdenes</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/clientesFrecuentes.jsp"><i class="bi bi-star-fill"></i> Clientes frecuentes</a></li>
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
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                                <i class="bi bi-person-circle"></i>
                                <span class="ms-1">
                                    <%= usuarioLogueado.getNombreUsuario()%>
                                </span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">
                                        <i class="bi bi-box-arrow-right"></i> Cerrar sesión
                                    </a>
                                </li>
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
            .card {
                border: none;
                border-radius: 1rem;
                box-shadow: 0 0.125rem 0.35rem rgba(0,0,0,.05);
            }
            .card-title {
                font-size: 0.95rem;
                font-weight: 600;
                color: #6c757d;
            }
        </style>

        <br>
        <div class="container mt-5">
            <div class="alert alert-primary" role="alert">  
                <h2>¡Bienvenido <%= usuarioLogueado.getNombreUsuario()%>!</h2>
            </div>
            <h3>Comprobando la conexión</h3>
        </div>
        <%
            Connection conn = ConexionDB.conectar();
            if (conn != null) {
        %>
        <div class="container mt-5">
            <div class="alert alert-success" role="alert">  
                <h2>Conexión exitosa</h2>
            </div>
        </div>
        <%
            conn.close();
        } else {
        %>
        <div class="container mt-5">
            <div class="alert alert-danger" role="alert">
                <h2>Error en la conexión</h2>
            </div>
        </div>
        <%
            }
        %>
        <div class="container py-4">
            <h2 class="mb-4">Dashboard Taller Automotriz</h2>
            <div class="row g-4 mb-2">

                <!-- Ventas por mes -->
                <div class="col-lg-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <div>
                                    <h5 class="card-title mb-1">Ventas mensuales</h5>
                                    <small id="lblAnioVentas" class="text-muted"></small>
                                </div>
                            </div>
                            <div style="height:260px;">
                                <canvas id="chartVentasMes"></canvas>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Top servicios-->
                <div class="col-lg-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h5 class="card-title mb-0">Top 5 servicios</h5>
                            </div>
                            <div style="height:260px;">
                                <canvas id="chartTopServicios"></canvas>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="row g-4 mb-2">
                <!-- Clientes por mes -->
                <div class="col-lg-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h5 class="card-title mb-0">Clientes registrados por mes</h5>
                            </div>
                            <div style="height:260px;">
                                <canvas id="chartClientesMes"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Ingreso de categoria de servicio -->
                <div class="col-lg-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h5 class="card-title mb-0">Grafico de ingreso por categoría de servicio</h5>
                            </div>
                            <div style="height:260px;">
                                <canvas id="chartCategoria"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {

                const ctxVentasMes = document.getElementById("chartVentasMes");
                const ctxTopServicios = document.getElementById("chartTopServicios");
                const ctxClientesMes = document.getElementById("chartClientesMes");

                const base = "<%=request.getContextPath()%>/dashboard";

                /* Ventas por mes */
                fetch(base + "/ventas-mes")
                        .then(r => r.json())
                        .then(datos => {
                            const labels = datos.map(d => d.mesNombre);
                            const values = datos.map(d => d.total);

                            new Chart(ctxVentasMes, {
                                type: "line",
                                data: {
                                    labels: labels,
                                    datasets: [{
                                            label: "Ventas",
                                            data: values,
                                            tension: 0.5
                                        }]
                                }
                            });
                        });

                /* Top servicios */
                fetch(base + "/top-servicios")
                        .then(r => r.json())
                        .then(datos => {
                            const labels = datos.map(d => d.servicio);
                            const values = datos.map(d => d.total);

                            new Chart(ctxTopServicios, {
                                type: "doughnut",
                                data: {
                                    labels: labels,
                                    datasets: [{
                                            data: values,
                                            backgroundColor: [
                                                "#03045e",
                                                "#0077b6",
                                                "#00b4d8",
                                                "#90e0ef",
                                                "#caf0f8"
                                            ]
                                        }]
                                },
                                options: {
                                    responsive: true,
                                    maintainAspectRatio: false,
                                    layout: {
                                        padding: {
                                            top: 30,
                                            bottom: 30
                                        }
                                    },
                                    plugins: {
                                        legend: {
                                            position: "bottom"
                                        }
                                    }}
                            });
                        });

                /* Clientes por mes */
                fetch(base + "/clientes-mes")
                        .then(r => r.json())
                        .then(datos => {
                            const labels = datos.map(d => d.mesNombre);
                            const values = datos.map(d => d.cantidad);

                            new Chart(ctxClientesMes, {
                                type: "bar",
                                data: {
                                    labels: labels,
                                    datasets: [{
                                            label: "Clientes",
                                            data: values
                                        }]
                                },
                                options: {
                                    indexAxis: 'y',
                                    scales: {
                                        x: {
                                            beginAtZero: true,
                                            ticks: {
                                                stepSize: 10
                                            }
                                        }
                                    }
                                }
                            });
                        });
                /* Ingresos por categoría */
                fetch(base + "/ingresos-categoria")
                        .then(r => r.json())
                        .then(datos => {
                            console.log("categorias:", datos);
                            const labels = datos.map(d => d.categoria);
                            const values = datos.map(d => d.total);

                            new Chart(document.getElementById("chartCategoria"), {
                                type: "bar",
                                data: {
                                    labels: labels,
                                    datasets: [{
                                            label: "Ingresos por categoría",
                                            data: values,
                                            backgroundColor: "#0077b6"
                                        }]
                                },
                                options: {
                                    responsive: true,
                                    plugins: {
                                        legend: {display: false}
                                    }
                                }
                            });
                        });

            });
        </script>
    </body>
</html>