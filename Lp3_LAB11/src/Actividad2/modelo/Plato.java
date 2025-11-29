/*************************************************************************************
ARCHIVO : Plato.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.modelo;

public class Plato {
    private String nombre;
    private double precio;

    public Plato(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return String.format("%s ($%.2f)", nombre, precio);
    }
}