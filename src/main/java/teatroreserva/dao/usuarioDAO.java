package teatroreserva.dao;

import teatroreserva.model.usuario;
import teatroreserva.model.usuario.tipousuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class usuarioDAO {
    
    private Connection conn;

    public usuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public List<usuario> getAllUsuarios() throws SQLException {
        List<usuario> usuarios = new LinkedList<>(); 
        String query = "SELECT * FROM usuarios";
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Convertir el valor de la columna "tipo" a TipoUsuario
            	tipousuario tipo = tipousuario.valueOf(rs.getString("role").toUpperCase());

                usuario usuario = new usuario(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nombre"),
                    tipo, // Usar el valor del enum en lugar de String
                    rs.getString("correo")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
    
    public usuario getUsuarioByCorreo(String correo) throws SQLException {
        usuario usuario = null;
        String query = "SELECT * FROM usuarios WHERE correo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        tipousuario.valueOf(rs.getString("role")),
                        rs.getString("correo")
                    );
                }
            }
        }
        return usuario;
    }

    
    public boolean registerUser(usuario newUser) throws SQLException {
        String query = "INSERT INTO usuarios (username, password, nombre, role, correo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {            
            stmt.setString(1, newUser.getUsername());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getNombre());
            stmt.setString(4, newUser.getRole().name()); // Convertir el enum a String
            stmt.setString(5, newUser.getCorreo());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean isEmailRegistered(String correo) throws SQLException {
        String query = "SELECT id FROM usuarios WHERE correo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Devuelve true si encuentra un registro
            }
        }
    }
    
    public usuario validarUsuario(String userInput, String password) throws SQLException {
        if (userInput == null || password == null) {
            System.out.println("ERROR: Username o password son nulos");
            return null;
        }

        System.out.println("Buscando usuario con: " + userInput + " y contraseña: " + password);

        // Buscar tanto por username como por correo
        String query = "SELECT * FROM usuarios WHERE (username = ? OR correo = ?) AND password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userInput);
            stmt.setString(2, userInput);
            stmt.setString(3, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Usuario encontrado en BD: " + rs.getString("username"));
                    return new usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        tipousuario.valueOf(rs.getString("role").toUpperCase()),
                        rs.getString("correo")
                    );
                } else {
                    System.out.println("Usuario NO encontrado en BD.");
                }
            }
        }
        return null;
    }






}
