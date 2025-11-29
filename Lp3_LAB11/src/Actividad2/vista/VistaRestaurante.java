/*************************************************************************************
ARCHIVO : VistaRestaurante.java
FECHA   : 28/11/2025
*************************************************************************************/
package Actividad2.vista;

import Actividad2.controlador.ControladorRestaurante;
import Actividad2.modelo.Plato;
import Actividad2.modelo.ModeloRestaurante;
import java.util.Scanner;

public class VistaRestaurante {
    private ControladorRestaurante controlador;
    private ModeloRestaurante modelo;
    private Scanner scanner;

    public VistaRestaurante(ModeloRestaurante modelo) {
        this.modelo = modelo;
        this.scanner = new Scanner(System.in);
        PantallaMesero pantalla = new PantallaMesero("Salon Principal");
        this.modelo.agregarObservador(pantalla);
    }

    public void setControlador(ControladorRestaurante controlador) {
        this.controlador = controlador;
    }

    public void iniciar() {
        boolean salir = false;
        System.out.println("SISTEMA RESTAURANTE");
        
        while (!salir) {
            mostrarMenu();
            int opcion = leerEnteroConRango("Seleccione una opción: ", 1, 7);

            switch (opcion) {
                case 1:
                    ingresarPlato();
                    break;
                case 2:
                    if(verificarControlador()) controlador.enviarPedidoACocina();
                    break;
                case 3:
                    if(verificarControlador()) controlador.cancelarPedido();
                    break;
                case 4:
                    if(verificarControlador()) controlador.deshacerUltimoCambio();
                    break;
                case 5:
                    mostrarDetallePedido();
                    break;
                case 6:
                    if(verificarControlador()) procesarPago();
                    salir = true;
                    break;
                case 7:
                    System.out.println("Saliendo del sistema.");
                    salir = true;
                    break;
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENU DE COMANDAS ---");
        System.out.println("1. Agregar Plato");
        System.out.println("2. Enviar a Cocina (Command)");
        System.out.println("3. Cancelar Pedido (Command)");
        System.out.println("4. Deshacer cambio (Undo)");
        System.out.println("5. Ver Pedido Actual");
        System.out.println("6. Cerrar Cuenta (Strategy)");
        System.out.println("7. Salir");
    }

    private void ingresarPlato() {
        System.out.println("\n NUEVO PLATO ");
        String nombre = leerTextoObligatorio("Ingrese nombre del plato: ");
        double precio = leerDoublePositivo("Ingrese precio del plato: ");
        
        try {
            if(verificarControlador()) {
                controlador.agregarPlato(nombre, precio);
                System.out.println("ÉXITO: Plato '" + nombre + "' agregado.");
            }
        } catch (IllegalArgumentException e) {

            System.out.println(">> ERROR DEL SISTEMA: " + e.getMessage());
        }
    }

    private void mostrarDetallePedido() {
        System.out.println("\n--- DETALLE ACTUAL ---");
        System.out.println("Estado: " + modelo.getEstadoOrden());
        if (modelo.getPedidoActual().getPlatos().isEmpty()) {
            System.out.println("(Sin platos registrados)");
        } else {
            for(Plato p : modelo.getPedidoActual().getPlatos()) {
                System.out.println("- " + p);
            }
        }
    }

    private void procesarPago() {
        System.out.println("\n--- FACTURACIÓN ---");
        System.out.println("1. Cliente Normal");
        System.out.println("2. Hora Feliz / VIP (20% Desc)");

        int tipo = leerEnteroConRango("Seleccione tipo de cliente: ", 1, 2);

        double total = controlador.calcularCuenta(tipo);
        System.out.println("=================================");
        System.out.println("TOTAL A PAGAR: S/" + String.format("%.2f", total));
        System.out.println("=================================");
    }
    
    private boolean verificarControlador() {
        if (controlador == null) {
            System.out.println("ERROR CRÍTICO: Controlador no inicializado.");
            return false;
        }
        return true;
    }

    private String leerTextoObligatorio(String mensaje) {
        String input = "";
        while (true) {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: El texto no puede estar vacío.");
        }
    }

    private int leerEnteroConRango(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            
            try {
                int valor = Integer.parseInt(input); 
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("Error: Por favor ingrese un número entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: '" + input + "' no es una opción válida. Ingrese solo números.");
            }
        }
    }

    private double leerDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (!input.matches("^[0-9]+(\\.[0-9]+)?$")) {
                System.out.println("Error: Ingrese un formato de precio válido (ej: 10 o 10.50). No use letras.");
                continue; 
            }

            try {
                double valor = Double.parseDouble(input);
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("Error: El precio debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato numérico inválido.");
            }
        }
    }
}