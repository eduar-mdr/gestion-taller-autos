<%-- 
    Document   : index
    Created on : Oct 19, 2025, 7:46:54 PM
    Author     : Eduar Medrano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection" %>
<%@page import="conexion.ConexionDB" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Gestión de taller de autos y motos</title>
    </head>
    <body>
        <h1>Comprobando la conexión</h1>
        <% 
            Connection conn = ConexionDB.conectar();
            if(conn != null) {
        %>
        <h2>Conexión exitosa</h2>
        <% 
            conn.close();
            } else {
        %>
        <h2>Error en la conexión</h2>
        <% 
            }
        %>
        
        <hr>
        <a href="/gestion-taller-autos/clientes">Ir a Clientes</a>
    </body>
</html>
