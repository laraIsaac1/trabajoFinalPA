package teatroreserva.dao;

import teatroreserva.model.funcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class funcionDAO {
    private Connection conn;

    public funcionDAO(Connection conn) {
        this.conn = conn;
    }

    public funcion obtenerFuncionPorId(int id) throws SQLException {
        String query = "SELECT * FROM funciones WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new funcion(
                        rs.getInt("id"),
                        rs.getInt("obra_id"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getDouble("precio"),
                        rs.getInt("sala"),
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    public List<funcion> obtenerTodasLasFunciones() throws SQLException {
        List<funcion> funciones = new ArrayList<>();
        String query = "SELECT * FROM funciones";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                funcion funcion = new funcion(
                    rs.getInt("id"),
                    rs.getInt("obra_id"),
                    rs.getDate("fecha"),
                    rs.getTime("hora"),
                    rs.getDouble("precio"),
                    rs.getInt("sala"),
                    rs.getInt("stock")
                );
                funciones.add(funcion);
            }
        }
        return funciones;
    }

    public boolean insertarFuncion(funcion f) throws SQLException {
        String sql = "INSERT INTO funciones (obra_id, fecha, hora, precio, sala, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, f.getObraId());
            stmt.setDate(2, f.getFecha());
            stmt.setTime(3, f.getHora());
            stmt.setDouble(4, f.getPrecio());
            stmt.setInt(5, f.getSala());
            stmt.setInt(6, f.getStock());
            return stmt.executeUpdate() > 0;
        }
    }


    public boolean actualizarFuncion(funcion funcion) throws SQLException {
        String query = "UPDATE funciones SET obra_id = ?, fecha = ?, hora = ?, precio = ?, sala = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, funcion.getObraId());
            stmt.setDate(2, funcion.getFecha());
            stmt.setTime(3, funcion.getHora());
            stmt.setDouble(4, funcion.getPrecio());
            stmt.setInt(5, funcion.getSala());
            stmt.setInt(6, funcion.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean eliminarFuncion(int id) throws SQLException {
        String query = "DELETE FROM funciones WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<funcion> obtenerFuncionesPorObraId(int obraId) throws SQLException {
        List<funcion> funciones = new ArrayList<>();
        String query = "SELECT * FROM funciones WHERE obra_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, obraId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    funcion f = new funcion(
                        rs.getInt("id"),
                        rs.getInt("obra_id"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getDouble("precio"),
                        rs.getInt("sala"),
                        rs.getInt("stock")
                    );
                    funciones.add(f);
                }
            }
        }
        return funciones;
    }
    
    public List<funcion> obtenerFuncionesConTituloObra() throws SQLException {
        List<funcion> funciones = new ArrayList<>();
        String query = "SELECT f.*, o.titulo AS titulo_obra " +
                       "FROM funciones f JOIN obras o ON f.obra_id = o.id";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                funcion f = new funcion(
                    rs.getInt("id"),
                    rs.getInt("obra_id"),
                    rs.getDate("fecha"),
                    rs.getTime("hora"),
                    rs.getDouble("precio"),
                    rs.getInt("sala"),
                    rs.getInt("stock")
                );
                f.setTituloObra(rs.getString("titulo_obra")); // Método adicional en modelo
                funciones.add(f);
            }
        }
        return funciones;
    }



}
