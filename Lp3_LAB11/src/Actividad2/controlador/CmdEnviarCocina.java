/*************************************************************************************
ARCHIVO : CmdEnviarCocina.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.controlador;

import Actividad2.modelo.ModeloRestaurante;

public class CmdEnviarCocina implements IComandoOrden {
    private ModeloRestaurante modelo;
    private String estadoAnterior;

    public CmdEnviarCocina(ModeloRestaurante modelo) {
        this.modelo = modelo;
    }

    @Override
    public void ejecutar() {
        this.estadoAnterior = modelo.getEstadoOrden();
        modelo.setEstadoOrden("En Preparación");
    }

    @Override
    public void deshacer() {
        if (estadoAnterior != null) {
            modelo.setEstadoOrden(estadoAnterior);
        }
    }
}