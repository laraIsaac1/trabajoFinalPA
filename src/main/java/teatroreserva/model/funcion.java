package teatroreserva.model;

import java.sql.Date;
import java.sql.Time;

public class funcion {
    private int id;
    private int obraId;         // ID de la obra asociada
    private Date fecha;         // Fecha de la función
    private Time hora;          // Hora de la función
    private double precio;      // Precio de la entrada
    private int sala;
    private int stock;

    // Constructor vacío
    public funcion() {}

    // Constructor completo
    public funcion(int id, int obraId, Date fecha, Time hora, double precio, int sala, int stock) {
        this.id = id;
        this.obraId = obraId;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.sala = sala;
        this.stock = stock;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObraId() {
        return obraId;
    }

    public void setObraId(int obraId) {
        this.obraId = obraId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    private String tituloObra;

    public String getTituloObra() {
        return tituloObra;
    }

    public void setTituloObra(String tituloObra) {
        this.tituloObra = tituloObra;
    }

}
