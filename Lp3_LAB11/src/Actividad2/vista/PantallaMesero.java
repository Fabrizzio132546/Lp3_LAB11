/*************************************************************************************
ARCHIVO : PantallaMesero.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.vista;

import Actividad2.modelo.IObserverRestaurante;
import Actividad2.modelo.ModeloRestaurante;

public class PantallaMesero implements IObserverRestaurante {
    private String ubicacion;

    public PantallaMesero(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public void actualizar(String mensaje, ModeloRestaurante modelo) {
        System.out.println("\n*** NOTIFICACIÓN EN " + ubicacion + " ***");
        System.out.println(">> Mensaje: " + mensaje);
        System.out.println(">> Estado actual: " + modelo.getEstadoOrden());
        System.out.println(">> Platos en orden: " + modelo.getPedidoActual().getPlatos().size());
        System.out.println("******************************************\n");
    }
}