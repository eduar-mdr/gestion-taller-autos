<%-- 
    Document   : servicioCliente
    Created on : 18 nov 2025, 11:47:35 p. m.
    Author     : MINEDUCYT
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicios del cliente</title>
        <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>
    </head>
    <body>
        <div class="container mt-5">

            <h2 class="mb-4 text-center fw-bold">
                Historial de servicios del Cliente
            </h2>
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID del servicio</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${servicios}">
                        <tr>
                            <td>${s.idServicio}</td>
                            <td>${s.nombre}</td>
                            <td>${s.descripcion}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
