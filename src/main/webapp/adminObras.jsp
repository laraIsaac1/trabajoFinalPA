<%@ page import="java.util.List" %>
<%@ page import="teatroreserva.model.obra" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<obra> obras = (List<obra>) request.getAttribute("obras");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Obras</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="obras-body">

<h2 class="title">Gestión de Obras</h2>
<a href="adminHome.jsp" class="btn-link">Volver al Panel</a>

<!-- Mensajes -->
<% if (request.getAttribute("mensajeExito") != null) { %>
    <div class="success-messaje"><%= request.getAttribute("mensajeExito") %></div>
<% } else if (request.getAttribute("mensajeError") != null) { %>
    <div class="danger-message"><%= request.getAttribute("mensajeError") %></div>
<% } %>

<!-- Botones superiores -->
<div class="top-buttons">
    <form action="adminFunciones" method="get" class="m-0">
        <button type="submit" class="btn-funciones">Ir a Funciones</button>
    </form>
</div>

<!-- Formulario para agregar obra -->
<form action="adminObra" method="post" class="obra-form">
    <input type="hidden" name="action" value="add">

    <input type="text" name="titulo" placeholder="Título" required>
    <input type="text" name="descripcion" placeholder="Descripción" required>
   	<input type="text" name="autor" placeholder="Autor" required>
    <input type="number" name="duracion" placeholder="Duración (min)" min="0" required>
    <input type="text" name="genero" placeholder="Género" required>
    <button type="submit" class="btn-agregarObra">Agregar Obra</button>
</form>

<!-- Tabla de obras -->
<table class="obra-table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Descripción</th>
        <th>Autor</th>
        <th>Duración</th>
        <th>Género</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <% for (obra o : obras) { %>
        <tr>
            <form action="adminObra" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= o.getId() %>">
                <td><%= o.getId() %></td>
                <td><input type="text" name="titulo" value="<%= o.getTitulo() %>" required></td>
                <td><input type="text" name="descripcion" value="<%= o.getDescripcion() %>" required></td>
                <td><input type="text" name="autor" value="<%= o.getAutor() %>" required></td>
                <td><input type="number" name="duracion" value="<%= o.getDuracion() %>" required></td>
                <td><input type="text" name="genero" value="<%= o.getGenero() %>" required></td>
                <td class="action-buttons">
                    <button type="submit" class="btn-guardarObra">Guardar</button>
            </form>
            <form action="adminObra" method="post" onsubmit="return confirm('¿Seguro que querés eliminar esta obra?');">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= o.getId() %>">
                <button type="submit" class="btn-eliminarObra">Eliminar</button>
            </form>
                </td>
        </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>