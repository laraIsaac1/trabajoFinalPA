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
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DatabaseConnection.getConnection()) {
            funcionDAO funcionDAO = new funcionDAO(conn);

            if ("add".equals(action)) {
                int obraId = Integer.parseInt(request.getParameter("obraId"));
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                Time hora = Time.valueOf(normalizarHora(request.getParameter("hora")));
                double precio = Double.parseDouble(request.getParameter("precio"));
                int sala = Integer.parseInt(request.getParameter("sala"));
                int stock = Integer.parseInt(request.getParameter("stock"));

                if (sala <= 0 || stock < 0 || precio < 0) {
                    request.setAttribute("mensajeError", "Sala, stock y precio deben ser positivos.");
                } else if (funcionDAO.existeFuncion(sala, fecha, hora)) {
                    request.setAttribute("mensajeError", "Ya existe una función en esa sala, fecha y hora.");
                } else {
                    funcion nueva = new funcion(0, obraId, fecha, hora, precio, sala, stock);
                    funcionDAO.insertarFuncion(nueva);
                    request.setAttribute("mensajeExito", "Función agregada correctamente.");
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int obraId = Integer.parseInt(request.getParameter("obraId"));
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                Time hora = Time.valueOf(normalizarHora(request.getParameter("hora")));
                double precio = Double.parseDouble(request.getParameter("precio"));
                int sala = Integer.parseInt(request.getParameter("sala"));

                if (sala <= 0 || precio < 0) {
                    request.setAttribute("mensajeError", "Sala y precio deben ser positivos.");
                } else if (funcionDAO.existeFuncion(sala, fecha, hora, id)) {
                    request.setAttribute("mensajeError", "Otra función ya ocupa esa sala, fecha y hora.");
                } else {
                	funcion actualizada = new funcion(id, obraId, fecha, hora, precio, sala, 0);

                    funcionDAO.actualizarFuncion(actualizada);
                    request.setAttribute("mensajeExito", "Función actualizada con éxito.");
                }

            } else if ("delete".equals(action)) {
                String idStr = request.getParameter("id");
                if (idStr != null && !idStr.isEmpty()) {
                    int id = Integer.parseInt(idStr);
                    funcionDAO.eliminarFuncion(id);
                    request.setAttribute("mensajeExito", "Función eliminada correctamente.");
                } else {
                    request.setAttribute("mensajeError", "ID no válido para eliminar.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Ocurrió un error al procesar la función.");
        }

        doGet(request, response);
    }

    private String normalizarHora(String horaStr) {
        if (horaStr != null) {
            if (horaStr.length() == 5) {
                return horaStr + ":00";
            }
        }
        return horaStr;
    }
}