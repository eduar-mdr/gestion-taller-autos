<%-- 
    Document   : vehiculoCliente
    Created on : 18 nov 2025, 11:13:56 p. m.
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehiculo del cliente</title>
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
    </head>
    <body>
        <div class="container mt-5">

    <h2 class="mb-4 text-center fw-bold">
        Historial de vehículos registrados del cliente
    </h2>
            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>ID Vehículo</th>
                        <th>Modelo</th>
                        <th>Año</th>
                        <th>Placa</th>
                        <th>Color</th>
                        <th>Estado</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="v" items="${vehiculos}">
                        <tr>
                            <td>${v.idVehiculo}</td>
                            <td>${v.modelo}</td>
                            <td>${v.anio}</td>
                            <td>${v.placa}</td>
                            <td>${v.color}</td>
                            <td>${v.estadoVehiculo}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
</div>

</html>
