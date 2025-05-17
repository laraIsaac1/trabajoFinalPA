<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="confirmacion-body">

<%
    if (session.getAttribute("usuarioId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String numeroOrden = request.getParameter("numeroOrden");
%>

<nav class="navbar">
    <a href="misReservas" class="nav-link">Mis Reservas</a>
    <a href="logoutServlet" class="nav-link logout">Cerrar Sesión</a>
</nav>

<hr class="separator">

<div class="mensaje-exito">
    <h4>¡Reserva realizada con éxito!</h4>
    <% if (numeroOrden != null) { %>
        <p>Tu número de orden es: <strong><%= numeroOrden %></strong></p>
    <% } %>
</div>

<div class="boton-volver-container">
    <a href="obras" class="btn-volver">Volver al Catálogo</a>
</div>

</body>
</html>
