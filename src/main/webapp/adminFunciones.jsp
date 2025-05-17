<%@ page import="java.util.List" %>
<%@ page import="teatroreserva.model.funcion, teatroreserva.model.obra" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<funcion> funciones = (List<funcion>) request.getAttribute("funciones");
    List<obra> obras = (List<obra>) request.getAttribute("obras");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Funciones</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="funciones-body">

<h2 class="title">Gestión de Funciones</h2>
<a href="adminHome.jsp" class="btn-link">Volver al Panel</a>

<!-- Mensajes -->
<% if (request.getAttribute("mensajeExito") != null) { %>
    <div class="success-messaje"><%= request.getAttribute("mensajeExito") %></div>
<% } else if (request.getAttribute("mensajeError") != null) { %>
    <div class="danger-message"><%= request.getAttribute("mensajeError") %></div>
<% } %>

<!-- Formulario para agregar nueva función -->
<form action="adminFunciones" method="post" class="funcion-form">
    <input type="hidden" name="action" value="add">

    <select name="obraId" required>
        <option value="">Obra...</option>
        <% for (obra o : obras) { %>
            <option value="<%= o.getId() %>"><%= o.getTitulo() %></option>
        <% } %>
    </select>

    <input type="date" name="fecha" required>
    <input type="time" name="hora" required>
    <input type="number" name="precio" placeholder="Precio" min="0" required>
    <input type="number" name="sala" placeholder="Sala" min="1" required>
    <input type="number" name="stock" placeholder="Stock" min="0" required>

    <button type="submit" class="btn-agregarFuncion">Agregar Función</button>
</form>

<!-- Tabla de funciones existentes -->
<table class="funcion-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Obra</th>
            <th>Fecha</th>
            <th>Hora</th>
            <th>Precio</th>
            <th>Sala</th>
            <th>Stock</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
    <% for (funcion f : funciones) { %>
        <tr>
            <td><%= f.getId() %></td>
            <form action="adminFunciones" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= f.getId() %>">

                <td>
                    <select name="obraId" required>
                        <% for (obra o : obras) { %>
                            <option value="<%= o.getId() %>" <%= (o.getId() == f.getObraId()) ? "selected" : "" %>>
                                <%= o.getTitulo() %>
                            </option>
                        <% } %>
                    </select>
                </td>

                <td><input type="date" name="fecha" value="<%= f.getFecha() %>"></td>
                <td><input type="time" name="hora" value="<%= f.getHora() %>"></td>
                <td><input type="number" name="precio" value="<%= f.getPrecio() %>"></td>
                <td><input type="number" name="sala" value="<%= f.getSala() %>"></td>
                <td><input type="number" value="<%= f.getStock() %>" readonly></td>

                <td class="action-buttons">
                    <button type="submit" class="btn-guardarFuncion">Guardar</button>
            </form>
            <form action="adminFunciones" method="post" onsubmit="return confirm('¿Seguro que querés eliminar esta función?');">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= f.getId() %>">
                <button type="submit" class="btn-eliminarFuncion">Eliminar</button>
            </form>
                </td>
        </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
