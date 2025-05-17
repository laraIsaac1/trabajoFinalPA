package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import teatroreserva.dao.funcionDAO;
import teatroreserva.dao.obraDAO;
import teatroreserva.dao.reservaDAO;
import teatroreserva.database.DatabaseConnection;
import teatroreserva.model.funcion;
import teatroreserva.model.obra;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/compra")
public class compraServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int funcionId = Integer.parseInt(request.getParameter("funcionId"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int usuarioId = (int) session.getAttribute("usuarioId");

            try (Connection conn = DatabaseConnection.getConnection()) {
                reservaDAO reservaDAO = new reservaDAO(conn);
                obraDAO obraDAO = new obraDAO(conn);
                funcionDAO funcionDAO = new funcionDAO(conn);

                int stockActual = reservaDAO.obtenerStockFuncion(funcionId);

                if (stockActual >= cantidad) {
                    boolean reservaOk = reservaDAO.guardarReserva(funcionId, usuarioId, cantidad);
                    if (reservaOk) {
                        reservaDAO.descontarStock(funcionId, cantidad);
                        int idReserva = reservaDAO.obtenerUltimaReservaId(usuarioId);
                        String numeroOrden = reservaDAO.obtenerNumeroOrdenPorUsuario(usuarioId);
                        response.sendRedirect("confirmacion.jsp?reservaId=" + idReserva + "&numeroOrden=" + numeroOrden);
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } else {
                    // Sin stock, vuelve a la vista reserva.jsp con el cartel
                    funcion funcion = funcionDAO.obtenerFuncionPorId(funcionId);
                    obra obraSeleccionada = obraDAO.obtenerObraPorId(funcion.getObraId());
                    List<funcion> funciones = funcionDAO.obtenerFuncionesPorObraId(obraSeleccionada.getId());

                    request.setAttribute("error", "sinStock");
                    request.setAttribute("obra", obraSeleccionada);
                    request.setAttribute("funciones", funciones);
                    request.getRequestDispatcher("reserva.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
