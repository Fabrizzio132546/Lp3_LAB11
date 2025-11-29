/*************************************************************************************
ARCHIVO : Notificacion.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.modelo;

import java.time.LocalDateTime;

public class Notificacion {
    private String mensaje;
    private Categoria categoria;
    private Prioridad prioridad;
    private LocalDateTime fechaHora;

    public Notificacion(String mensaje, Categoria categoria, Prioridad prioridad) {
        this.mensaje = mensaje;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.fechaHora = LocalDateTime.now();
    }

    // Getters
    public String getMensaje() { return mensaje; }
    public Categoria getCategoria() { return categoria; }
    public Prioridad getPrioridad() { return prioridad; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", categoria, prioridad, mensaje);
    }
}