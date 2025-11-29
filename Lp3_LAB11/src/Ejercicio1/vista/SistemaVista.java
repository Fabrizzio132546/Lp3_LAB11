/*************************************************************************************
ARCHIVO : SistemaVista.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.vista;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Ejercicio1.modelo.Categoria;
import Ejercicio1.modelo.MensajeBuzon;
import Ejercicio1.modelo.Notificacion;
import Ejercicio1.modelo.Prioridad;
import Ejercicio1.modelo.Usuario;

public class SistemaVista {
    private Scanner scanner;

    public SistemaVista() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("\n=== SISTEMA DE NOTIFICACIONES ===");
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Ingresar como USUARIO");
        System.out.println("2. Ingresar como ADMINISTRADOR (Enviar alertas)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        return leerEntero();
    }

    public int mostrarMenuUsuario(Usuario u) {
        System.out.println("\n--- PERFIL DE: " + u.getNombre().toUpperCase() + " ---");
        System.out.println("Bandeja: " + u.getCantidadSinLeer() + " mensajes sin leer.");
        System.out.println("-------------------------");
        System.out.println("1. Ver Bandeja de Entrada (Leer Notificaciones)");
        System.out.println("2. Gestionar Suscripciones");
        System.out.println("0. Volver atrás");
        System.out.print("Opción: ");
        return leerEntero();
    }

    public int mostrarMenuAdmin() {
        System.out.println("\n--- PANEL DE ADMINISTRADOR ---");
        System.out.println("1. Enviar nueva Notificación");
        System.out.println("2. Ver Dashboard de Métricas");
        System.out.println("0. Volver atrás");
        System.out.print("Opción: ");
        return leerEntero();
    }


    public void mostrarBuzon(List<MensajeBuzon> buzon) {
        if (buzon.isEmpty()) {
            System.out.println("\n[!] La bandeja de entrada está vacía.");
            return;
        }

        System.out.println("\n=== BANDEJA DE ENTRADA ===");
        for (int i = 0; i < buzon.size(); i++) {
            MensajeBuzon mb = buzon.get(i);
            Notificacion n = mb.getNotificacion();

            String estado = mb.isLeido() ? "[LEÍDO]" : "[NUEVO]";
            String color = mb.isLeido() ? "" : "* "; 

            System.out.printf("%d. %s %s | %s | %s: %s\n", 
                (i + 1), color, estado, n.getFechaHora().toLocalTime(), n.getCategoria(), n.getMensaje());
        }
    }

    public void mostrarDashboard(int total, Map<Categoria, Integer> stats, Map<Categoria, Integer> subsCount) {
        System.out.println("\n=== DASHBOARD DE MÉTRICAS ===");
        System.out.println("Total Notificaciones Enviadas: " + total);
        System.out.println("\nDesglose por Categoría:");
        System.out.printf("%-15s | %-10s | %-15s\n", "CATEGORÍA", "ENVIADAS", "SUSCRIPTORES");
        System.out.println("----------------------------------------------");
        
        for (Categoria c : Categoria.values()) {
            System.out.printf("%-15s | %-10d | %-15d\n", 
                c, stats.get(c), subsCount.get(c));
        }
    }

    public void mostrarCategoriasConEstado(Usuario u, Map<Categoria, Boolean> estados) {
        System.out.println("\n--- GESTIÓN DE SUSCRIPCIONES ---");
        int index = 1;
        for (Categoria c : Categoria.values()) {
            String estado = estados.get(c) ? "[SUSCRITO]" : "[NO SUSCRITO]";
            System.out.println(index + ". " + c + " " + estado);
            index++;
        }
        System.out.println("Seleccione el número para cambiar estado (0 para salir):");
    }

    public String solicitarTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine();
    }

    public Categoria solicitarCategoria() {
        System.out.println("Seleccione Categoría:");
        Categoria[] cats = Categoria.values();
        for (int i = 0; i < cats.length; i++) {
            System.out.println((i + 1) + ". " + cats[i]);
        }
        int op = leerEntero();
        if (op > 0 && op <= cats.length) return cats[op - 1];
        return Categoria.NOTICIAS; 
    }

    public Prioridad solicitarPrioridad() {
        System.out.println("Seleccione Prioridad (1.Baja, 2.Media, 3.Alta, 4.Critica):");
        int op = leerEntero();
        switch(op) {
            case 1: return Prioridad.BAJA;
            case 3: return Prioridad.ALTA;
            case 4: return Prioridad.CRITICA;
            default: return Prioridad.MEDIA;
        }
    }

    public void mostrarMensaje(String msg) {
        System.out.println("Error: " + msg);
    }

    private int leerEntero() {
        try {
            int i = Integer.parseInt(scanner.nextLine());
            return i;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}