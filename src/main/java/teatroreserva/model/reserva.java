package teatroreserva.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public class reserva {
    private int id;
    private int funcionId;
    private int usuarioId;
    private int cantidad;
    private LocalDateTime fechaReserva;
    private String numeroOrden;


    // Datos extra para mostrar en la vista
    private String tituloObra;
    private Date fechaFuncion;
    private Time horaFuncion;
    private int sala;

    // Constructores
    public reserva() {}

    public reserva(int id, int funcionId, int usuarioId, int cantidad, LocalDateTime fechaReserva, 
                   String tituloObra, Date fechaFuncion, Time horaFuncion, int sala, String numeroOrden) {
        this.id = id;
        this.funcionId = funcionId;
        this.usuarioId = usuarioId;
        this.cantidad = cantidad;
        this.fechaReserva = fechaReserva;
        this.tituloObra = tituloObra;
        this.fechaFuncion = fechaFuncion;
        this.horaFuncion = horaFuncion;
        this.sala = sala;
        this.numeroOrden = numeroOrden;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFuncionId() { return funcionId; }
    public void setFuncionId(int funcionId) { this.funcionId = funcionId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFechaReserva() {return fechaReserva; }
    public void setFechaReserva(LocalDateTime fechaReserva) {this.fechaReserva = fechaReserva;}
    
    public String getTituloObra() { return tituloObra; }
    public void setTituloObra(String tituloObra) { this.tituloObra = tituloObra; }

    public Date getFechaFuncion() { return fechaFuncion; }
    public void setFechaFuncion(Date fechaFuncion) { this.fechaFuncion = fechaFuncion; }

    public Time getHoraFuncion() { return horaFuncion; }
    public void setHoraFuncion(Time horaFuncion) { this.horaFuncion = horaFuncion; }

    public int getSala() { return sala; }
    public void setSala(int sala) { this.sala = sala; }
    
    public String getNumeroOrden() {return numeroOrden; }
    public void setNumeroOrden(String numeroOrden) {this.numeroOrden = numeroOrden;}
}
