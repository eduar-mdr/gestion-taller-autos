<%-- 
    Document   : facturas
    Created on : 19 nov 2025, 12:33:24 a. m.
    Author     : fuent
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Factura ${pago.numeroFactura}</title>
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
            }

            .factura-container {
                max-width: 800px;
                margin: 20px auto;
                background-color: white;
                padding: 20px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .header {
                background-color: #007bff;
                color: white;
                padding: 15px;
                text-align: center;
                margin-bottom: 20px;
            }

            .info-section {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 15px;
                background-color: #f9f9f9;
            }

            .total-box {
                background-color: #e7f3ff;
                border: 2px solid #007bff;
                padding: 15px;
                margin-top: 15px;
            }

            @media print {
                body {
                    background-color: white;
                    margin: 0;
                    padding: 0;
                }

                .factura-container {
                    max-width: 100%;
                    margin: 0;
                    padding: 15px;
                    box-shadow: none;
                }

                .no-print {
                    display: none !important;
                }

                .header {
                    padding: 10px;
                    font-size: 14px;
                }

                .header h3 {
                    font-size: 18px;
                    margin: 5px 0;
                }

                .info-section {
                    padding: 8px;
                    margin-bottom: 10px;
                    font-size: 12px;
                }

                .info-section p {
                    margin: 3px 0;
                }

                table {
                    font-size: 12px;
                }

                .total-box {
                    padding: 10px;
                    margin-top: 10px;
                }

                .firma-section {
                    margin-top: 30px;
                    font-size: 12px;
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


        <br>
        <div class="factura-container">
            <div class="header">
                <h3>TALLER AUTOMOTRIZ</h3>
                <p>FACTURA: ${pago.numeroFactura}</p>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="info-section">
                        <h6>Información del Cliente</h6>
                        <p><strong>Nombre:</strong> ${pago.nombreCliente}</p>
                        <p><strong>Vehículo:</strong> ${pago.placaVehiculo}</p>
                        <p><strong>Orden:</strong> #${pago.idOrden}</p>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="info-section">
                        <h6>Datos del Pago</h6>
                        <p><strong>Fecha:</strong> <fmt:formatDate value="${pago.fechaPago}" pattern="dd/MM/yyyy HH:mm" /></p>
                        <p><strong>Método:</strong> ${pago.metodoPago}</p>
                        <p><strong>Tipo:</strong> ${pago.tipoPago}</p>
                    </div>
                </div>
            </div>

            <h5>Desglose de Costos</h5>
            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Concepto</th>
                        <th class="text-end">Monto</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Mano de Obra</td>
                        <td class="text-end">$<fmt:formatNumber value="${infoOrden.totalManoObra}" pattern="#,##0.00" /></td>
                    </tr>
                    <tr>
                        <td>Repuestos</td>
                        <td class="text-end">$<fmt:formatNumber value="${infoOrden.totalRepuestos}" pattern="#,##0.00" /></td>
                    </tr>
                    <tr class="table-active">
                        <td><strong>Subtotal</strong></td>
                        <td class="text-end"><strong>$<fmt:formatNumber value="${infoOrden.totalGeneral}" pattern="#,##0.00" /></strong></td>
                    </tr>
                </tbody>
            </table>

            <div class="total-box">
                <h5>Información de Este Pago</h5>
                <table class="table table-sm mb-0">
                    <tr>
                        <td><strong>Monto Pagado:</strong></td>
                        <td class="text-end">$<fmt:formatNumber value="${pago.monto}" pattern="#,##0.00" /></td>
                    </tr>
                    <c:if test="${pago.descuento > 0}">
                        <tr>
                            <td><strong>Descuento:</strong></td>
                            <td class="text-end text-success">-$<fmt:formatNumber value="${pago.descuento}" pattern="#,##0.00" /></td>
                        </tr>
                    </c:if>
                    <tr class="table-active">
                        <td><strong>Total de Este Pago:</strong></td>
                        <td class="text-end"><strong>$<fmt:formatNumber value="${pago.monto - pago.descuento}" pattern="#,##0.00" /></strong></td>
                    </tr>
                </table>

                <div class="text-center mt-2">
                    <c:choose>
                        <c:when test="${pago.estado == 'Pagado'}">
                            <span class="badge bg-success">PAGADO</span>
                        </c:when>
                        <c:when test="${pago.estado == 'En Crédito'}">
                            <span class="badge bg-info">EN CRÉDITO</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-warning">PENDIENTE</span>
                        </c:otherwise>
                    </c:choose>
                </div>

                <c:if test="${infoOrden.montoPendiente > 0}">
                    <div class="alert alert-warning mt-2 mb-0">
                        <strong>Saldo Pendiente:</strong> 
                        $<fmt:formatNumber value="${infoOrden.montoPendiente}" pattern="#,##0.00" />
                    </div>
                </c:if>
            </div>

            <c:if test="${not empty pago.observaciones}">
                <div class="mt-3">
                    <p><strong>Observaciones:</strong> ${pago.observaciones}</p>
                </div>
            </c:if>

            <c:if test="${not empty historialPagos && historialPagos.size() > 1}">
                <div class="mt-3">
                    <h6>Historial de Pagos</h6>
                    <table class="table table-sm table-striped">
                        <thead>
                            <tr>
                                <th>Factura</th>
                                <th>Fecha</th>
                                <th>Método</th>
                                <th class="text-end">Monto</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pagoHist" items="${historialPagos}">
                                <tr>
                                    <td>${pagoHist.numeroFactura}</td>
                                    <td><fmt:formatDate value="${pagoHist.fechaPago}" pattern="dd/MM/yyyy" /></td>
                                    <td>${pagoHist.metodoPago}</td>
                                    <td class="text-end">$<fmt:formatNumber value="${pagoHist.monto - pagoHist.descuento}" pattern="#,##0.00" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <div class="firma-section mt-4 pt-3" style="border-top: 1px dashed #ccc;">
                <div class="row text-center">
                    <div class="col-6">
                        <div style="border-top: 1px solid #000; width: 200px; margin: 0 auto; padding-top: 5px;">
                            Firma del Cliente
                        </div>
                    </div>
                    <div class="col-6">
                        <div style="border-top: 1px solid #000; width: 200px; margin: 0 auto; padding-top: 5px;">
                            Firma del Cajero
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center mt-3">
                <small>Gracias por su preferencia</small>
            </div>

            <div class="text-center mt-4 no-print">
                <button onclick="window.print()" class="btn btn-primary">Imprimir Factura</button>
                <a href="pagos?action=listar" class="btn btn-secondary">Volver</a>
                <c:if test="${infoOrden.montoPendiente > 0}">
                    <a href="pagos?action=nuevo&idOrden=${pago.idOrden}" class="btn btn-success">Otro Pago</a>
                </c:if>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>