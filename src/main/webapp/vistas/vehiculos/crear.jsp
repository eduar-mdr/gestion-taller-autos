<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
        <title>Registro de Vehiculos</title>
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
        <div class="border border-black p-4 w-75 mx-auto">
            <form action="${pageContext.request.contextPath}/vehiculos?action=guardar" method="post">

                <div class="mb-3 text-center">
                    <h2 style="color: #1B3C53;"><i class="bi bi-car-front"></i> REGISTRO DE VEHÍCULOS</h2>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-sliders"></i></span>
                    <input type="text" name="modelo" class="form-control" placeholder="Modelo del vehículo" required style="color:black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-calendar-date"></i></span>
                    <input type="number" name="anio" class="form-control" placeholder="Año del vehículo" required style="color:black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-card-heading"></i></span>
                    <input type="text" name="placa" class="form-control" placeholder="Placa del vehículo" required style="color:black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi-speedometer2"></i></span>
                    <input type="text" name="numMotor" class="form-control" placeholder="Número de motor" required style="color:black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi-palette-fill"></i></span>
                    <input type="text" name="color" class="form-control" placeholder="Color del vehículo" required style="color:black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi-speedometer2"></i></span>
                    <input type="number" name="kilometraje" class="form-control" placeholder="Kilometraje" required style="color:black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-shield-fill"></i></span>
                    <input type="text" name="numChasis" class="form-control" placeholder="Número de chasis" required style="color:black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi-people-fill"></i></span>
                    <select name="idCliente" required class="form-select">
                        <option value="">Seleccione un cliente</option>
                        <c:forEach var="c" items="${clientes}">
                            <option value="${c.idCliente}">
                                ${c.nombre} ${c.apellido}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-car-front"></i></span>
                    <select name="idTipo" required class="form-select">
                        <option value="">Seleccione un tipo de vehículo</option>
                        <c:forEach var="t" items="${tipos}">
                            <option value="${t.idTipo}">
                                ${t.nombreTipo}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-tags"></i></span>
                    <select name="idMarca" required class="form-select">
                        <option value="">Seleccione una marca</option>
                        <c:forEach var="m" items="${marcas}">
                            <option value="${m.idMarca}">
                                ${m.nombreMarca}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-journal-text"></i></span>
                    <textarea name="historialServicio" class="form-control" placeholder="Historial de servicio" rows="3" style="color:black;"></textarea>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-gear-fill"></i></span>
                    <select name="estadoVehiculo" required class="form-select">
                        <option value="">Seleccione un estado</option>
                        <option value="Activo">Activo</option>
                        <option value="Inactivo">Inactivo</option>
                        <option value="En reparación">En reparación</option>
                        <option value="Vendido">Vendido</option>
                    </select>
                </div>

                <!-- Botones -->
                <div class="text-center">
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-save"></i> Guardar
                    </button>
                    <a href="${pageContext.request.contextPath}/vehiculos" class="btn btn-danger ms-2">
                        <i class="bi bi-x-circle"></i> Cancelar
                    </a>
                </div>

            </form>
        </div>
    </body>
</html>