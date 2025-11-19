<%-- 
    Document   : repuestos-uso.jsp
    Author     : fuent
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.Set"%>

<%
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuarioLogueado == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    Set<String> permisos = (Set<String>) session.getAttribute("permisosUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reporte de Repuestos más Utilizados</title>

    <link rel="stylesheet" href="bs/css/estilo.css">
    <link rel="stylesheet" href="bs/fonts/iconos.css">
    <script src="bs/js/accion.js"></script>

    <style>
        body {
            background-color: #f4f4f4;
            padding: 20px 0;
            overflow-x: hidden;
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
            text-align: center;
            margin-bottom: 30px;
            border-bottom: 3px solid #007bff;
            padding-bottom: 10px;
        }

        .filtros {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 6px;
            margin-bottom: 30px;
        }

        .form-group {
            display: inline-block;
            margin-right: 20px;
            margin-bottom: 10px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
            color: #444;
        }

        select, input[type="date"] {
            padding: 7px 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .btn {
            padding: 9px 18px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
            font-size: 14px;
        }

        .btn-primary { background-color: #007bff; color: white; }
        .btn-primary:hover { background-color: #0056b3; }

        .btn-print { background-color: #28a745; color: white; }
        .btn-print:hover { background-color: #1e7e34; }

        .btn-pdf { background-color: #dc3545; color: white; }
        .btn-pdf:hover { background-color: #c82333; }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th {
            background-color: #007bff;
            color: white;
            padding: 12px;
            text-align: left;
        }

        td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        tr:hover { background-color: #f2f2f2; }

        .text-right { text-align: right; }

        .resumen {
            background-color: #fff3cd;
            border-left: 4px solid #ffc107;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
        }

        .no-data {
            padding: 30px;
            text-align: center;
            font-style: italic;
            color: #777;
        }

        @media print {
            .filtros, nav.navbar, .btn {
                display: none;
            }
            body { padding: 0; background: white; }
            .container { box-shadow: none; margin: 0; }
        }

        /* Navbar estilos iguales a los demás reportes */
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
        }
        .dropdown-item { color: #fff !important; }
        .dropdown-item:hover { background-color: #2f5473; }
    </style>
</head>

<body>

    <!-- NAVBAR igual que los demás reportes -->
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#1B3C53;">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                <i class="bi bi-house-door-fill"></i> Taller Mecánico
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="menuNav">
                <ul class="navbar-nav ms-auto">

                    <!-- Personas -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                            Personas
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/clientes?action=listar">Clientes</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/empleados?action=listar">Empleados</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios?action=listar">Usuarios</a></li>
                        </ul>
                    </li>

                    <!-- Gestiones -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Gestiones</a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/vehiculos?action=listar">Vehículos</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/orden-trabajo?action=listar">Órdenes</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/pagos?action=listar">Pagos</a></li>
                        </ul>
                    </li>

                    <!-- Reportes -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Reportes</a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reporte-servicio">Servicios</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ingresos">Ingresos</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reporte-repuesto">Repuestos más usados</a></li>
                        </ul>
                    </li>

                    <!-- Usuario -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                            <i class="bi bi-person-circle"></i> <%= usuarioLogueado.getNombreUsuario() %>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
        </div>
    </nav>

    <br>

    <div class="container">
        <h1>Reporte de Repuestos más Utilizados</h1>

        <!-- FILTROS -->
        <div class="filtros">
            <form action="${pageContext.request.contextPath}/reporte-repuesto" method="get">
                <input type="hidden" name="accion" value="generar"/>

                <div class="form-group">
                    <label>Proveedor:</label>
                    <select name="idProveedor">
                        <option value="">-- Todos --</option>
                        <c:forEach var="p" items="${proveedores}">
                            <option value="${p.idProveedor}"
                                ${idProveedorSeleccionado == p.idProveedor ? 'selected' : ''}>
                                ${p.nombre}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Fecha Inicio:</label>
                    <input type="date" name="fechaInicio" value="${fechaInicio}">
                </div>

                <div class="form-group">
                    <label>Fecha Fin:</label>
                    <input type="date" name="fechaFin" value="${fechaFin}">
                </div>

                <div class="form-group" style="margin-top: 24px;">
                    <button type="submit" class="btn btn-primary">Generar</button>
                </div>

                <button type="button" class="btn btn-print" onclick="window.print()">🖨️ Imprimir</button>
            </form>

            <!-- Botón PDF -->
            <c:if test="${not empty reportes}">
                <form action="${pageContext.request.contextPath}/reporte-repuesto" method="get" style="display:inline;">
                    <input type="hidden" name="accion" value="pdf"/>
                    <input type="hidden" name="idProveedor" value="${idProveedorSeleccionado}"/>
                    <input type="hidden" name="fechaInicio" value="${fechaInicio}"/>
                    <input type="hidden" name="fechaFin" value="${fechaFin}"/>
                    <button type="submit" class="btn btn-pdf">📄 PDF</button>
                </form>
            </c:if>
        </div>

        <!-- RESUMEN -->
        <c:if test="${not empty reportes}">
            <div class="resumen">
                <strong>Período:</strong>
                <c:choose>
                    <c:when test="${not empty fechaInicio}">
                        ${fechaInicio} al ${fechaFin}
                    </c:when>
                    <c:otherwise>
                        Todos los registros
                    </c:otherwise>
                </c:choose>

                |
                <strong>Total Repuestos:</strong> ${reportes.size()}

                |
                <strong>Cantidad Total Usada:</strong> ${totalCantidad}

                |
                <strong>Consumo Total:</strong>
                $<fmt:formatNumber value="${totalConsumo}" pattern="#,##0.00"/>
            </div>

            <!-- TABLA -->
            <table>
                <thead>
                    <tr>
                        <th>Repuesto</th>
                        <th>Proveedor</th>
                        <th class="text-right">Cantidad usada</th>
                        <th class="text-right">Consumo total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${reportes}">
                        <tr>
                            <td>${item.repuesto}</td>
                            <td>${item.proveedor}</td>
                            <td class="text-right">${item.cantidadUsada}</td>
                            <td class="text-right">
                                $<fmt:formatNumber value="${item.consumoTotal}" pattern="#,##0.00"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- NO DATA -->
        <c:if test="${empty reportes && param.accion == 'generar'}">
            <div class="no-data">No se encontraron repuestos para los filtros seleccionados.</div>
        </c:if>

    </div>

</body>
</html>
