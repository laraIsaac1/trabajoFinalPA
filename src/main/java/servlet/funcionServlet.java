package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import teatroreserva.dao.funcionDAO;
import teatroreserva.database.DatabaseConnection;

@WebServlet("/FuncionServlet")
public class funcionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try (Connection conn = DatabaseConnection.getConnection()) {
            funcionDAO funcionDAO = new funcionDAO(conn);

            if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                funcionDAO.eliminarFuncion(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("adminHome.jsp");
    }
}
