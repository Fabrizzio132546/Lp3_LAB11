package ejercicio2;

import java.util.List;

public class descuentoporcentualacumulado implements discountstrategy {

    public double aplicarDescuento(List<producto> productos) {

        if (productos.size() >= 3) {

            double total = 0;
            double masBarato = productos.get(0).getPrecio();

            for (producto p : productos) {

                total += p.getPrecio();

                if (p.getPrecio() < masBarato) { masBarato = p.getPrecio(); }
            }

            return total - (masBarato * 0.50);
        }

        double total = 0;

        for (producto p : productos) { total += p.getPrecio(); }

        return total;
    }

}
