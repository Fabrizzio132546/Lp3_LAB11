package ejercicio3;

import java.util.Stack;

public class controlremoto {

    private Stack<comando> historial = new Stack<>();

    public void ejecutar(comando c) {
        c.ejecutar();
        historial.push(c);
    }

    public void deshacer() {
        if (historial.isEmpty()) {
            System.out.println("no hay acciones para deshacer");
        } else {
            comando ultimo = historial.pop();
            ultimo.deshacer();
        }
    }

}
