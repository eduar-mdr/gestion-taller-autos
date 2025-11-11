<%-- 
    Document   : crear
    Created on : Oct 20, 2025, 1:10:34 AM
    Author     : Eduar Medrano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
        
        <title>Registro de clientes</title>
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
    <div class="border border-primary p-4 w-50 mx-auto">
        <form action="${pageContext.request.contextPath}/clientes?action=guardar" method="post">
            <div class="mb-3">
                <h2 style="color: #253D85;" class="text-center">
                    <i class="bi bi-person-check-fill"></i> REGISTRO DE CLIENTES</h2>
            </div>
            <!-- usuario -->
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input name="nombre" type="text" class="form-control" placeholder="Nombres" required style="color: black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input name="apellido"  type="text" class="form-control" placeholder="Apellidos" required style="color: black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                 <select name="tipoDocumento" class="form-select" required style="color: black;">
                    <option value="">Seleccione tipo de documento</option>
                    <option value="DUI">DUI</option>
                    <option value="NIT">NIT</option>
                    <option value="Pasaporte">Pasaporte</option>
                    <option value="Licencia">Licencia</option>
                </select>
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                <input name="documento"  type="number" class="form-control" placeholder="DUI" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-envelope-fill"></i></span>
                <input name="email"  type="text" class="form-control" placeholder="Correo electrónico" required>
            </div>
            <div class="mb-3 input-group">
                 <span class="input-group-text"><i class="bi bi-phone"></i></span>
                <input name="telefono"  type="number" class="form-control" placeholder="Telefono"
                style="color :black;">
            </div>
            
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-house"></i></span>
                <input name="direccion" type="text" class="form-control" placeholder="Direccion" required
                style="color :black;">
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-success">
                    <i class="bi bi-save"></i> Guardar
                </button>
                <a href="${pageContext.request.contextPath}/clientes" class="btn btn-danger ms-2">
                    <i class="bi bi-x-circle"></i> Cancelar
                </a>
            </div>
        </form>
    </div>
</body>
</html>