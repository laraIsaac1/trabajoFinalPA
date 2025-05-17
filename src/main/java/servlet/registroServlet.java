package servlet;

import teatroreserva.dao.usuarioDAO;
import teatroreserva.database.DatabaseConnection;
import teatroreserva.model.usuario;
import teatroreserva.model.usuario.tipousuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/registroServlet")
public class registroServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");

        // Definir el rol por defecto como "USER"
        tipousuario role = tipousuario.USER;

        try (Connection conn = DatabaseConnection.getConnection()) {
            usuarioDAO usuarioDAO = new usuarioDAO(conn);

            // Verificar si el correo ya existe
            if (usuarioDAO.isEmailRegistered(correo)) {
                // Si el correo ya existe, redirigir a registro.jsp con un mensaje de error
                response.sendRedirect("registro.jsp?error=emailExists");
            } else {
                // Crear un nuevo objeto usuario
                usuario newUser = new usuario(0, username, password, nombre, role, correo);

                // Insertar el nuevo usuario en la base de datos
                boolean isRegistered = usuarioDAO.registerUser(newUser);

                if (isRegistered) {
                    // Si el registro es exitoso, redirigir a registro.jsp con éxito en la URL
                    response.sendRedirect("registro.jsp?success=registered");
                } else {
                    // Si falla el registro, redirigir con mensaje de error
                    response.sendRedirect("registro.jsp?error=registrationFailed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de excepción, redirigir a registro.jsp con un mensaje de error general
            response.sendRedirect("registro.jsp?error=exception");
        }
    }
}
