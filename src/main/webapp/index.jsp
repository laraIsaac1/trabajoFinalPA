<%@ page import="java.sql.Connection" %>
<%@ page import="teatroreserva.database.DatabaseConnection" %>
<%@ page import="java.sql.SQLException" %>
<%
    try {
        Connection conn = DatabaseConnection.getConnection();
        conn.close(); // conexión exitosa, cerramos
        response.sendRedirect("login.jsp"); // redirigimos al login real
    } catch (SQLException e) {
        response.sendRedirect("error.jsp"); // base caída → mostrar error
    }
%>