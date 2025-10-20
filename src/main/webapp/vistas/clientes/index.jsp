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
    </head>
    <body>
        <h1>Catálogo de Clientes</h1>
      
        <a href="/gestion-taller-autos">Ir a Inicio</a>
        
        <h2>Lista de Clientes</h2> 
        <a href="${pageContext.request.contextPath}/clientes?action=crear">Crear</a>        
        <br>
        <br>
        <table border="1">
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
                    <td>
                        
                        <!-- Formulario para Editar -->
                        <form action="${pageContext.request.contextPath}/clientes" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="editar">
                            <input type="hidden" name="idCliente" value="${c.idCliente}">
                            <button type="submit">Editar</button>
                        </form>

                        <!-- Formulario para Eliminar -->
                        <form action="${pageContext.request.contextPath}/clientes" method="post" style="display:inline;"
                              onsubmit="return confirm('¿Estás seguro de eliminar este cliente?');">
                            <input type="hidden" name="action" value="eliminar">
                            <input type="hidden" name="idCliente" value="${c.idCliente}">
                            <button type="submit">Eliminar</button>
                        </form>

                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
