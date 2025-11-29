/*************************************************************************************
ARCHIVO : SistemaModelo.java
FECHA   : 28/11/2025
*************************************************************************************/
package Ejercicio1.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SistemaModelo {

    private Map<Categoria, List<IObservador>> suscriptores;

    private Map<String, Usuario> usuariosRegistrados;

    private Map<Categoria, Integer> contadorNotificaciones;
    private int totalNotificacionesEnviadas;

    public SistemaModelo() {
        suscriptores = new HashMap<>();
        usuariosRegistrados = new HashMap<>();
        contadorNotificaciones = new HashMap<>();
        totalNotificacionesEnviadas = 0;

        for (Categoria cat : Categoria.values()) {
            suscriptores.put(cat, new ArrayList<>());
            contadorNotificaciones.put(cat, 0);
        }
    }

    public Usuario registrarUsuario(String nombre) {
        if (!usuariosRegistrados.containsKey(nombre)) {
            Usuario nuevo = new Usuario(nombre);
            usuariosRegistrados.put(nombre, nuevo);
            return nuevo;
        }
        return usuariosRegistrados.get(nombre);
    }

    public Usuario getUsuario(String nombre) {
        return usuariosRegistrados.get(nombre);
    }

    public void suscribir(Usuario usuario, Categoria categoria) {
        List<IObservador> lista = suscriptores.get(categoria);
        if (!lista.contains(usuario)) {
            lista.add(usuario);
        }
    }

    public void desuscribir(Usuario usuario, Categoria categoria) {
        List<IObservador> lista = suscriptores.get(categoria);
        lista.remove(usuario);
    }

    public boolean estaSuscrito(Usuario usuario, Categoria categoria) {
        return suscriptores.get(categoria).contains(usuario);
    }

    public void generarNotificacion(String mensaje, Categoria categoria, Prioridad prioridad) {
        Notificacion nuevaNotificacion = new Notificacion(mensaje, categoria, prioridad);

        List<IObservador> listaInteresados = suscriptores.get(categoria);
        for (IObservador obs : listaInteresados) {
            obs.actualizar(nuevaNotificacion);
        }

        totalNotificacionesEnviadas++;
        int conteoActual = contadorNotificaciones.get(categoria);
        contadorNotificaciones.put(categoria, conteoActual + 1);
    }

    public Map<Categoria, Integer> getEstadisticasCategoria() {
        return contadorNotificaciones;
    }

    public int getTotalNotificacionesEnviadas() {
        return totalNotificacionesEnviadas;
    }
    
    public int getCantidadSuscriptores(Categoria c) {
        return suscriptores.get(c).size();
    }
}