package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import teatroreserva.dao.obraDAO;
import teatroreserva.database.DatabaseConnection;
import teatroreserva.model.obra;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/adminObra")
public class adminObraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            obraDAO dao = new obraDAO(conn);
            List<obra> obras = dao.obtenerTodasLasObras();

            request.setAttribute("obras", obras);
            request.getRequestDispatcher("adminObras.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DatabaseConnection.getConnection()) {
            obraDAO dao = new obraDAO(conn);

            switch (action) {
                case "add":
                    obra nueva = new obra();
                    nueva.setTitulo(request.getParameter("titulo"));
                    nueva.setDescripcion(request.getParameter("descripcion"));
                    nueva.setAutor(request.getParameter("autor"));
                    nueva.setDuracion(Integer.parseInt(request.getParameter("duracion")));
                    nueva.setGenero(request.getParameter("genero"));

                    dao.insertarObra(nueva);
                    request.setAttribute("mensajeExito", "Obra agregada correctamente.");
                    break;

                case "update":
                    obra editada = new obra();
                    editada.setId(Integer.parseInt(request.getParameter("id")));
                    editada.setTitulo(request.getParameter("titulo"));
                    editada.setDescripcion(request.getParameter("descripcion"));
                    editada.setAutor(request.getParameter("autor"));
                    editada.setDuracion(Integer.parseInt(request.getParameter("duracion")));
                    editada.setGenero(request.getParameter("genero"));

                    dao.actualizarObra(editada);
                    request.setAttribute("mensajeExito", "Obra actualizada con éxito.");
                    break;

                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminarObra(id);
                    request.setAttribute("mensajeExito", "Obra eliminada correctamente.");
                    break;

                default:
                    request.setAttribute("mensajeError", "Acción no reconocida.");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Ocurrió un error al procesar la obra.");
        }

        // Reload con estado actualizado
        doGet(request, response);
    }
}
