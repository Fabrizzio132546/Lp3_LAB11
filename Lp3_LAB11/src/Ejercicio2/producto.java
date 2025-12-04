package ejercicio2;

public class producto {

    private String nombre;
    private double precio;

    public producto(String nombre, double precio) {

        if (precio <= 0) { throw new IllegalArgumentException("el precio debe ser mayor a cero"); }

        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }

    public double getPrecio() { return precio; }

}
