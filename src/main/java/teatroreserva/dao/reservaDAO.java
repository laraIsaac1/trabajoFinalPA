package teatroreserva.dao;

import teatroreserva.model.reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class reservaDAO {
    private Connection conn;

    public reservaDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean crearReserva(reserva reserva) throws SQLException {
        String sql = "INSERT INTO reservas (usuario_id, funcion_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getUsuarioId());
            stmt.setInt(2, reserva.getFuncionId());
            return stmt.executeUpdate() > 0;
        }
    }
       
       
    public boolean guardarReserva(int funcionId, int usuarioId, int cantidad) throws SQLException {
    	
    	String numeroOrden = "ORD-" + System.currentTimeMillis();
    	 String sql = "INSERT INTO reservas (funcion_id, usuario_id, cantidad, numero_orden) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, funcionId);
            stmt.setInt(2, usuarioId);
            stmt.setInt(3, cantidad);
            stmt.setString(4, numeroOrden);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public int obtenerStockFuncion(int funcionId) throws SQLException {
        String sql = "SELECT stock FROM funciones WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, funcionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }
        }
        return 0;
    }

    public void descontarStock(int funcionId, int cantidad) throws SQLException {
        String sql = "UPDATE funciones SET stock = stock - ? WHERE id = ? AND stock >= ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, funcionId);
            stmt.setInt(3, cantidad);
            stmt.executeUpdate();
        }
    }
    public List<reserva> obtenerReservasPorUsuario(int usuarioId) throws SQLException {
        List<reserva> reservas = new ArrayList<>();

        String query = "SELECT r.*, f.fecha, f.hora, f.sala, o.titulo AS titulo_obra " +
                "FROM reservas r " +
                "JOIN funciones f ON r.funcion_id = f.id " +
                "JOIN obras o ON f.obra_id = o.id " +
                "WHERE r.usuario_id = ?";



        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reserva r = new reserva();
                    r.setId(rs.getInt("id"));
                    r.setUsuarioId(rs.getInt("usuario_id"));
                    r.setFuncionId(rs.getInt("funcion_id"));
                    r.setCantidad(rs.getInt("cantidad"));
                    r.setFechaReserva(rs.getTimestamp("fecha_reserva").toLocalDateTime());
                    r.setNumeroOrden(rs.getString("numero_orden"));

                    // Datos adicionales para mostrar
                    r.setTituloObra(rs.getString("titulo_obra"));
                    r.setFechaFuncion(rs.getDate("fecha"));
                    r.setHoraFuncion(rs.getTime("hora"));
                    r.setSala(rs.getInt("sala"));

                    reservas.add(r);
                }
            }
        }

        return reservas;
    }

    public int obtenerUltimaReservaId(int usuarioId) throws SQLException {
        String sql = "SELECT id FROM reservas WHERE usuario_id = ? ORDER BY fecha_reserva DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }

    public String obtenerNumeroOrdenPorUsuario(int usuarioId) throws SQLException {
        String sql = "SELECT numero_orden FROM reservas WHERE usuario_id = ? ORDER BY fecha_reserva DESC LIMIT 1";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("numero_orden");
                }
            }
        }
        return null;
    }

}
