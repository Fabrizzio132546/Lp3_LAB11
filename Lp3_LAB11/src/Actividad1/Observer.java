/*************************************************************************************
ARCHIVO : Observer.java
FECHA   : 25/11/2025
*************************************************************************************/
package Actividad1;

import java.util.ArrayList;
import java.util.List;

interface Observador {
    void actualizar(String mensaje);
}

interface Sujeto {
    void suscribir(Observador observador);
    void desuscribir(Observador observador);
    void notificarObservadores(String mensaje);
}

class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {

        System.out.println("Usuario " + nombre + " recibio notificacion: " + mensaje);
    }

    public String getNombre() {
        return nombre;
    }
}

class TiendaTecnologia implements Sujeto {

    private List<Observador> usuarios;
    private String nombreTienda;

    public TiendaTecnologia(String nombreTienda) {
        this.nombreTienda = nombreTienda;
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void suscribir(Observador observador) {
        usuarios.add(observador);
        if (observador instanceof Usuario) {
            System.out.println("Se ha suscrito al sistema: " + ((Usuario) observador).getNombre());
        }
    }

    @Override
    public void desuscribir(Observador observador) {
        if (usuarios.contains(observador)) {
            usuarios.remove(observador);
            if (observador instanceof Usuario) {
                System.out.println("Se ha desuscripto del sistema: " + ((Usuario) observador).getNombre());
            }
        }
    }

    @Override
    public void notificarObservadores(String mensaje) {
        System.out.println("\n--- Enviando notificacion masiva desde " + nombreTienda + " ---");
        for (Observador usuario : usuarios) {
            usuario.actualizar(mensaje);
        }
        System.out.println("--- Fin del envio ---\n");
    }

    public void lanzarNuevaPromocion(String producto, String descuento) {
        String mensaje = "Aprovecha el " + descuento + " en " + producto;
        notificarObservadores(mensaje);
    }
}

public class Observer {
    public static void main(String[] args) {

        TiendaTecnologia tienda = new TiendaTecnologia("Smart Store Arequipa");

        Usuario usuario1 = new Usuario("Lizardo Castillo");
        Usuario usuario2 = new Usuario("Fabrizzio Ochoa");

        tienda.suscribir(usuario1);
        tienda.suscribir(usuario2);

        tienda.lanzarNuevaPromocion("Laptop Asus tuf gaming", "20 por ciento de descuento");

        tienda.suscribir(usuario1);

        tienda.desuscribir(usuario1);

        tienda.lanzarNuevaPromocion("Teclado Mecanico", "oferta 2x1");
    }
}



