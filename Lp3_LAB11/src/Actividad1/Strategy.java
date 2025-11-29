/*************************************************************************************
ARCHIVO : Strategy.java
FECHA   : 25/11/2025
*************************************************************************************/
package Actividad1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException; 

class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
}

interface EstrategiaDescuento {
    double calcularTotal(List<Producto> productos);
}

class SinDescuento implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        System.out.println("Aplicando: Sin Descuento");
        return total;
    }
}

class DescuentoFijo implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        System.out.println("Aplicando: Descuento Fijo del 10 por ciento");
        return total * 0.90;
    }
}

class DescuentoAvanzado implements EstrategiaDescuento {
    @Override
    public double calcularTotal(List<Producto> productos) {
        double total = 0;
        int cantidad = productos.size();

        if (cantidad == 0) return 0;

        if (cantidad == 2) {
            Producto p1 = productos.get(0);
            Producto p2 = productos.get(1);

            if (p1.getNombre().equalsIgnoreCase(p2.getNombre())) {
                System.out.println("Aplicando: Descuento par de productos iguales (30 por ciento)");
                total = p1.getPrecio() + p2.getPrecio();
                return total * 0.70;
            }
        }

        if (cantidad >= 3) {
            double minPrecio = Double.MAX_VALUE;
            double sumaTotal = 0;

            for (Producto p : productos) {
                if (p.getPrecio() < minPrecio) {
                    minPrecio = p.getPrecio();
                }
                sumaTotal += p.getPrecio();
            }

            System.out.println("Aplicando: Descuento 50 por ciento al producto mas barato");
            return (sumaTotal - minPrecio) + (minPrecio * 0.50);
        }

        System.out.println("No cumple condiciones especiales, precio normal");
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }
}

class CalculadoraDePrecios {
    private EstrategiaDescuento estrategia;
    private List<Producto> carrito;

    public CalculadoraDePrecios() {
        this.carrito = new ArrayList<>();
        this.estrategia = new SinDescuento();
    }

    public void agregarProducto(String nombre, double precio) {
        carrito.add(new Producto(nombre, precio));
    }

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double procesarCompra() {
        return estrategia.calcularTotal(carrito);
    }
    
    public void limpiarCarrito() {
        carrito.clear();
    }
}

public class Strategy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraDePrecios calculadora = new CalculadoraDePrecios();
        boolean continuar = true;

        calculadora.agregarProducto("Teclado", 100);
        calculadora.agregarProducto("Mouse", 50);
        calculadora.agregarProducto("Monitor", 300);

        while (continuar) {
            System.out.println("\n--- SISTEMA DE CAJA TIENDA ---");
            System.out.println("1 Agregar producto manual");
            System.out.println("2 Elegir estrategia SIN DESCUENTO");
            System.out.println("3 Elegir estrategia DESCUENTO FIJO (10 por ciento)");
            System.out.println("4 Elegir estrategia DESCUENTO AVANZADO (Reglas)");
            System.out.println("5 Calcular Total y Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese nombre: ");
                        String nom = scanner.nextLine();
                        System.out.print("Ingrese precio: ");
                        try {

                            double pre = scanner.nextDouble();
                            calculadora.agregarProducto(nom, pre);
                            scanner.nextLine();
                            System.out.println("Producto agregado");
                        } catch (InputMismatchException e) {
                            System.out.println("Error: El precio debe ser un numero valido. Intente de nuevo.");
                            scanner.nextLine(); 
                        }
                        break;
                    case 2:
                        calculadora.setEstrategia(new SinDescuento());
                        System.out.println("Estrategia cambiada a Sin Descuento");
                        break;
                    case 3:
                        calculadora.setEstrategia(new DescuentoFijo());
                        System.out.println("Estrategia cambiada a Descuento Fijo");
                        break;
                    case 4:
                        calculadora.setEstrategia(new DescuentoAvanzado());
                        System.out.println("Estrategia cambiada a Descuento Avanzado");
                        break;
                    case 5:
                        double total = calculadora.procesarCompra();
                        System.out.println("El total a pagar es: " + total);
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (InputMismatchException e) {

                System.out.println("Error: Debe ingresar un numero entero.");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}