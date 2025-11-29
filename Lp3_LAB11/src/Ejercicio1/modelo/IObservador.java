/*************************************************************************************
ARCHIVO : IObservador.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.modelo;

public interface IObservador {
    void actualizar(Notificacion notificacion);
    String getNombre();
}