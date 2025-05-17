package teatroreserva.dao;

import teatroreserva.model.funcion;
import teatroreserva.model.obra;

import java.sql.*;
import java.util.*;

public class obraDAO {
    private Connection conn;

    public obraDAO(Connection conn) {
        this.conn = conn;
    }

    // Obtener todas las obras con sus funciones
    public Map<obra, List<funcion>> getObrasConFunciones() throws SQLException {
        Map<Integer, obra> obrasIndex = new LinkedHashMap<>();
        Map<obra, List<funcion>> obrasConFunciones = new LinkedHashMap<>();

        String sql = "SELECT o.id, o.titulo, o.descripcion, o.autor, o.duracion, o.genero, " +
                     "f.id AS funcion_id, f.fecha, f.hora, f.precio, f.sala, f.stock " +
                     "FROM obras o LEFT JOIN funciones f ON o.id = f.obra_id " +
                     "ORDER BY o.id, f.fecha";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int obraId = rs.getInt("id");

                obra obraActual = obrasIndex.get(obraId);
                if (obraActual == null) {
                    obraActual = new obra(
                            obraId,
                            rs.getString("titulo"),
                            rs.getString("descripcion"),
                            rs.getString("autor"),
                            rs.getInt("duracion"),
                            rs.getString("genero")
                    );
                    obrasIndex.put(obraId, obraActual);
                    obrasConFunciones.put(obraActual, new ArrayList<>());
                }

                int funcionId = rs.getInt("funcion_id");
                if (funcionId > 0) {
                    funcion f = new funcion(
                            funcionId,
                            obraId,
                            rs.getDate("fecha"),
                            rs.getTime("hora"),
                            rs.getDouble("precio"),
                            rs.getInt("sala"),
                            rs.getInt("stock")
                    );
                    obrasConFunciones.get(obraActual).add(f);
                }
            }
        }
        return obrasConFunciones;
    }

    // Obtener una obra por ID
    public obra obtenerObraPorId(int id) throws SQLException {
        String query = "SELECT * FROM obras WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new obra(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("autor"),
                        rs.getInt("duracion"),
                        rs.getString("genero")
                    );
                }
            }
        }
        return null;
    }

    // Obtener todas las obras sin funciones
    public List<obra> obtenerTodasLasObras() throws SQLException {
        List<obra> lista = new ArrayList<>();
        String sql = "SELECT * FROM obras";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new obra(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("autor"),
                    rs.getInt("duracion"),
                    rs.getString("genero")
                ));
            }
        }
        return lista;
    }


    // Obtener las funciones de una obra específica
    public List<funcion> obtenerFuncionesPorObraId(int obraId) throws SQLException {
        List<funcion> funciones = new ArrayList<>();
        String query = "SELECT * FROM funciones WHERE obra_id = ? ORDER BY fecha, hora";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, obraId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    funcion nuevaFuncion = new funcion(
                            rs.getInt("id"),
                            rs.getInt("obra_id"),
                            rs.getDate("fecha"),
                            rs.getTime("hora"),
                            rs.getDouble("precio"),
                            rs.getInt("sala"),
                            rs.getInt("stock")
                    );
                    funciones.add(nuevaFuncion);
                }
            }
        }
        return funciones;
    }
    
    public obra obtenerObraPorFuncionId(int funcionId) throws SQLException {
        String query = "SELECT o.* FROM obras o " +
                       "JOIN funciones f ON o.id = f.obra_id " +
                       "WHERE f.id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, funcionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new obra(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("autor"),
                        rs.getInt("duracion"),
                        rs.getString("genero")
                    );
                }
            }
        }
        return null;
    }

    
    
    
    //Panel Administrador 
    // Insertar una nueva obra
    public boolean insertarObra(obra o) throws SQLException {
        String sql = "INSERT INTO obras (titulo, descripcion, autor, duracion, genero) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, o.getTitulo());
            stmt.setString(2, o.getDescripcion());
            stmt.setString(3, o.getAutor());
            stmt.setInt(4, o.getDuracion());
            stmt.setString(5, o.getGenero());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean eliminarObra(int id) throws SQLException {
        String sql = "DELETE FROM obras WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
   
    public boolean actualizarObra(obra o) throws SQLException {
        String sql = "UPDATE obras SET titulo=?, descripcion=?, autor=?, duracion=?, genero=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, o.getTitulo());
            stmt.setString(2, o.getDescripcion());
            stmt.setString(3, o.getAutor());
            stmt.setInt(4, o.getDuracion());
            stmt.setString(5, o.getGenero());
            stmt.setInt(6, o.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    
    
}
