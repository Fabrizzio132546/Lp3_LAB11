/*************************************************************************************
ARCHIVO : MensaheBuzon.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.modelo;

public class MensajeBuzon {
    private Notificacion notificacion;
    private boolean leido;

    public MensajeBuzon(Notificacion notificacion) {
        this.notificacion = notificacion;
        this.leido = false; 
    }

    public void marcarComoLeido() {
        this.leido = true;
    }

    public boolean isLeido() {
        return leido;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }
}