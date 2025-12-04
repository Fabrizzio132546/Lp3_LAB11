package ejercicio2;

import java.util.List;

public class calculadoradeprecios {

    private discountstrategy strategy;

    public void setStrategy(discountstrategy strategy) { this.strategy = strategy; }

    public double calcular(List<producto> productos) {

        if (strategy == null) { throw new IllegalArgumentException("debe seleccionar una estrategia antes de calcular"); }

        return strategy.aplicarDescuento(productos);
    }

}
