/*************************************************************************************
ARCHIVO : Main.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.app;

import Actividad2.controlador.ControladorRestaurante;
import Actividad2.modelo.ModeloRestaurante;
import Actividad2.vista.VistaRestaurante;

public class Main {
    public static void main(String[] args) {
        ModeloRestaurante modelo = new ModeloRestaurante();
        VistaRestaurante vista = new VistaRestaurante(modelo);
        ControladorRestaurante controlador = new ControladorRestaurante(modelo, vista);

        vista.setControlador(controlador);
        vista.iniciar();
    }
}