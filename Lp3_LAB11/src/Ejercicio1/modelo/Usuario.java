/*************************************************************************************
ARCHIVO : Usuario.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements IObservador {
    private String nombre;

    private List<MensajeBuzon> buzon;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.buzon = new ArrayList<>();
    }

    @Override
    public void actualizar(Notificacion notificacion) {

        buzon.add(new MensajeBuzon(notificacion));
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public List<MensajeBuzon> getBuzon() {
        return buzon;
    }

    public int getCantidadSinLeer() {
        return (int) buzon.stream().filter(m -> !m.isLeido()).count();
    }
}