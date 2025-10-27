<%-- 
    Document   : registroClientes
    Created on : 21 oct 2025, 3:18:08 p. m.
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
        
        <title>Registro de clientes</title>
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100">
    <div class="border border-primary p-4 w-50">
        <form action="" method="post">
            <div class="mb-3">
                <h2 style="color: #253D85;" class="text-center">
                    <i class="bi bi-person-check-fill"></i> REGISTRO DE CLIENTES</h2>
            </div>
            <!-- usuario -->
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Nombres" required style="color: black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Apellidos" required style="color: black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-card-text"></i></span>
                <input type="number" class="form-control" placeholder="DUI" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-envelope-fill"></i></span>
                <input type="text" class="form-control" placeholder="Correo electrónico" required>
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
        </form>
    </div>
</body>
</html>
