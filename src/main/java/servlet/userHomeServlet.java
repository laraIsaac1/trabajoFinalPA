package servlet;

import teatroreserva.dao.funcionDAO;
import teatroreserva.dao.obraDAO;
import teatroreserva.database.DatabaseConnection;
import teatroreserva.model.funcion;
import teatroreserva.model.obra;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userHome")
public class userHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            obraDAO obraDAO = new obraDAO(conn);
            funcionDAO funcionDAO = new funcionDAO(conn);

            List<obra> obras = obraDAO.obtenerTodasLasObras();
            Map<Integer, List<funcion>> funcionesPorObra = new HashMap<>();

            for (obra o : obras) {
                List<funcion> funciones = funcionDAO.obtenerFuncionesPorObraId(o.getId());
                funcionesPorObra.put(o.getId(), funciones);
            }

            request.setAttribute("obras", obras);
            request.setAttribute("funcionesPorObra", funcionesPorObra);

            request.getRequestDispatcher("userHome.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
