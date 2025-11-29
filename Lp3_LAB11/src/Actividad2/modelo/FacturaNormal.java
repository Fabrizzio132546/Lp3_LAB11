/*************************************************************************************
ARCHIVO : FacturaNormal.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.modelo;

public class FacturaNormal implements IEstrategiaFacturacion {
    @Override
    public double calcularTotal(double montoBase) {
        return montoBase;
    }
}