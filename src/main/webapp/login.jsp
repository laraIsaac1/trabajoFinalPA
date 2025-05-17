<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="login-body">

<div class="login-container">
    <div class="login-card">
        <h2 class="title">Iniciar Sesión</h2>
        <form action="loginServlet" method="post">
            <div class="form-group">
                <label for="user">Usuario</label>
                <input type="text" id="user" name="username" placeholder="Ingrese su usuario o correo" required>

            </div>
            <div class="form-group">
                <label for="pass">Contraseña</label>
                <input type="password" id="pass" name="password" placeholder="Ingrese su contraseña" required>
            </div>
            <button type="submit" class="btn-primary">Iniciar Sesión</button>
        </form>
        <p class="register-link">¿No tienes cuenta? <a href="registro.jsp" class="text-primary">Regístrate aquí</a></p>
    </div>
</div>

</body>
</html>

