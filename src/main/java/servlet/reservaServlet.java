package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import teatroreserva.dao.funcionDAO;
import teatroreserva.dao.obraDAO;
import teatroreserva.database.DatabaseConnection;
import teatroreserva.model.funcion;
import teatroreserva.model.obra;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reserva")
public class reservaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int obraId = Integer.parseInt(request.getParameter("obraId"));

        try (Connection conn = DatabaseConnection.getConnection()) {
            obraDAO obraDAO = new obraDAO(conn);
            funcionDAO funcionDAO = new funcionDAO(conn);

            obra obraActual = obraDAO.obtenerObraPorId(obraId);
            List<funcion> funciones = funcionDAO.obtenerFuncionesPorObraId(obraId);

            boolean agotadas = funciones.isEmpty() || funciones.stream().allMatch(f -> f.getStock() == 0);

            request.setAttribute("obra", obraActual);
            request.setAttribute("funciones", funciones);
            request.setAttribute("agotadas", agotadas);

            request.getRequestDispatcher("reserva.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

