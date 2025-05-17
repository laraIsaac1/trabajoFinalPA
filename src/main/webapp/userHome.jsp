<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="teatroreserva.model.obra, teatroreserva.model.funcion" %>

<%
    Map<obra, List<funcion>> obrasConFunciones = (Map<obra, List<funcion>>) request.getAttribute("obrasConFunciones");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Obras Disponibles</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css"> 
</head>
<body class="obras-body">

<!-- Links arriba a la derecha -->
<% if (session != null && session.getAttribute("usuarioId") != null) { %>
<nav class="navbar">
    <a href="misReservas" class="nav-link">Mis Reservas</a>
    <a href="logout" class="nav-link logout">Cerrar Sesión</a>
</nav>
<hr>
<% } %>

<div class="container-obras">
    <h2 class="title">Obras Disponibles</h2>
    <div class="row-obras">
        <% if (obrasConFunciones != null) {
            for (Map.Entry<obra, List<funcion>> entry : obrasConFunciones.entrySet()) {
                obra obraActual = entry.getKey();
                List<funcion> funciones = entry.getValue();
                boolean agotadas = funciones.isEmpty() || funciones.stream().allMatch(f -> f.getStock() == 0);
        %>
        <div class="col-obras">
            <div class="card-obras">
                <img src="img/img-<%= obraActual.getTitulo()
                        .toLowerCase()
                        .replaceAll("[áàäâ]", "a")
                        .replaceAll("[éèëê]", "e")
                        .replaceAll("[íìïî]", "i")
                        .replaceAll("[óòöô]", "o")
                        .replaceAll("[úùüû]", "u")
                        .replaceAll("[^a-z0-9]", "") %>.jpg" 
                     class="card-img-obras" 
                     alt="<%= obraActual.getTitulo() %>"
                     onerror="this.onerror=null; this.src='img/img-default.jpg';">
                <div class="card-body-obras">
                    <h5 class="card-title-obras"><%= obraActual.getTitulo() %></h5>
                    <p><strong>Autor:</strong> <%= obraActual.getAutor() %></p>
                    <p><strong>Género:</strong> <%= obraActual.getGenero() %></p>
                    <% if (agotadas) { %>
                        <p class="text-danger-obras">Funciones Agotadas</p>
                    <% } else { %>
                        <form action="reserva" method="get" class="form-reserva-obras">
                            <input type="hidden" name="obraId" value="<%= obraActual.getId() %>">
                            <button type="submit" class="btn-reserva-obras">Seleccionar</button>
                        </form>
                    <% } %>
                </div>
            </div>
        </div>
        <% } } %>
    </div>
</div>

</body>
</html>
