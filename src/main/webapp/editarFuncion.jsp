<%@ page import="teatroreserva.model.funcion, teatroreserva.model.obra" %>
<%@ page import="java.util.List" %>
<%
    funcion f = (funcion) request.getAttribute("funcion");
    List<obra> obras = (List<obra>) request.getAttribute("obras");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Función</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="editar-funcion-body">

    <div class="form-container">
        <h2 class="form-title">Editar Función</h2>
        <form action="actualizarFuncion" method="post" class="formulario">
            <input type="hidden" name="id" value="<%= f.getId() %>">

            <div class="form-group">
                <label>Obra</label>
                <select name="obraId" required class="input">
                    <% for (obra o : obras) { %>
                        <option value="<%= o.getId() %>" <%= o.getId() == f.getObraId() ? "selected" : "" %>>
                            <%= o.getTitulo() %>
                        </option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <label>Fecha</label>
                <input type="date" name="fecha" value="<%= f.getFecha() %>" class="input" required>
            </div>

            <div class="form-group">
                <label>Hora</label>
                <input type="time" name="hora" value="<%= f.getHora().toString().substring(0,5) %>" class="input" required>
            </div>

            <div class="form-group">
                <label>Precio</label>
                <input type="number" name="precio" step="0.01" value="<%= f.getPrecio() %>" class="input" required>
            </div>

            <div class="form-group">
                <label>Sala</label>
                <input type="number" name="sala" value="<%= f.getSala() %>" class="input" required>
            </div>

            <div class="form-group">
                <label>Stock</label>
                <input type="number" name="stock" value="<%= f.getStock() %>" class="input" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-guardar">Guardar Cambios</button>
                <a href="adminFunciones" class="btn btn-cancelar">Cancelar</a>
            </div>
        </form>
    </div>

</body>
</html>
