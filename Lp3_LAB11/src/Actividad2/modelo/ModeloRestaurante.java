/*************************************************************************************
ARCHIVO : ModeloRestaurante.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloRestaurante {
    private Pedido pedidoActual;
    private String estadoOrden;
    private List<IObserverRestaurante> observadores;

    public ModeloRestaurante() {
        this.pedidoActual = new Pedido(1); 
        this.estadoOrden = "Pendiente";
        this.observadores = new ArrayList<>();
    }

    public void agregarPlato(String nombre, double precio) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El nombre del plato no puede estar vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("Error: El precio debe ser mayor a 0.");
        }
        if (precio > 10000) { 
            throw new IllegalArgumentException("Error: Precio sospechosamente alto. Verifique.");
        }

        pedidoActual.agregarPlato(new Plato(nombre, precio));
        notificarObservadores("Nuevo plato agregado: " + nombre);
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String nuevoEstado) {
        this.estadoOrden = nuevoEstado;
        notificarObservadores("El estado del pedido cambió a: " + nuevoEstado);
    }

    public double generarCuenta(IEstrategiaFacturacion estrategia) {
        double subtotal = pedidoActual.calcularSubtotal();
        return estrategia.calcularTotal(subtotal);
    }

    public void agregarObservador(IObserverRestaurante obs) {
        observadores.add(obs);
    }

    private void notificarObservadores(String mensaje) {
        for (IObserverRestaurante obs : observadores) {
            obs.actualizar(mensaje, this);
        }
    }
}