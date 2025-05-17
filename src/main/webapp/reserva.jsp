<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="teatroreserva.model.obra" %>
<%@ page import="teatroreserva.model.funcion" %>
<%@ page import="java.util.List" %>  
<%     
    obra obraActual = (obra) request.getAttribute("obra");     
    List<funcion> funciones = (List<funcion>) request.getAttribute("funciones");     
    Boolean agotadas = (Boolean) request.getAttribute("agotadas");     
    if (agotadas == null) agotadas = false; 
%>  

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reservar Entrada</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css"> 
</head>
<body class="body-reserva">

<% if (session != null && session.getAttribute("usuarioId") != null) { %>
    <nav class="nav-bar-reserva">
        <a href="misReservas" class="nav-link-reserva">Mis Reservas</a>
        <a href="logout" class="nav-link-reserva logout-reserva">Cerrar Sesión</a>
    </nav>
    <hr>
<% } %>

<div class="container-reserva">
    <h2 class="section-title-reserva">Reservar Entrada</h2>
    
    <div class="content-row-reserva">
        <div class="col-left-reserva">
            <img src="img/img-<%= obraActual.getTitulo().toLowerCase().replace(" ", "").replace("ó", "o") %>.jpg"
                alt="<%= obraActual.getTitulo() %>" class="image-reserva">
        </div>
        <div class="col-right-reserva">
            <h4 class="obra-title-reserva"><%= obraActual.getTitulo() %></h4>
            <p><strong>Autor:</strong> <%= obraActual.getAutor() %></p>
            <p><strong>Género:</strong> <%= obraActual.getGenero() %></p>

            <% if (agotadas) { %>
                <p class="text-danger-reserva">Funciones Agotadas</p>
            <% } else { %>
                <form action="compra" method="post" class="form-reserva">
                    <input type="hidden" name="obraId" value="<%= obraActual.getId() %>">
                    
                    <div class="form-group-reserva">
                        <label for="funcion">Elegí función:</label>
                        <select name="funcionId" class="input-select-reserva" required>
                            <% for (funcion f : funciones) { %>
                                <% if (f.getStock() > 0) { %>
                                    <option value="<%= f.getId() %>">
                                        <%= f.getFecha() %> - <%= f.getHora() %> - Sala <%= f.getSala() %> - $<%= f.getPrecio() %>
                                    </option>
                                <% } else { %>
                                    <option disabled>
                                        <%= f.getFecha() %> - <%= f.getHora() %> - Sala <%= f.getSala() %> - AGOTADA
                                    </option>
                                <% } %>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group-reserva">
                        <label for="cantidad">Cantidad:</label>
                        <input type="number" name="cantidad" min="1" value="1" class="input-text-reserva" required>
                    </div>

                    <button type="submit" class="btn-submit-reserva">Comprar</button>
                </form>
            <% } %>

            <a href="obras" class="btn-back-reserva">Volver al catálogo</a>
        </div>
    </div>
</div>

</body>
</html>
