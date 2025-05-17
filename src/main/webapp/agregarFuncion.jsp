<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="teatroreserva.model.obra" %>
<%
    List<obra> obras = (List<obra>) request.getAttribute("obras");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Función</title>
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="funcion-body">

    <h2 class="form-title">Agregar Función</h2>

    <form action="guardarFuncion" method="post" class="form-funcion">
        <div class="form-group">
            <label for="obraId" class="form-label">Obra</label>
            <select name="obraId" id="obraId" required class="form-select">
                <option value="">Seleccioná una obra</option>
                <% for (obra o : obras) { %>
                    <option value="<%= o.getId() %>"><%= o.getTitulo() %></option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label class="form-label">Fecha</label>
            <input type="date" name="fecha" required class="form-input">
        </div>

        <div class="form-group">
            <label class="form-label">Hora</label>
            <input type="time" name="hora" required class="form-input">
        </div>

        <div class="form-group">
            <label class="form-label">Precio</label>
            <input type="number" name="precio" step="0.01" required class="form-input">
        </div>

        <div class="form-group">
            <label class="form-label">Sala</label>
            <input type="number" name="sala" required class="form-input">
        </div>

        <div class="form-group">
            <label class="form-label">Stock</label>
            <input type="number" name="stock" required class="form-input">
        </div>

        <div class="form-buttons">
            <button type="submit" class="btn-guardar">Guardar Función</button>
            <a href="adminFunciones" class="btn-cancelar">Cancelar</a>
        </div>
    </form>
</body>
</html>
