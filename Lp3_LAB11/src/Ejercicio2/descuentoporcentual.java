package ejercicio2;

import java.util.List;

public class descuentoporcentual implements discountstrategy {

    public double aplicarDescuento(List<producto> productos) {

        if (productos.size() == 2 && productos.get(0).getNombre().equalsIgnoreCase(productos.get(1).getNombre())) {

            double total = productos.get(0).getPrecio() + productos.get(1).getPrecio();

            return total * 0.70;
        }

        double total = 0;

        for (producto p : productos) { total += p.getPrecio(); }

        return total;
    }

}
