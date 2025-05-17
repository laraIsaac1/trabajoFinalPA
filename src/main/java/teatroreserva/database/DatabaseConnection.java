package teatroreserva.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	
		private static final String URL = "jdbc:mysql://localhost:3306/teatro_reserva";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

	    public static Connection getConnection() throws SQLException {
	        Connection conn = null;
	        try {
	            // Cargar el driver de MySQL
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            // Establecer la conexión con la base de datos
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Conexión establecida con éxito.");
	        } catch (ClassNotFoundException e) {
	            // Error si no se encuentra el driver
	            System.err.println("Error: No se pudo cargar el driver de MySQL.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            // Error si la conexión falla
	            System.err.println("Error: No se pudo conectar a la base de datos.");
	            System.err.println("Verifica que el servidor de MySQL esté en ejecución.");
	            e.printStackTrace();
	            throw e; // Lanzar la excepción para que el código que llama a este método pueda manejarla
	        }
	        return conn;
	    }
	}


