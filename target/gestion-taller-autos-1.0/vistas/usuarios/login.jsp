<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../bs/css/estilo.css">
        <!-- <link rel="stylesheet" href="../../bs/fonts/iconos.css"> -->
        <script src="../../bs/js/accion.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
        <title>Inicio de sesión</title>
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100">
    <div class="border border-primary p-4 w-50">
        <form action="" method="post">
            <div class="mb-3">
                <h2 style="color: #234C6A;" class="text-center">
                    <i class="bi bi-box-arrow-in-right"></i> INICIO DE SESIÓN</h2>
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" class="form-control" placeholder="Usuario" required style="color: black;">
            </div>
            <div class="mb-3 input-group">
                <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                <input type="password" class="form-control" placeholder="Contraseña" required> 
            </div>
        </form>
    </div>
</body>
</html>
