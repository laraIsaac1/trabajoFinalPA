package servlet;

import teatroreserva.dao.usuarioDAO;
import teatroreserva.database.DatabaseConnection;
import teatroreserva.model.usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("username");
        String password = request.getParameter("password");

        if (userInput == null || password == null || userInput.trim().isEmpty() || password.trim().isEmpty()) {
            response.sendRedirect("login.jsp?error=empty");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            usuarioDAO usuarioDAO = new usuarioDAO(conn);
            usuario user = usuarioDAO.validarUsuario(userInput.trim(), password.trim());

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);
                session.setAttribute("usuarioId", user.getId());

                if (user.getRole() == usuario.tipousuario.ADMIN) {
                    response.sendRedirect("adminHome.jsp");
                } else {
                    response.sendRedirect("obras"); // siempre mandamos al servlet, no al JSP directo
                }
            } else {
                response.sendRedirect("login.jsp?error=invalid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=exception");
        }
    }
}
