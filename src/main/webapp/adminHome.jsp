<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
</head>
<body class="admin-container">
	<!-- Links arriba a la derecha -->
	<%
	    if (session != null && session.getAttribute("usuarioId") != null) {
	%>
	<nav class="navbar">
	   <a href="logout" class="nav-link logout">Cerrar Sesión</a>
	</nav>
	<hr>
	<% } %>

    <h2 class="admin-title">Panel de Administración</h2> 
    
    <div class="admin-cards">
    
        <div class="admin-card">
             <h5 class="title">Administrar Obras</h5>
             <p class="card-text">Agregar, modificar o eliminar obras disponibles.</p>
             <a href="adminObra" class="btn-admin">Ir a Obras</a>
       </div>

        <div class="admin-card">
            <h5 class="title">Administrar Funciones</h5>
            <p class="card-text">Gestionar fechas, horarios, salas y precios.</p>
            <a href="adminFunciones" class="btn-admin">Ir a Funciones</a>
        </div>
   </div>
</body>
</html>
