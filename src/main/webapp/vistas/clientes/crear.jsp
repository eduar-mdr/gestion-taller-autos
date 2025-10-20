<%-- 
    Document   : crear
    Created on : Oct 20, 2025, 1:10:34 AM
    Author     : Eduar Medrano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Cliente</title>
    </head>
    <body>
        <h2>Registrar Cliente</h2>
        <form action="${pageContext.request.contextPath}/clientes?action=guardar" method="post">
            Nombre: <input type="text" name="nombre"><br>
            Apellido: <input type="text" name="apellido"><br>
            Documento: <input type="text" name="documento"><br>
            Tipo Documento: <input type="text" name="tipoDocumento"><br>
            Dirección: <input type="text" name="direccion"><br>
            Teléfono: <input type="text" name="telefono"><br>
            Email: <input type="text" name="email"><br>
            <input type="submit" value="Guardar">
        </form>
        <a href="${pageContext.request.contextPath}/clientes">Cancelar</a>        

    </body>
</html>
