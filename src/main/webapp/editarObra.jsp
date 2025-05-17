<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="teatroreserva.model.obra" %>

<%
    obra o = (obra) request.getAttribute("obra");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Obra</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="editar-obra-body">

<div class="form-container">
    <h2 class="form-title">Editar Obra</h2>

    <form action="editarObra" method="post" class="formulario">
        <input type="hidden" name="id" value="<%= o.getId() %>">

        <div class="form-group">
            <label for="titulo">Título</label>
            <input type="text" name="titulo" id="titulo" value="<%= o.getTitulo() %>" class="input" required>
        </div>

        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input type="text" name="descripcion" id="descripcion" value="<%= o.getDescripcion() %>" class="input" required>
        </div>

        <div class="form-group">
            <label for="autor">Autor</label>
            <input type="text" name="autor" id="autor" value="<%= o.getAutor() %>" class="input" required>
        </div>

        <div class="form-group">
            <label for="duracion">Duración (min)</label>
            <input type="number" name="duracion" id="duracion" value="<%= o.getDuracion() %>" class="input" required>
        </div>

        <div class="form-group">
            <label for="genero">Género</label>
            <input type="text" name="genero" id="genero" value="<%= o.getGenero() %>" class="input" required>
        </div>

        <div class="form-actions">
            <a href="adminObra" class="btn btn-cancelar">Cancelar</a>
            <button type="submit" class="btn btn-guardar">Guardar Cambios</button>
        </div>
    </form>
</div>

</body>
</html>
