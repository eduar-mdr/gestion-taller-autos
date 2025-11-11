<%-- 
    Document   : index
    Created on : Oct 19, 2025, 11:33:23 PM
    Author     : Eduar Medrano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Clientes</title>
        
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
        <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> -->
    
        <!-- Datatables and Jquery -->
        <link  rel="stylesheet" href="datatables/datatables.css"/>
        <script src="datatables/jquery.js"></script>
        <script src="datatables/datatables.js"></script>
        
    </head>
    <body>
        
        <!-- Menú -->
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#1B3C53;">
            <div class="container-fluid">
                <a class="navbar-brand" href="../../index.jsp" style="color:#fff; font-weight:bold;">
                    <i class="bi bi-house-door-fill"></i> Taller Mecánico
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNav">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="menuNav">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        
                        <!-- Usuarios -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"
                               style="color:#fff; transition:0.3s;"
                               onmouseover="this.style.backgroundColor = '#456882'; this.style.borderRadius = '5px';"
                               onmouseout="this.style.backgroundColor = 'transparent';">
                                <i class="bi bi-person-circle"></i> Usuarios
                            </a>
                            <ul class="dropdown-menu" style="background-color:#456882;">
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                            </ul>
                        </li>

                        <!-- Empleados -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"
                               style="color:#fff; transition:0.3s;"
                               onmouseover="this.style.backgroundColor = '#456882'; this.style.borderRadius = '5px';"
                               onmouseout="this.style.backgroundColor = 'transparent';">
                                <i class="bi bi-person-badge"></i> Empleados
                            </a>
                            <ul class="dropdown-menu" style="background-color:#456882;">
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                            </ul>
                        </li>

                        <!-- Clientes -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"
                               style="color:#fff; transition:0.3s;"
                               onmouseover="this.style.backgroundColor = '#456882'; this.style.borderRadius = '5px';"
                               onmouseout="this.style.backgroundColor = 'transparent';">
                                <i class="bi bi-people"></i> Clientes
                            </a>
                            <ul class="dropdown-menu" style="background-color:#456882;">
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                            </ul>
                        </li>

                        <!-- Proveedores -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"
                               style="color:#fff; transition:0.3s;"
                               onmouseover="this.style.backgroundColor = '#456882'; this.style.borderRadius = '5px';"
                               onmouseout="this.style.backgroundColor = 'transparent';">
                                <i class="bi bi-box-seam"></i> Proveedores
                            </a>
                            <ul class="dropdown-menu" style="background-color:#456882;">
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                            </ul>
                        </li>
                        <!-- Vehiculos -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"
                               style="color:#fff; transition:0.3s;"
                               onmouseover="this.style.backgroundColor = '#456882'; this.style.borderRadius = '5px';"
                               onmouseout="this.style.backgroundColor = 'transparent';">
                                <i class="bi bi-car-front"></i> Vehiculos
                            </a>
                            <ul class="dropdown-menu" style="background-color:#456882;">
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                                <li><a class="dropdown-item" href="#" style="color:#fff;"
                                       onmouseover="this.style.backgroundColor = '#456882';" 
                                       onmouseout="this.style.backgroundColor = 'transparent';">#</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Fin del menú -->

        <br>
        
        
        <div class="container mt-5">

            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="/gestion-taller-autos">Inicio</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Clientes</li>
                </ol>
            </nav>

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2 class="mb-0">Catálogo de Clientes</h2>

                <a href="${pageContext.request.contextPath}/clientes?action=crear" 
                   class="btn btn-primary">
                    <i class="bi bi-plus-circle me-1"></i> Crear
                </a>
            </div>       
            <br>
            
            <div class="table-responsive">
            <table class="table table-bordered border-dark-subtle table-striped table-hover" id="tablaClientes">
                <thead class="" >
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Documento</th>
                        <th>Tipo de documento</th>
                        <th>Dirección</th>
                        <th>Telefono</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">
                    <c:forEach var="c" items="${clientes}">
                        <tr>
                            <td>${c.idCliente}</td>
                            <td>${c.nombre}</td>
                            <td>${c.apellido}</td>
                            <td>${c.documento}</td>
                            <td>${c.tipoDocumento}</td>
                            <td>${c.direccion}</td>
                            <td>${c.telefono}</td>
                            <td>${c.email}</td>
                            <td class="text-nowrap">

                               <!-- Botón Editar -->
                                <form action="${pageContext.request.contextPath}/clientes" 
                                      method="post" 
                                      class="d-inline">

                                    <input type="hidden" name="action" value="editar">
                                    <input type="hidden" name="idCliente" value="${c.idCliente}">

                                    <button type="submit" class="btn btn-sm btn-warning">
                                        <i class="bi bi-pencil-square"></i>
                                    </button>
                                </form>

                                <!-- Botón Eliminar -->
                                <form action="${pageContext.request.contextPath}/clientes" 
                                      method="post" 
                                      class="d-inline"
                                      onsubmit="return confirm('¿Estás seguro de eliminar este cliente?');">

                                    <input type="hidden" name="action" value="eliminar">
                                    <input type="hidden" name="idCliente" value="${c.idCliente}">

                                    <button type="submit" class="btn btn-sm btn-danger">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                
            </table>
            </div>
        </div>
        
    </body>
</html>
