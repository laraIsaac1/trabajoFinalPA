<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="teatroreserva.model.reserva" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Reservas</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="pagina-reservas">
    <div class="contenedor-reservas">
        <h2 class="titulo-reservas">Mis Reservas</h2>

        <% 
            List<reserva> reservas = (List<reserva>) request.getAttribute("reservas");
            if (reservas == null || reservas.isEmpty()) {
        %>
            <div class="mensaje-informacion">No tenés reservas realizadas.</div>
        <% 
            } else { 
        %>
            <table class="tabla-reservas">
                <thead>
                    <tr>
                        <th>Obra</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Sala</th>
                        <th>Cantidad</th>
                        <th>N° Orden</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (reserva r : reservas) { %>
                    <tr>
                        <td><%= r.getTituloObra() %></td>
                        <td><%= r.getFechaFuncion() %></td>
                        <td><%= r.getHoraFuncion() %></td>
                        <td><%= r.getSala() %></td>
                        <td><%= r.getCantidad() %></td>
                        <td><%= r.getNumeroOrden() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% 
            } 
        %>

        <a href="obras" class="btn-volver-catalogo">Volver al Catálogo</a>
    </div>
</body>
</html>
