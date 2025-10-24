<%-- 
    Document   : registroEmpleado
    Created on : 20 oct 2025, 4:51:33 p. m.
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../bs/css/estilo.css">
        <!-- <link rel="stylesheet" href="../../bs/fonts/iconos.css"> -->
        <script src="../../bs/js/accion.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
        <title>Registro de empleados</title>
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100">
        <div class="border border-primary p-4 w-50 mx-auto">
        <form action="" method="post">
            <div class="mb-3">
                <h2 style="color: #234C6A;" class="text-center">
                    <i class="bi bi-people"></i> REGISTRO DE EMPLEADOS</h2>
            </div>
            
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Nombre" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Apellido" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                <input type="number" class="form-control" placeholder="DUI" required
                style="color :black;">
            </div>
             <div class="mb-3 input-group">
                 <span class="input-group-text"><i class="bi bi-phone"></i></span>
                <input type="number" class="form-control" placeholder="Telefono"
                style="color :black;">
            </div>
            
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-house"></i></span>
                <input type="text" class="form-control" placeholder="Direccion" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Nombre de usuario" required style="color: black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-badge-fill"></i></span>
                <select class="form-select" aria-label="Default select example">
                    <option selected>Selecciona un rol</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>    
        </form>
    </div>
    </body>
</html>
