package ejercicio2;

import java.util.*;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        calculadoradeprecios calc = new calculadoradeprecios();
        List<producto> productos = new ArrayList<>();

        System.out.println("**********");
        System.out.println("menu descuentos");
        System.out.println("**********");

        System.out.print("cuantos productos desea ingresar: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("nombre producto: ");
            String nombre = sc.nextLine();

            System.out.print("precio: ");
            double precio = sc.nextDouble();
            sc.nextLine();

            productos.add(new producto(nombre, precio));
        }

        System.out.println("\nelige estrategia:");
        System.out.println("1. sin descuento");
        System.out.println("2. descuento fijo 10 por ciento");
        System.out.println("3. descuento por dos iguales 30 por ciento");
        System.out.println("4. descuento acumulado 50 por ciento al mas barato desde 3 productos");
        System.out.print("opcion: ");

        int op = sc.nextInt();

        switch (op) {

            case 1: calc.setStrategy(new sindescuento()); break;
            case 2: calc.setStrategy(new descuentofijo()); break;
            case 3: calc.setStrategy(new descuentoporcentual()); break;
            case 4: calc.setStrategy(new descuentoporcentualacumulado()); break;

            default: System.out.println("opcion no valida"); return;
        }

        double total = calc.calcular(productos);

        System.out.println("**********");
        System.out.println("precio final: " + total);
        System.out.println("**********");
    }

}
