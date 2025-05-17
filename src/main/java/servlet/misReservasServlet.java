package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import teatroreserva.dao.reservaDAO;
import teatroreserva.database.DatabaseConnection;
import teatroreserva.model.reserva;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/misReservas")
public class misReservasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int usuarioId = (int) session.getAttribute("usuarioId");

        try (Connection conn = DatabaseConnection.getConnection()) {
            reservaDAO reservaDAO = new reservaDAO(conn);
            List<reserva> reservas = reservaDAO.obtenerReservasPorUsuario(usuarioId);

            request.setAttribute("reservas", reservas);
            request.getRequestDispatcher("misReservas.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
