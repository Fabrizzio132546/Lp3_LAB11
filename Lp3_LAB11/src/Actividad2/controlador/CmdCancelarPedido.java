/*************************************************************************************
ARCHIVO : CmdCancelarPedido.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.controlador;

import Actividad2.modelo.ModeloRestaurante;

public class CmdCancelarPedido implements IComandoOrden {
    private ModeloRestaurante modelo;
    private String estadoAnterior;

    public CmdCancelarPedido(ModeloRestaurante modelo) {
        this.modelo = modelo;
    }

    @Override
    public void ejecutar() {
        this.estadoAnterior = modelo.getEstadoOrden();
        modelo.setEstadoOrden("Cancelado");
    }

    @Override
    public void deshacer() {
        if (estadoAnterior != null) {
            modelo.setEstadoOrden(estadoAnterior);
        }
    }
}