<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar empleado</title>
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
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

        <div class="border border-primary p-4 w-50 mx-auto">
            <form action="${pageContext.request.contextPath}/empleados?action=actualizar" method="post">
                <input type="hidden" name="idEmpleado" value="${empleado.idEmpleado}" />

                <!-- TÍTULO -->
                <div class="mb-3">
                    <h2 style="color: #253D85;" class="text-center">
                        <i class="bi bi-person-badge"></i> ACTUALIZAR EMPLEADO
                    </h2>
                </div>
                <input type="hidden" name="idEmpleado" value="${empleado.idEmpleado}" />

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-tags-fill"></i></span>
                    <input name="nombre" type="text" class="form-control" 
                           placeholder="Nombre del empleado"
                           value="${empleado.nombre}"
                           required style="color: black;">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-tags-fill"></i></span>
                    <input name="apellido" type="text" class="form-control" 
                           placeholder="Apellido del empleado"
                           value="${empleado.apellido}"
                           required style="color: black;">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                    <input name="dui" type="text" class="form-control" 
                           placeholder="DUI"
                           value="${empleado.dui}"
                           required style="color: black;">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-phone"></i></span>
                    <input name="telefono" type="text" class="form-control" 
                           placeholder="Telefono del empleado"
                           value="${empleado.telefono}"
                           required style="color: black;">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-house"></i></span>
                    <input name="direccion" type="text" class="form-control" 
                           placeholder="Direccion del empleado"
                           value="${empleado.direccion}"
                           required style="color: black;">
                </div>
                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi-person-circle"></i></span>
                    <select name="idUsuario" required class="form-select">
                        <option value="">Seleccione un usuario</option>
                        <c:forEach var="u" items="${usuarios}">
                            <option value="${u.idUsuario}" 
                                    ${empleado.idUsuario == u.idUsuario ? 'selected' : ''}>
                                ${u.nombreUsuario}
                            </option>
                        </c:forEach>

                    </select>
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-tags-fill"></i></span>
                    <input name="salario" type="number" class="form-control" step="0.01" min="0"
                           placeholder="Salario"
                           value="${empleado.salario}"
                           required style="color: black;">
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text"><i class="bi bi-briefcase-fill"></i></span>
                    <select name="estado" required class="form-select">
                        <option value="">Seleccione un estado</option>
                        <option value="Activo"  ${empleado.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                        <option value="Inactivo" ${empleado.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>

 <div class="mb-3 input-group">
     <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                <select name="cargo" required class="form-select">
    <option value="">Seleccione un cargo</option>
    <option value="Gerente"  ${empleado.cargo == 'Gerente' ? 'selected' : ''}>Gerente</option>
    <option value="Recepcionista" ${empleado.cargo == 'Recepcionista' ? 'selected' : ''}>Recepcionista</option>
    <option value="Asesor" ${empleado.cargo == 'Asesor' ? 'selected' : ''}>Asesor de servicios</option>
    <option value="Mecanico" ${empleado.cargo == 'Mecanico' ? 'selected' : ''}>Mecánico</option>
    <option value="Electricista" ${empleado.cargo == 'Electricista' ? 'selected' : ''}>Electricista automotriz</option>
    <option value="MecanicoEspecialista" ${empleado.cargo == 'MecanicoEspecialista' ? 'selected' : ''}>Mecánico especialista</option>
</select>
 </div>


                <!-- BOTONES -->
                <div class="text-center">
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-save"></i> Actualizar
                    </button>

                    <a href="${pageContext.request.contextPath}/empleados" class="btn btn-danger ms-2">
                        <i class="bi bi-x-circle"></i> Cancelar
                    </a>
                </div>

            </form>
        </div>


    </body>
</html>
