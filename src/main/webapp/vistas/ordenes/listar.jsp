<%-- 
    Document   : listar
    Created on : 19 nov 2025, 3:38:40 p. m.
    Author     : fuent
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Órdenes de Trabajo - Taller</title>
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
            .table {
                font-size: 14px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Órdenes de Trabajo</h2>

            <c:if test="${not empty sessionScope.mensaje}">
                <div class="alert alert-success">
                    ${sessionScope.mensaje}
                </div>
                <c:remove var="mensaje" scope="session"/>
            </c:if>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>

            <a href="${pageContext.request.contextPath}/orden-trabajo?action=listar"
               class="btn btn-primary mb-3">
                + Nueva Orden de Trabajo
            </a>


            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Nº Orden</th>
                        <th>Cliente</th>
                        <th>Vehículo</th>
                        <th>Fecha Ingreso</th>
                        <th>Mecánico</th>
                        <th>Estado</th>
                        <th>Total</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty listaOrdenes}">
                            <tr>
                                <td colspan="8" class="text-center">
                                    No hay órdenes de trabajo registradas
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="orden" items="${listaOrdenes}">
                                <tr>
                                    <td><strong>#${orden.idOrden}</strong></td>
                                    <td>${orden.nombreCliente}</td>
                                    <td>${orden.placaVehiculo} - ${orden.marcaVehiculo} ${orden.modeloVehiculo}</td>
                                    <td><fmt:formatDate value="${orden.fechaIngreso}" pattern="dd/MM/yyyy HH:mm" /></td>
                                    <td>${orden.nombreEmpleado != null ? orden.nombreEmpleado : 'Sin asignar'}</td>
                                    <td>
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
                                            <c:otherwise>
                                                <span class="badge bg-secondary">${orden.estado}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><strong>$<fmt:formatNumber value="${orden.totalGeneral}" pattern="#,##0.00" /></strong></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/orden-trabajo?action=ver&id=${orden.idOrden}"
                                           class="btn btn-sm btn-info">Ver</a>
                                        <a href="${pageContext.request.contextPath}/orden-trabajo?action=editar&id=${orden.idOrden}"
                                           class="btn btn-sm btn-warning">Editar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <div class="mt-3">
                <a href="${pageContext.request.contextPath}/dashboard.jsp" class="btn btn-secondary">
                    Volver al Inicio
                </a>
            </div>
        </div>
    </body>
</html>