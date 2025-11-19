<%-- 
    Document   : editar
    Created on : 19 nov 2025, 3:48:52 p. m.
    Author     : fuent
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Orden #${orden.idOrden} - Taller</title>
    <link rel="stylesheet" href="bs/css/estilo.css">
        <link rel="stylesheet" href="bs/fonts/iconos.css">
        <script src="bs/js/accion.js"></script>

        <!-- Datatables and Jquery -->
        <link  rel="stylesheet" href="datatables/datatables.css"/>
        <script src="datatables/jquery.js"></script>
        <script src="datatables/datatables.js"></script>
    <style>
        body {
            background-color: #f5f5f5;
            padding: 20px;
        }
        .container {
            max-width: 900px;
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            border-bottom: 3px solid #007bff;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        .info-box {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Editar Orden de Trabajo #${orden.idOrden}</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>
        
        <!-- Información del cliente y vehículo (solo lectura) -->
        <div class="info-box">
            <h5>Información (No editable)</h5>
            <p><strong>Cliente:</strong> ${orden.nombreCliente}</p>
            <p><strong>Vehículo:</strong> ${orden.placaVehiculo} - ${orden.marcaVehiculo} ${orden.modeloVehiculo}</p>
            <p class="mb-0"><strong>Fecha de Ingreso:</strong> 
                <fmt:formatDate value="${orden.fechaIngreso}" pattern="dd/MM/yyyy HH:mm" />
            </p>
        </div>
        
        <form action="orden-trabajo" method="post">
            <input type="hidden" name="action" value="actualizar">
            <input type="hidden" name="idOrden" value="${orden.idOrden}">
            
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Estado *</label>
                    <select class="form-select" name="estado" required>
                        <option value="Pendiente" ${orden.estado == 'Pendiente' ? 'selected' : ''}>Pendiente</option>
                        <option value="En Proceso" ${orden.estado == 'En Proceso' ? 'selected' : ''}>En Proceso</option>
                        <option value="Finalizada" ${orden.estado == 'Finalizada' ? 'selected' : ''}>Finalizada</option>
                        <option value="Entregada" ${orden.estado == 'Entregada' ? 'selected' : ''}>Entregada</option>
                        <option value="Cancelada" ${orden.estado == 'Cancelada' ? 'selected' : ''}>Cancelada</option>
                    </select>
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Mecánico Asignado</label>
                    <input type="number" class="form-control" name="idEmpleado" 
                           value="${orden.idEmpleado > 0 ? orden.idEmpleado : ''}">
                    <small class="text-muted">ID del mecánico</small>
                </div>
            </div>
            
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label">Tiempo Estimado (horas)</label>
                    <input type="number" class="form-control" name="tiempoEstimado" 
                           step="0.5" min="0" value="${orden.tiempoEstimado}">
                </div>
                
                <div class="col-md-6">
                    <label class="form-label">Fecha Estimada de Entrega</label>
                    <input type="datetime-local" class="form-control" name="fechaEntrega"
                           value="<fmt:formatDate value='${orden.fechaEntrega}' pattern='yyyy-MM-dd\'T\'HH:mm' />">
                </div>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Diagnóstico *</label>
                <textarea class="form-control" name="diagnostico" rows="4" required 
                          maxlength="300">${orden.diagnostico}</textarea>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Observaciones</label>
                <textarea class="form-control" name="observaciones" rows="3" 
                          maxlength="300">${orden.observaciones}</textarea>
            </div>
            
            <div class="mb-3">
                <label class="form-label">URL de Documentos</label>
                <input type="text" class="form-control" name="documentosUrl" 
                       value="${orden.documentosUrl}">
                <small class="text-muted">Link a documentos escaneados</small>
            </div>
            
            <div class="text-end">
                <a href="orden-trabajo?action=ver&id=${orden.idOrden}" class="btn btn-secondary">Cancelar</a>
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </div>
        </form>
    </div>
</body>
</html>