<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="registro-body">

<div class="registro-container">
    <div class="registro-card">
        <h2 class="title">Registro de Usuario</h2>
			        <%
			    String error = request.getParameter("error");
			    String success = request.getParameter("success");
			    if (error != null) {
			        if (error.equals("emailExists")) {
			%>
			            <div class="alert alert-error">El correo electrónico ya está registrado.</div>
			<%
			        } else if (error.equals("registrationFailed")) {
			%>
			            <div class="alert alert-error">Error en el registro, intente nuevamente.</div>
			<%
			        } else if (error.equals("exception")) {
			%>
			            <div class="alert alert-error">Ocurrió un error inesperado.</div>
			<%
			        }
			    } else if (success != null && success.equals("registered")) {
			%>
			        <div class="alert alert-success">Registro exitoso, ya puedes iniciar sesión.</div>
			<%
			    }
			%>
        <form action="registroServlet" method="post" class="registro-formulario">
            <div class="form-group">
                <label class="form-label">Nombre Completo</label>
                <input type="text" name="nombre" class="registro-input" placeholder="Ingrese su nombre completo" required>
            </div>
            <div class="form-group">
                <label class="form-label">Correo Electrónico</label>
                <input type="email" name="correo" class="form-input" placeholder="Ingrese su correo" required>
            </div>
            <div class="form-group">
                <label class="form-label">Usuario</label>
                <input type="text" name="username" class="registro-input" placeholder="Ingrese su usuario" required>
            </div>
            <div class="form-group">
                <label class="form-label">Contraseña</label>
                <input type="password" name="password" class="registro-input" placeholder="Ingrese su contraseña" required>
            </div>
            <button type="submit" class="btn-primary">Registrarse</button>
        </form>
        <p class="registro-link">¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión aquí</a></p>
    </div>
</div>

</body>
</html>
