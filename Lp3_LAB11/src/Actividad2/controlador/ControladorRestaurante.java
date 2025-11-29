/*************************************************************************************
ARCHIVO : ControladorRestaurante.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.controlador;

import Actividad2.modelo.*;
import Actividad2.vista.VistaRestaurante;

public class ControladorRestaurante {
    private ModeloRestaurante modelo;
    private VistaRestaurante vista; 
    private JefeCocinaInvoker invoker;


    public ControladorRestaurante(ModeloRestaurante modelo, VistaRestaurante vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.invoker = new JefeCocinaInvoker();
    }

    public void agregarPlato(String nombre, double precio) {
        modelo.agregarPlato(nombre, precio);
    }

    public void enviarPedidoACocina() {
        IComandoOrden cmd = new CmdEnviarCocina(modelo);
        invoker.recibirOrden(cmd);
    }

    public void cancelarPedido() {
        IComandoOrden cmd = new CmdCancelarPedido(modelo);
        invoker.recibirOrden(cmd);
    }

    public void deshacerUltimoCambio() {
        invoker.deshacerUltimaOrden();
    }

    public double calcularCuenta(int tipoCliente) {
        IEstrategiaFacturacion estrategia;
        
        switch (tipoCliente) {
            case 2:
                estrategia = new FacturaHoraFeliz();
                break;
            default:
                estrategia = new FacturaNormal();
                break;
        }
        return modelo.generarCuenta(estrategia);
    }

}