/*************************************************************************************
ARCHIVO : Pedido.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Plato> platos;
    private int numeroMesa;

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.platos = new ArrayList<>();
    }

    public void agregarPlato(Plato plato) {
        platos.add(plato);
    }

    public List<Plato> getPlatos() {
        return new ArrayList<>(platos);
    }

    public double calcularSubtotal() {
        return platos.stream().mapToDouble(Plato::getPrecio).sum();
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }
}