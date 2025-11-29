/*************************************************************************************
ARCHIVO : Main.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.app;
import Ejercicio1.vista.SistemaVista;
import Ejercicio1.controlador.SistemaControlador;
import Ejercicio1.modelo.SistemaModelo;

public class Main {
    public static void main(String[] args) {
        SistemaModelo modelo = new SistemaModelo();

        SistemaVista vista = new SistemaVista();

        SistemaControlador controlador = new SistemaControlador(modelo, vista);

        controlador.iniciar();
    }
}