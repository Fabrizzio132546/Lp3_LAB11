package ejercicio2;

import java.util.List;

public class descuentofijo implements discountstrategy {

    public double aplicarDescuento(List<producto> productos) {

        double total = 0;

        for (producto p : productos) { total += p.getPrecio(); }

        return total * 0.90;
    }

}
