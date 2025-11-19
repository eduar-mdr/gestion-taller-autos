<%-- 
    Document   : detalle
    Created on : 19 nov 2025, 3:47:33 p. m.
    Author     : fuent
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <body>
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
