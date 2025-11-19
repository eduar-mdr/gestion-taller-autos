<%-- 
    Document   : detalle
    Created on : 19 nov 2025, 3:47:33 p. m.
    Author     : fuent
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <meta charset="UTF-8">
        <title>Orden #${orden.idOrden} - Taller</title>
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>

        <!-- Datatables and Jquery -->
        <link  rel="stylesheet" href="datatables/datatables.css"/>
        <script src="datatables/jquery.js"></script>
        <script src="datatables/datatables.js"></script>
        <style>
            body {
                background-color: #f5f5f5;
                padding: 20px;
            }
            .container {
                background-color: white;
                padding: 30px;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }
            h2 {
                color: #333;
                border-bottom: 3px solid #007bff;
                padding-bottom: 10px;
                margin-bottom: 20px;
            }
            .info-box {
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                padding: 15px;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .total-box {
                background-color: #e7f3ff;
                border: 2px solid #007bff;
                padding: 15px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body> <!-- Menú principal -->
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

        <div class="container">
            <h2>Orden de Trabajo #${orden.idOrden}</h2>

            <c:if test="${not empty sessionScope.mensaje}">
                <div class="alert alert-success">
                    ${sessionScope.mensaje}
                </div>
                <c:remove var="mensaje" scope="session"/>
            </c:if>

            <!-- Información General -->
            <div class="row mb-4">
                <div class="col-md-6">
                    <div class="info-box">
                        <h5>Información del Cliente</h5>
                        <p><strong>Nombre:</strong> ${orden.nombreCliente}</p>
                        <p><strong>Teléfono:</strong> ${orden.telefonoCliente}</p>
                        <p class="mb-0"><strong>ID Cliente:</strong> ${orden.idCliente}</p>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="info-box">
                        <h5>Información del Vehículo</h5>
                        <p><strong>Placa:</strong> ${orden.placaVehiculo}</p>
                        <p><strong>Marca/Modelo:</strong> ${orden.marcaVehiculo} ${orden.modeloVehiculo}</p>
                        <p class="mb-0"><strong>ID Vehículo:</strong> ${orden.idVehiculo}</p>
                    </div>
                </div>
            </div>

            <!-- Información de la Orden -->
            <div class="info-box">
                <h5>Datos de la Orden</h5>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Fecha de Ingreso:</strong> 
                            <fmt:formatDate value="${orden.fechaIngreso}" pattern="dd/MM/yyyy HH:mm" />
                        </p>
                        <p><strong>Fecha Estimada de Entrega:</strong> 
                            <c:choose>
                                <c:when test="${orden.fechaEntrega != null}">
                                    <fmt:formatDate value="${orden.fechaEntrega}" pattern="dd/MM/yyyy HH:mm" />
                                </c:when>
                                <c:otherwise>No definida</c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Mecánico Asignado:</strong> 
                            ${orden.nombreEmpleado != null ? orden.nombreEmpleado : 'Sin asignar'}
                        </p>
                        <p><strong>Tiempo Estimado:</strong> 
                            ${orden.tiempoEstimado != null ? orden.tiempoEstimado : 'N/A'} horas
                        </p>
                    </div>
                </div>
                <p><strong>Estado:</strong> 
                    <c:choose>
                        <c:when test="${orden.estado == 'Pendiente'}">
                            <span class="badge bg-warning">Pendiente</span>
                        </c:when>
                        <c:when test="${orden.estado == 'En Proceso'}">
                            <span class="badge bg-info">En Proceso</span>
                        </c:when>
                        <c:when test="${orden.estado == 'Finalizada'}">
                            <span class="badge bg-success">Finalizada</span>
                        </c:when>
                        <c:when test="${orden.estado == 'Entregada'}">
                            <span class="badge bg-primary">Entregada</span>
                        </c:when>
                        <c:when test="${orden.estado == 'Cancelada'}">
                            <span class="badge bg-danger">Cancelada</span>
                        </c:when>
                    </c:choose>
                </p>
                <p><strong>Diagnóstico:</strong></p>
                <p class="text-muted">${orden.diagnostico}</p>

                <c:if test="${not empty orden.observaciones}">
                    <p><strong>Observaciones:</strong></p>
                    <p class="text-muted">${orden.observaciones}</p>
                </c:if>

                <c:if test="${not empty orden.documentosUrl}">
                    <p><strong>Documentos:</strong> 
                        <a href="${orden.documentosUrl}" target="_blank">Ver documentos</a>
                    </p>
                </c:if>
            </div>

            <!-- Servicios Realizados -->
            <h4 class="mt-4">Servicios Realizados (Mano de Obra)</h4>
            <a href="orden-trabajo?action=agregarServicio&id=${orden.idOrden}" class="btn btn-sm btn-success mb-2">
                + Agregar Servicio
            </a>

            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Servicio</th>
                        <th>Descripción</th>
                        <th>Precio Unit.</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty servicios}">
                            <tr>
                                <td colspan="6" class="text-center">No hay servicios registrados</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="servicio" items="${servicios}">
                                <tr>
                                    <td>${servicio.nombreServicio}</td>
                                    <td>${servicio.descripcionServicio}</td>
                                    <td>$<fmt:formatNumber value="${servicio.precioServicio}" pattern="#,##0.00" /></td>
                                    <td>${servicio.cantidad}</td>
                                    <td><strong>$<fmt:formatNumber value="${servicio.subtotal}" pattern="#,##0.00" /></strong></td>
                                    <td>
                                        <form action="orden-trabajo" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="eliminarServicio">
                                            <input type="hidden" name="idDetalle" value="${servicio.idDetalleServicio}">
                                            <input type="hidden" name="idOrden" value="${orden.idOrden}">
                                            <button type="submit" class="btn btn-sm btn-danger" 
                                                    onclick="return confirm('¿Eliminar este servicio?')">
                                                Eliminar
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <!-- Repuestos Utilizados -->
            <h4 class="mt-4">Repuestos Utilizados</h4>
            <a href="orden-trabajo?action=agregarRepuesto&id=${orden.idOrden}" class="btn btn-sm btn-success mb-2">
                + Agregar Repuesto
            </a>

            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Repuesto</th>
                        <th>Descripción</th>
                        <th>Precio Unit.</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty repuestos}">
                            <tr>
                                <td colspan="6" class="text-center">No hay repuestos registrados</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="repuesto" items="${repuestos}">
                                <tr>
                                    <td>${repuesto.nombreRepuesto}</td>
                                    <td>${repuesto.descripcionRepuesto}</td>
                                    <td>$<fmt:formatNumber value="${repuesto.precioRepuesto}" pattern="#,##0.00" /></td>
                                    <td>${repuesto.cantidad}</td>
                                    <td><strong>$<fmt:formatNumber value="${repuesto.subtotal}" pattern="#,##0.00" /></strong></td>
                                    <td>
                                        <form action="orden-trabajo" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="eliminarRepuesto">
                                            <input type="hidden" name="idDetalle" value="${repuesto.idDetalleRepuesto}">
                                            <input type="hidden" name="idOrden" value="${orden.idOrden}">
                                            <button type="submit" class="btn btn-sm btn-danger" 
                                                    onclick="return confirm('¿Eliminar este repuesto?')">
                                                Eliminar
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <!-- Totales -->
            <div class="total-box mt-4">
                <h4>Resumen de Costos</h4>
                <table class="table table-sm mb-0">
                    <tr>
                        <td><strong>Total Mano de Obra:</strong></td>
                        <td class="text-end">$<fmt:formatNumber value="${orden.totalManoObra}" pattern="#,##0.00" /></td>
                    </tr>
                    <tr>
                        <td><strong>Total Repuestos:</strong></td>
                        <td class="text-end">$<fmt:formatNumber value="${orden.totalRepuestos}" pattern="#,##0.00" /></td>
                    </tr>
                    <tr class="table-active">
                        <td><strong>TOTAL GENERAL:</strong></td>
                        <td class="text-end"><strong class="fs-5">$<fmt:formatNumber value="${orden.totalGeneral}" pattern="#,##0.00" /></strong></td>
                    </tr>
                </table>
            </div>

            <!-- Botones de Acción -->
            <div class="mt-4">
                <a href="orden-trabajo?action=listar" class="btn btn-secondary">Volver a Lista</a>
                <a href="orden-trabajo?action=editar&id=${orden.idOrden}" class="btn btn-warning">Editar Orden</a>
                <a href="PagoServlet?action=nuevo&idOrden=${orden.idOrden}" class="btn btn-success">Registrar Pago</a>
            </div>
        </div>
    </body>
</html>
