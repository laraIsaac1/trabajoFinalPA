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
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/adminFunciones")
public class adminFuncionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            funcionDAO funcionDAO = new funcionDAO(conn);
            obraDAO obraDAO = new obraDAO(conn);

            List<funcion> funciones = funcionDAO.obtenerTodasLasFunciones();
            List<obra> obras = obraDAO.obtenerTodasLasObras();

            request.setAttribute("funciones", funciones);
            request.setAttribute("obras", obras);
            request.getRequestDispatcher("adminFunciones.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DatabaseConnection.getConnection()) {
            funcionDAO funcionDAO = new funcionDAO(conn);

            if ("add".equals(action)) {
                funcion nueva = new funcion();
                nueva.setObraId(Integer.parseInt(request.getParameter("obraId")));
                nueva.setFecha(Date.valueOf(request.getParameter("fecha")));

                String horaStr = request.getParameter("hora");
                if (horaStr.length() == 5) horaStr += ":00";
                nueva.setHora(Time.valueOf(horaStr));

                nueva.setPrecio(Double.parseDouble(request.getParameter("precio")));
                nueva.setSala(Integer.parseInt(request.getParameter("sala")));
                nueva.setStock(Integer.parseInt(request.getParameter("stock")));

                funcionDAO.insertarFuncion(nueva);
                request.setAttribute("mensajeExito", "Función agregada correctamente.");

            } else if ("update".equals(action)) {
                funcion actualizada = new funcion();
                actualizada.setId(Integer.parseInt(request.getParameter("id")));
                actualizada.setObraId(Integer.parseInt(request.getParameter("obraId")));
                actualizada.setFecha(Date.valueOf(request.getParameter("fecha")));

                String horaStr = request.getParameter("hora");
                if (horaStr.length() == 5) horaStr += ":00";
                actualizada.setHora(Time.valueOf(horaStr));

                actualizada.setPrecio(Double.parseDouble(request.getParameter("precio")));
                actualizada.setSala(Integer.parseInt(request.getParameter("sala")));
                actualizada.setStock(Integer.parseInt(request.getParameter("stock")));

                funcionDAO.actualizarFuncion(actualizada);
                request.setAttribute("mensajeExito", "Función actualizada con éxito.");

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                funcionDAO.eliminarFuncion(id);
                request.setAttribute("mensajeExito", "Función eliminada correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Ocurrió un error al procesar la función.");
        }

        // Recargar la vista actualizada
        doGet(request, response);
    }
}
