<%-- 
    Document   : nuevo
    Created on : 19 nov 2025, 3:43:26 p. m.
    Author     : fuent
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Nueva Orden de Trabajo - Taller</title>
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
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Nueva Orden de Trabajo</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>

            <form action="orden-trabajo" method="post">
                <input type="hidden" name="action" value="crear">

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">ID del Vehículo *</label>
                        <input type="number" class="form-control" name="idVehiculo" required>
                        <small class="text-muted">Ingrese el ID del vehículo</small>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">ID del Cliente *</label>
                        <input type="number" class="form-control" name="idCliente" required>
                        <small class="text-muted">Ingrese el ID del cliente</small>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label">Mecánico Asignado</label>
                        <input type="number" class="form-control" name="idEmpleado">
                        <small class="text-muted">ID del mecánico (opcional)</small>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Tiempo Estimado (horas)</label>
                        <input type="number" class="form-control" name="tiempoEstimado" step="0.5" min="0">
                        <small class="text-muted">Ejemplo: 2.5 horas</small>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Fecha Estimada de Entrega</label>
                    <input type="datetime-local" class="form-control" name="fechaEntrega">
                </div>

                <div class="mb-3">
                    <label class="form-label">Diagnóstico Inicial *</label>
                    <textarea class="form-control" name="diagnostico" rows="4" required 
                              maxlength="300"></textarea>
                    <small class="text-muted">Describa el problema reportado (máx. 300 caracteres)</small>
                </div>

                <div class="mb-3">
                    <label class="form-label">Observaciones</label>
                    <textarea class="form-control" name="observaciones" rows="3" 
                              maxlength="300"></textarea>
                    <small class="text-muted">Notas adicionales (opcional)</small>
                </div>

                <div class="mb-3">
                    <label class="form-label">URL de Documentos</label>
                    <input type="text" class="form-control" name="documentosUrl">
                    <small class="text-muted">Link a documentos escaneados (opcional)</small>
                </div>

                <div class="alert alert-info">
                    <strong>Nota:</strong> Después de crear la orden, podrá agregar servicios y repuestos.
                </div>

                <div class="text-end">
                    <a href="orden-trabajo?action=listar" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Crear Orden</button>
                </div>
            </form>
        </div>
    </body>
</html>