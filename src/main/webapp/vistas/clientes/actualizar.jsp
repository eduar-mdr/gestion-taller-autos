<%-- 
    Document   : actualizar
    Created on : Oct 20, 2025, 1:14:28 AM
    Author     : Eduar Medrano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Cliente</title>
    </head>
    <body>
        <h2>Actualizar Cliente</h2>
        <form action="${pageContext.request.contextPath}/clientes?action=actualizar" method="post">
            <input type="hidden" name="idCliente" value="${cliente.idCliente}" />

            <label>Nombre:</label>
            <input type="text" name="nombre" value="${cliente.nombre}" required /><br/>

            <label>Apellido:</label>
            <input type="text" name="apellido" value="${cliente.apellido}" required /><br/>

            <label>Documento: 
            <input type="text" name="documento" value="${cliente.documento}" required><br>
            
            <label>Tipo Documento: </label>
            <input type="text" name="tipoDocumento" value="${cliente.tipoDocumento}" required><br>
            
            <label>Dirección: </label>
            <input type="text" name="direccion" value="${cliente.direccion}" required><br>
            
            <label>Teléfono: </label>
            <input type="text" name="telefono" value="${cliente.telefono}" required><br>
            
            <label>Email: </label>
            <input type="text" name="email" value="${cliente.email}" required><br>
            
            <input type="submit" value="Actualizar" />
            
        </form>

        <a href="${pageContext.request.contextPath}/clientes">Cancelar</a>        

    </body>
</html>
