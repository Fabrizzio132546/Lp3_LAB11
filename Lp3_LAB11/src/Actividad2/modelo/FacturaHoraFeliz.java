/*************************************************************************************
ARCHIVO : FacturaHoraFeliz.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.modelo;

public class FacturaHoraFeliz implements IEstrategiaFacturacion {
    @Override
    public double calcularTotal(double montoBase) {
        return montoBase * 0.80; 
    }
}