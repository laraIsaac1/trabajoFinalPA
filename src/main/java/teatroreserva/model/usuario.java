package teatroreserva.model;

public class usuario {
    private int id;
    private String nombre;
    private String username;
    private String password;
    private tipousuario role;
    private String correo;
	
    
    public enum tipousuario {
        ADMIN, USER
    }

    // Constructor, getters y setters
    public usuario(int id, String username, String password ,String nombre, tipousuario role, String correo) {
        this.id = id;
        this.username= username;
        this.nombre = nombre;
        this.role = role;
        this.password = password;
        this.correo = correo;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre()  { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public tipousuario getRole() { return role; }
    public void setRole(tipousuario role) { this.role = role; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
        
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
