<%-- 
    Document   : nuevo
    Created on : 19 nov 2025, 12:41:46 a. m.
    Author     : fuent
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrar Pago - Taller</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
    </head>
    <style>
        .info-card {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
        }
        .total-badge {
            font-size: 1.5em;
            padding: 10px 20px;
        }
        .form-control:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }
    </style>

    <body>
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
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/servicios/reporte.jsp"><i class="bi bi-graph-up"></i> Ingresos</a></li>
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
        <div class="container">
            <h2>Registrar Nuevo Pago</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>

            <form "action="${pageContext.request.contextPath}/clientes?action=registrar"" method="post" id="formPago">
                <!--input type="hidden" name="action" value="registrar"-->

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">Número de Orden *</label>
                        <input type="number" class="form-control" name="idOrden" id="idOrden" required
                               value="${param.idOrden != null ? param.idOrden : ''}"
                               onchange="cargarInfoOrden()">
                        <small class="text-muted">Ingrese el ID de la orden</small>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Método de Pago *</label>
                        <select class="form-select" name="metodoPago" required>
                            <option value="">Seleccione...</option>
                            <option value="Efectivo">Efectivo</option>
                            <option value="Tarjeta">Tarjeta</option>
                            <option value="Transferencia">Transferencia</option>
                            <option value="Cheque">Cheque</option>
                            <option value="Mixto">Mixto</option>
                        </select>
                    </div>
                </div>

                <div id="infoOrdenContainer" style="display: none;">
                    <div class="info-box">
                        <h5>Información de la Orden</h5>
                        <p><strong>Cliente:</strong> <span id="nombreCliente">-</span></p>
                        <p><strong>Vehículo:</strong> <span id="placaVehiculo">-</span></p>
                        <hr>
                        <p><strong>Mano de Obra:</strong> <span id="totalManoObra">$0.00</span></p>
                        <p><strong>Repuestos:</strong> <span id="totalRepuestos">$0.00</span></p>
                        <p><strong>Total General:</strong> <span id="totalGeneral">$0.00</span></p>
                        <p class="mb-0"><strong>Monto Pendiente:</strong> 
                            <span id="montoPendiente" class="text-danger fs-5">$0.00</span>
                        </p>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">Monto a Pagar *</label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="number" class="form-control" name="monto" id="monto" 
                                   step="0.01" min="0.01" required onchange="calcularTotal()">
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Descuento</label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="number" class="form-control" name="descuento" id="descuento" 
                                   step="0.01" min="0" value="0" onchange="calcularTotal()">
                        </div>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">Tipo de Pago *</label>
                        <select class="form-select" name="tipoPago" required>
                            <option value="Total">Pago Total</option>
                            <option value="Parcial">Pago Parcial</option>
                        </select>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Total a Pagar</label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="text" class="form-control bg-light" id="totalPagar" readonly>
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Observaciones</label>
                    <textarea class="form-control" name="observaciones" rows="3"></textarea>
                </div>

                <div class="alert alert-info">
                    <strong>Nota:</strong> El número de factura se generará automáticamente.
                </div>

                <div class="text-end">
                    <a href="pagos?action=listar" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Registrar Pago</button>
                </div>
            </form>
        </div>
        <script>
                                       function cargarInfoOrden() {
                                           const idOrden = document.getElementById('idOrden').value;

                                           if (!idOrden) {
                                               document.getElementById('infoOrdenContainer').style.display = 'none';
                                               return;
                                           }

                                           fetch('pagos?action=calcularTotales&idOrden=' + idOrden)
                                                   .then(response => response.json())
                                                   .then(data => {
                                                       if (data.success) {
                                                           document.getElementById('nombreCliente').textContent = data.nombreCliente;
                                                           document.getElementById('placaVehiculo').textContent = data.placaVehiculo;
                                                           document.getElementById('totalManoObra').textContent = '$' + parseFloat(data.totalManoObra).toFixed(2);
                                                           document.getElementById('totalRepuestos').textContent = '$' + parseFloat(data.totalRepuestos).toFixed(2);
                                                           document.getElementById('totalGeneral').textContent = '$' + parseFloat(data.totalGeneral).toFixed(2);
                                                           document.getElementById('montoPendiente').textContent = '$' + parseFloat(data.montoPendiente).toFixed(2);

                                                           document.getElementById('monto').value = data.montoPendiente;
                                                           document.getElementById('monto').max = data.montoPendiente;
                                                           calcularTotal();

                                                           document.getElementById('infoOrdenContainer').style.display = 'block';
                                                       } else {
                                                           alert(data.message || 'No se pudo cargar la información');
                                                           document.getElementById('infoOrdenContainer').style.display = 'none';
                                                       }
                                                   })
                                                   .catch(error => {
                                                       console.error('Error:', error);
                                                       alert('Error al cargar la información');
                                                   });
                                       }

                                       function calcularTotal() {
                                           const monto = parseFloat(document.getElementById('monto').value) || 0;
                                           const descuento = parseFloat(document.getElementById('descuento').value) || 0;
                                           const total = monto - descuento;
                                           document.getElementById('totalPagar').value = total.toFixed(2);
                                       }

                                       window.onload = function () {
                                           const idOrden = document.getElementById('idOrden').value;
                                           if (idOrden) {
                                               cargarInfoOrden();
                                           }
                                       };

                                       document.getElementById('formPago').addEventListener('submit', function (e) {
                                           const monto = parseFloat(document.getElementById('monto').value);
                                           const descuento = parseFloat(document.getElementById('descuento').value) || 0;

                                           if (descuento > monto) {
                                               e.preventDefault();
                                               alert('El descuento no puede ser mayor al monto');
                                               return false;
                                           }

                                           if (monto <= 0) {
                                               e.preventDefault();
                                               alert('El monto debe ser mayor a cero');
                                               return false;
                                           }
                                       });
        </script>
    </body>
</html>