<%-- 
    Document   : index
    Created on : Oct 19, 2025, 7:46:54 PM
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
        <link rel="stylesheet" href="bs/css/estilo.css">
        <script src="bs/js/accion.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <style>
            body {
                min-height: 100vh;
                background: linear-gradient(135deg, #0f172a, #1e3a8a, #0ea5e9);
                display: flex;
                align-items: center;
                justify-content: center;
                font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
            }
            .login-card {
                max-width: 420px;
                width: 100%;
                border-radius: 1.25rem;
                box-shadow: 0 20px 45px rgba(15, 23, 42, .5);
                overflow: hidden;
            }

            .login-header {
                background: radial-gradient(circle at top left, #38bdf8, #1d4ed8);
                color: #fff;
            }

            .login-header i {
                font-size: 2.5rem;
            }

            .form-control:focus {
                border-color: #2563eb;
                box-shadow: 0 0 0 .2rem rgba(37, 99, 235, .25);
            }

            .btn-primary {
                background: linear-gradient(135deg, #2563eb, #1d4ed8);
                border: none;
            }

            .btn-primary:hover {
                background: linear-gradient(135deg, #1d4ed8, #1e40af);
            }

            .toggle-password {
                cursor: pointer;
            }
        </style>

        <div class="card login-card bg-light">
            <!-- Encabezado -->
            <div class="login-header p-4 text-center">
                <div class="mb-2">
                    <i class="bi bi-person-circle"></i>
                </div>
                <h4 class="mb-0 fw-semibold">Bienvenido</h4>
                <small class="opacity-75">Ingresa tus credenciales para continuar</small>
            </div>

            <!-- Formulario -->
            <div class="card-body p-4">
                <form action="${pageContext.request.contextPath}/login" method="post" autocomplete="off">

                    <!-- Usuario -->
                    <div class="mb-3">
                        <label for="usuario" class="form-label">Usuario</label>
                        <div class="input-group">
                            <span class="input-group-text">
                                <i class="bi bi-person-fill"></i>
                            </span>
                            <input type="text" class="form-control" id="usuario" name="usuario"
                                   placeholder="Ingresa tu usuario" required>
                        </div>
                    </div>

                    <!-- Contraseña -->
                    <div class="mb-3">
                        <label for="contrasena" class="form-label">Contraseña</label>
                        <div class="input-group">
                            <span class="input-group-text">
                                <i class="bi bi-lock-fill"></i>
                            </span>
                            <input type="password" class="form-control" id="contrasena" name="contrasena"
                                   placeholder="Ingresa tu contraseña" required>
                            <span class="input-group-text toggle-password" id="btnVerClave">
                                <i class="bi bi-eye-fill" id="iconoOjo"></i>
                            </span>
                        </div>
                        <div class="form-text">Puedes mostrar/ocultar la contraseña con el icono️</div>
                    </div>

                    <!-- Recordarme -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="recordarme">
                            <label class="form-check-label" for="recordarme">Recordarme</label>
                        </div>
                    </div>

                    <!-- Error -->
                    <% String error = (String) request.getAttribute("error"); %>
                    <% if (error != null) { %>
                    <div class="alert alert-danger py-2" role="alert">
                        <%= error %>
                    </div>
                    <% } %>

                    <!-- Botón -->
                    <div class="d-grid mt-3">
                        <button type="submit" class="btn btn-primary btn-lg">
                            <i class="bi bi-box-arrow-in-right me-1"></i> Ingresar
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            const inputPass = document.getElementById('contrasena');
            const btnVerClave = document.getElementById('btnVerClave');
            const iconoOjo = document.getElementById('iconoOjo');

            btnVerClave.addEventListener('click', () => {
                const esPassword = inputPass.type === 'password';
                inputPass.type = esPassword ? 'text' : 'password';
                iconoOjo.classList.toggle('bi-eye-fill', !esPassword);
                iconoOjo.classList.toggle('bi-eye-slash-fill', esPassword);
            });
        </script>
    </body>
</html>
