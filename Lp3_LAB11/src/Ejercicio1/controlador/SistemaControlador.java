/*************************************************************************************
ARCHIVO : SistemaControlador.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.controlador;

import java.util.HashMap;
import java.util.Map;


import Ejercicio1.vista.SistemaVista;
import Ejercicio1.modelo.*;

public class SistemaControlador {
    private SistemaModelo modelo;
    private SistemaVista vista;

    public SistemaControlador(SistemaModelo modelo, SistemaVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarBienvenida();
        boolean ejecutando = true;

        while (ejecutando) {
            int opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    manejarSesionUsuario();
                    break;
                case 2:
                    manejarSesionAdmin();
                    break;
                case 0:
                    vista.mostrarMensaje("Cerrando sistema...");
                    ejecutando = false;
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void manejarSesionUsuario() {
        String nombre = vista.solicitarTexto("Ingrese su nombre de usuario");
        Usuario usuario = modelo.registrarUsuario(nombre);

        boolean enSesion = true;
        while (enSesion) {
            int opcion = vista.mostrarMenuUsuario(usuario);
            switch (opcion) {
                case 1:
                    vista.mostrarBuzon(usuario.getBuzon());
                    for(MensajeBuzon m : usuario.getBuzon()) m.marcarComoLeido();
                    vista.solicitarTexto("\nPresione Enter para continuar");
                    break;
                case 2:
                    manejarSuscripciones(usuario);
                    break;
                case 0:
                    enSesion = false;
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void manejarSuscripciones(Usuario usuario) {
        boolean gestionando = true;
        while(gestionando) {
            Map<Categoria, Boolean> estados = new HashMap<>();
            for(Categoria c : Categoria.values()) {
                estados.put(c, modelo.estaSuscrito(usuario, c));
            }

            vista.mostrarCategoriasConEstado(usuario, estados);

            try {
                String input = vista.solicitarTexto("Opción");
                int seleccion = Integer.parseInt(input);
                
                if (seleccion == 0) {
                    gestionando = false;
                } else if (seleccion > 0 && seleccion <= Categoria.values().length) {
                    Categoria catSeleccionada = Categoria.values()[seleccion - 1];
                    if (modelo.estaSuscrito(usuario, catSeleccionada)) {
                        modelo.desuscribir(usuario, catSeleccionada);
                        vista.mostrarMensaje("Te has desuscrito de " + catSeleccionada);
                    } else {
                        modelo.suscribir(usuario, catSeleccionada);
                        vista.mostrarMensaje("Te has suscrito a " + catSeleccionada);
                    }
                }
            } catch (NumberFormatException e) {
                vista.mostrarMensaje("Entrada no válida.");
            }
        }
    }

    private void manejarSesionAdmin() {
        boolean enAdmin = true;
        while(enAdmin) {
            int opcion = vista.mostrarMenuAdmin();
            switch (opcion) {
                case 1:
                    String mensaje = vista.solicitarTexto("Escriba el mensaje de la notificación");
                    Categoria cat = vista.solicitarCategoria();
                    Prioridad prio = vista.solicitarPrioridad();
                    
                    modelo.generarNotificacion(mensaje, cat, prio);
                    vista.mostrarMensaje("Notificación enviada exitosamente.");
                    break;
                case 2:
                    Map<Categoria, Integer> suscriptoresCount = new HashMap<>();
                    for(Categoria c : Categoria.values()) {
                        suscriptoresCount.put(c, modelo.getCantidadSuscriptores(c));
                    }
                    
                    vista.mostrarDashboard(
                        modelo.getTotalNotificacionesEnviadas(),
                        modelo.getEstadisticasCategoria(),
                        suscriptoresCount
                    );
                    vista.solicitarTexto("\nPresione Enter para continuar");
                    break;
                case 0:
                    enAdmin = false;
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida.");
            }
        }
    }
}