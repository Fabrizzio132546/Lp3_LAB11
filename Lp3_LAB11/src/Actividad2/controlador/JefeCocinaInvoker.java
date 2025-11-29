/*************************************************************************************
ARCHIVO : JefeCocinaInvoker.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.controlador;

import java.util.Stack;

public class JefeCocinaInvoker {
    private Stack<IComandoOrden> historial;

    public JefeCocinaInvoker() {
        this.historial = new Stack<>();
    }

    public void recibirOrden(IComandoOrden comando) {
        comando.ejecutar();
        historial.push(comando);
    }

    public void deshacerUltimaOrden() {
        if (!historial.isEmpty()) {
            IComandoOrden ultimo = historial.pop();
            ultimo.deshacer();
        } else {
            System.out.println("[INFO] No hay acciones para deshacer.");
        }
    }
}