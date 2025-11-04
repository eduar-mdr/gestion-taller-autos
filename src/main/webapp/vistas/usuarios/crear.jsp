<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../bs/css/estilo.css">
        <!-- <link rel="stylesheet" href="../../bs/fonts/iconos.css"> -->
        <script src="../../bs/js/accion.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
        <title>Registro de usuarios</title>
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
        <form action="" method="post">
            <div class="mb-3">
                <h2 style="color: #234C6A;" class="text-center">
                    <i class="bi bi-person-circle"></i> REGISTRO DE USUARIO</h2>
            </div>
            <!-- usuario -->
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Nombre de usuario" required style="color: black;">
            </div>
            <!-- correo -->
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-envelope-fill"></i></span>
                <input type="text" class="form-control" placeholder="Correo electrónico" required>
            </div>
            <!-- contra -->
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                <input type="password" class="form-control" placeholder="Introduza una contraseña" required> 
            </div>
            <!-- rol -->
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-badge-fill"></i></span>
                <select class="form-select" aria-label="Default select example">
                    <option selected>Selecciona un rol</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-success">
                    <i class="bi bi-save"></i> Guardar
                </button>
                <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-danger ms-2">
                    <i class="bi bi-x-circle"></i> Cancelar
                </a>
            </div>
        </form>
    </div>
</body>

</html>
