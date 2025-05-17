package teatroreserva.model;

import java.util.List;

public class obra {
    private int id;
    private String titulo;
    private String descripcion;
    private String autor;
    private int duracion;        
    private String genero;

    // Constructor vacío
    public obra() {}

    // Constructor completo
    public obra(int id, String titulo, String descripcion, String autor, int duracion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.duracion = duracion;
        this.genero = genero;
       }
    
    private List<funcion> funciones;

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public List<funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<funcion> funciones) {
        this.funciones = funciones;
    }
}


