<%-- 
    Document   : reporte
    Created on : 19 nov 2025, 11:03:48 a. m.
    Author     : fuent
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.sql.Connection" %>
<%@page import="conexion.ConexionDB" %>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.Set"%>
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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reporte de Ingresos - Taller</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            }

            h1 {
                color: #333;
                margin-bottom: 30px;
                text-align: center;
                border-bottom: 3px solid #007bff;
                padding-bottom: 10px;
            }

            .filtros {
                background-color: #f8f9fa;
                padding: 20px;
                border-radius: 5px;
                margin-bottom: 30px;
            }

            .form-group {
                display: inline-block;
                margin-right: 20px;
                margin-bottom: 10px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #555;
            }

            select, input[type="date"] {
                padding: 8px 12px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
            }

            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 14px;
                transition: background-color 0.3s;
            }

            .btn-primary {
                background-color: #007bff;
                color: white;
            }

            .btn-primary:hover {
                background-color: #0056b3;
            }

            .btn-print {
                background-color: #28a745;
                color: white;
                float: right;
            }

            .btn-print:hover {
                background-color: #218838;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #007bff;
                color: white;
                font-weight: bold;
            }

            tr:hover {
                background-color: #f5f5f5;
            }

            .tipo-servicio {
                display: inline-block;
                padding: 4px 8px;
                border-radius: 3px;
                font-size: 12px;
                font-weight: bold;
            }

            .tipo-servicio.servicio {
                background-color: #d4edda;
                color: #155724;
            }

            .tipo-servicio.repuesto {
                background-color: #d1ecf1;
                color: #0c5460;
            }

            .total-row {
                font-weight: bold;
                background-color: #e9ecef;
                font-size: 16px;
            }

            .text-right {
                text-align: right;
            }

            .no-data {
                text-align: center;
                padding: 40px;
                color: #999;
                font-style: italic;
            }

            .resumen {
                margin-top: 20px;
                padding: 15px;
                background-color: #fff3cd;
                border-left: 4px solid #ffc107;
                border-radius: 4px;
            }

            @media print {
                .filtros, .btn {
                    display: none;
                }

                body {
                    padding: 0;
                }

                .container {
                    box-shadow: none;
                }
            }
        </style>
    </head>
    <body>
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
                                <% if (permisos == null || permisos.contains("VEHICULO_VER")) { %>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/vehiculos?action=listar">
                                        <i class="bi bi-car-front-fill"></i> Vehículos
                                    </a>
                                </li>
                                <% } %>

                                <% if (permisos == null || permisos.contains("ORDEN_VER")) { %>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/ordenes?action=listar">
                                        <i class="bi bi-receipt-cutoff"></i> Órdenes de trabajo
                                    </a>
                                </li>
                                <% } %>

                                <% if (permisos == null || permisos.contains("PAGO_VER")) { %>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/pagos?action=listar">
                                        <i class="bi bi-cash-stack"></i> Pagos y Facturación
                                    </a>
                                </li>
                                <% } %>
                            </ul>
                        </li>
                        <!-- Reportes -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                                <i class="bi bi-clipboard-data-fill"></i> Reportes
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reportes/servicios.jsp"><i class="bi bi-wrench-adjustable-circle"></i> Servicios</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ingresos"><i class="bi bi-graph-up"></i> Ingresos</a></li>
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
        <div class="container">
            <h1>Reporte de Ingresos por Servicio/Repuesto</h1>

            <!-- Formulario de Filtros -->
            <div class="filtros">
                <form action="${pageContext.request.contextPath}/ingresos" method="get">
                    <input type="hidden" name="accion" value="generar">

                    <div class="form-group">
                        <label for="tipoReporte">Tipo de Reporte:</label>
                        <select name="tipoReporte" id="tipoReporte">
                            <option value="todos" ${tipoReporte == 'todos' ? 'selected' : ''}>Todos</option>
                            <option value="servicios" ${tipoReporte == 'servicios' ? 'selected' : ''}>Solo Servicios</option>
                            <option value="repuestos" ${tipoReporte == 'repuestos' ? 'selected' : ''}>Solo Repuestos</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="fechaInicio">Fecha Inicio:</label>
                        <input type="date" name="fechaInicio" id="fechaInicio" value="${fechaInicio}">
                    </div>

                    <div class="form-group">
                        <label for="fechaFin">Fecha Fin:</label>
                        <input type="date" name="fechaFin" id="fechaFin" value="${fechaFin}">
                    </div>

                    <div class="form-group" style="margin-top: 24px;">
                        <button type="submit" class="btn btn-primary">Generar Reporte</button>
                    </div>

                    <button type="button" class="btn btn-print" onclick="window.print()">🖨️ Imprimir</button>
                </form>

                <!-- Botón para exportar a PDF -->
                <c:if test="${not empty reportes}">
                    <form action="${pageContext.request.contextPath}/ingresos" method="get" style="display: inline;">
                        <input type="hidden" name="accion" value="pdf">
                        <input type="hidden" name="tipoReporte" value="${tipoReporte}">
                        <input type="hidden" name="fechaInicio" value="${fechaInicio}">
                        <input type="hidden" name="fechaFin" value="${fechaFin}">
                        <button type="submit" class="btn btn-pdf">📄 Exportar a PDF</button>
                    </form>
                </c:if>
            </div>

            <style>
                .btn-pdf {
                    background-color: #dc3545;
                    color: white;
                    margin-left: 10px;
                }

                .btn-pdf:hover {
                    background-color: #c82333;
                }

            </style>

            <!-- Tabla de Resultados -->
            <c:if test="${not empty reportes}">
                <div class="resumen">
                    <strong>Período:</strong> 
                    <c:choose>
                        <c:when test="${not empty fechaInicio and not empty fechaFin}">
                            ${fechaInicio} al ${fechaFin}
                        </c:when>
                        <c:otherwise>
                            Todos los registros
                        </c:otherwise>
                    </c:choose>
                    | <strong>Total de items:</strong> ${reportes.size()}
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Tipo</th>
                            <th>Nombre</th>
                            <th>Categoría/Proveedor</th>
                            <th class="text-right">Cantidad Vendida</th>
                            <th class="text-right">Precio Unitario</th>
                            <th class="text-right">Ingreso Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reportes}" var="item">
                            <tr>
                                <td>
                                    <span class="tipo-servicio ${item.tipo == 'Servicio' ? 'servicio' : 'repuesto'}">
                                        ${item.tipo}
                                    </span>
                                </td>
                                <td>${item.nombre}</td>
                                <td>${item.categoria != null ? item.categoria : '-'}</td>
                                <td class="text-right">${item.cantidadVendida}</td>
                                <td class="text-right">$<fmt:formatNumber value="${item.precioUnitario}" pattern="#,##0.00"/></td>
                                <td class="text-right">$<fmt:formatNumber value="${item.ingresoTotal}" pattern="#,##0.00"/></td>
                            </tr>
                        </c:forEach>

                        <!-- Fila de Total -->
                        <tr class="total-row">
                            <td colspan="5" class="text-right">TOTAL GENERAL:</td>
                            <td class="text-right">$<fmt:formatNumber value="${totalGeneral}" pattern="#,##0.00"/></td>
                        </tr>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty reportes and not empty param.accion}">
                <div class="no-data">
                    No se encontraron datos para el período seleccionado.
                </div>
            </c:if>

            <c:if test="${empty reportes and empty param.accion}">
                <div class="no-data">
                    Seleccione los filtros y haga clic en "Generar Reporte" para ver los resultados.
                </div>
            </c:if>
        </div>
    </body>
</html>
