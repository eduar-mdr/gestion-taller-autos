<%-- 
    Document   : registroVehiculo
    Created on : 21 oct 2025, 2:48:23 p. m.
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

        <title>Registro de Vehiculos</title>
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100">
    <div class="border border-black p-4 w-50">
        <form action="" method="post">
            <div class="mb-3">
                <h2 style="color: #1B3C53;" class="text-center">
                    <i class="bi bi-car-front"></i> REGISTRO DE VEHICULOS</h2>
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-car-front"></i></span>
                <select class="form-select" aria-label="Default select example">
                    <option selected>Selecciona el tipo de vehiculo</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-tags"></i></span>
                <input type="text" class="form-control" placeholder="Marca del vehiculo" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-sliders"></i></span>
                <input type="text" class="form-control" placeholder="Modelo del vehiculo" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-calendar-date"></i></span>
                <input type="number" class="form-control" placeholder="Año del vehiculo" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-card-heading"></i></span>
                <input type="number" class="form-control" placeholder="Placa del vehiculo" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi-palette-fill"></i></span>
                <input type="text" class="form-control" placeholder="Color del vehiculo" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi-speedometer2"></i></span>
                <input type="number" class="form-control" placeholder="Kilometraje del vehiculo" required
                style="color :black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Propietario del vehiculo" required
                style="color :black;">
            </div>
            
        </form>
    </div>
</body>
</html>
