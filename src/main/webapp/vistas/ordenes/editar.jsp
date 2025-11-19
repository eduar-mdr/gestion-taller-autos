<%-- 
    Document   : editar
    Created on : 19 nov 2025, 3:48:52 p. m.
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
    <title>Editar Orden #${orden.idOrden} - Taller</title>
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
            max-width: 900px;
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
        <h2>Editar Orden de Trabajo #${orden.idOrden}</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>
        
        <!-- Información del cliente y vehículo (solo lectura) -->
        <div class="info-box">
            <h5>Información (No editable)</h5>
            <p><strong>Cliente:</strong> ${orden.nombreCliente}</p>
            <p><strong>Vehículo:</strong> ${orden.placaVehiculo} - ${orden.marcaVehiculo} ${orden.modeloVehiculo}</p>
            <p class="mb-0"><strong>Fecha de Ingreso:</strong> 
                <fmt:formatDate value="${orden.fechaIngreso}" pattern="dd/MM/yyyy HH:mm" />
            </p>
        </div>
        
        <form action="orden-trabajo" method="post">
            <input type="hidden" name="action" value="actualizar">
            <input type="hidden" name="idOrden" value="${orden.idOrden}">
            
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Estado *</label>
                    <select class="form-select" name="estado" required>
                        <option value="Pendiente" ${orden.estado == 'Pendiente' ? 'selected' : ''}>Pendiente</option>
                        <option value="En Proceso" ${orden.estado == 'En Proceso' ? 'selected' : ''}>En Proceso</option>
                        <option value="Finalizada" ${orden.estado == 'Finalizada' ? 'selected' : ''}>Finalizada</option>
                        <option value="Entregada" ${orden.estado == 'Entregada' ? 'selected' : ''}>Entregada</option>
                        <option value="Cancelada" ${orden.estado == 'Cancelada' ? 'selected' : ''}>Cancelada</option>
                    </select>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Mecánico Asignado</label>
                    <input type="number" class="form-control" name="idEmpleado" 
                           value="${orden.idEmpleado > 0 ? orden.idEmpleado : ''}">
                    <small class="text-muted">ID del mecánico</small>
                </div>
            </div>
            
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Tiempo Estimado (horas)</label>
                    <input type="number" class="form-control" name="tiempoEstimado" 
                           step="0.5" min="0" value="${orden.tiempoEstimado}">
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Fecha Estimada de Entrega</label>
                    <input type="datetime-local" class="form-control" name="fechaEntrega"
                           value="<fmt:formatDate value='${orden.fechaEntrega}' pattern='yyyy-MM-dd\'T\'HH:mm' />">
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Diagnóstico *</label>
                <textarea class="form-control" name="diagnostico" rows="4" required 
                          maxlength="300">${orden.diagnostico}</textarea>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Observaciones</label>
                <textarea class="form-control" name="observaciones" rows="3" 
                          maxlength="300">${orden.observaciones}</textarea>
            </div>
            
            <div class="mb-3">
                <label class="form-label">URL de Documentos</label>
                <input type="text" class="form-control" name="documentosUrl" 
                       value="${orden.documentosUrl}">
                <small class="text-muted">Link a documentos escaneados</small>
            </div>
            
            <div class="text-end">
                <a href="orden-trabajo?action=ver&id=${orden.idOrden}" class="btn btn-secondary">Cancelar</a>
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </div>
        </form>
    </div>
</body>
</html>