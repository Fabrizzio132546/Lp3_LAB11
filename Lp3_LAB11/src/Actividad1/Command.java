    
/*************************************************************************************
ARCHIVO : Commmand.java
FECHA   : 25/11/2025
*************************************************************************************/

package Actividad1;

import java.util.Scanner;
import java.util.Stack;
import java.util.InputMismatchException;

interface Comando {
    void ejecutar();
    void deshacer();
}

class Televisor {
    private boolean encendido;
    private int volumen;
    private int canal;

    public Televisor() {
        this.encendido = false; 
        this.volumen = 10;   
        this.canal = 1;       
    }

    public boolean estaEncendido() {
        return encendido;
    }

    public void encender() {
        encendido = true;
        System.out.println("TV ENCENDIDO - Bienvenido");
    }

    public void apagar() {
        encendido = false;
        System.out.println("TV APAGADO - Hasta luego");
    }

    public void subirVolumen() {
        if (encendido) {
            volumen++;
            System.out.println("Volumen subido a: " + volumen);
        } else {
            System.out.println("ERROR: El TV esta apagado, no se puede subir volumen");
        }
    }

    public void bajarVolumen() {
        if (encendido) {
            if (volumen > 0) {
                volumen--;
                System.out.println("Volumen bajado a: " + volumen);
            } else {
                System.out.println("El volumen ya esta en 0 (Mute)");
            }
        } else {
            System.out.println("ERROR: El TV esta apagado, no se puede bajar volumen");
        }
    }

    public void siguienteCanal() {
        if (encendido) {
            canal++;
            System.out.println("Cambiando al canal: " + canal);
        } else {
            System.out.println("ERROR: El TV esta apagado, no se puede cambiar canal");
        }
    }

    public void canalAnterior() {
        if (encendido) {
            if (canal > 1) {
                canal--;
                System.out.println("Regresando al canal: " + canal);
            } else {
                System.out.println("Estas en el canal 1, no se puede bajar mas");
            }
        } else {
            System.out.println("ERROR: El TV esta apagado");
        }
    }
}


class CmdEncender implements Comando {
    private Televisor tv;
    public CmdEncender(Televisor tv) { this.tv = tv; }
    
    @Override
    public void ejecutar() { tv.encender(); }
    
    @Override
    public void deshacer() { tv.apagar(); }
}

class CmdApagar implements Comando {
    private Televisor tv;
    public CmdApagar(Televisor tv) { this.tv = tv; }
    
    @Override
    public void ejecutar() { tv.apagar(); }
    
    @Override
    public void deshacer() { tv.encender(); }
}

class CmdSubirVolumen implements Comando {
    private Televisor tv;
    private boolean fueEjecutado; 

    public CmdSubirVolumen(Televisor tv) { this.tv = tv; }
    
    @Override
    public void ejecutar() {
        if (tv.estaEncendido()) {
            tv.subirVolumen();
            fueEjecutado = true;
        } else {
            System.out.println("Comando ignorado: TV apagado");
            fueEjecutado = false;
        }
    }
    
    @Override
    public void deshacer() {
        if (fueEjecutado) {
            System.out.print("Deshaciendo subir volumen... ");
            tv.bajarVolumen();
        }
    }
}

class CmdBajarVolumen implements Comando {
    private Televisor tv;
    private boolean fueEjecutado;

    public CmdBajarVolumen(Televisor tv) { this.tv = tv; }
    
    @Override
    public void ejecutar() {
        if (tv.estaEncendido()) {
            tv.bajarVolumen();
            fueEjecutado = true;
        } else {
            System.out.println("Comando ignorado: TV apagado");
            fueEjecutado = false;
        }
    }
    
    @Override
    public void deshacer() {
        if (fueEjecutado) {
            System.out.print("Deshaciendo bajar volumen... ");
            tv.subirVolumen();
        }
    }
}

class CmdSiguienteCanal implements Comando {
    private Televisor tv;
    private boolean fueEjecutado;

    public CmdSiguienteCanal(Televisor tv) { this.tv = tv; }
    
    @Override
    public void ejecutar() {
        if (tv.estaEncendido()) {
            tv.siguienteCanal();
            fueEjecutado = true;
        } else {
            System.out.println("Comando ignorado: TV apagado");
            fueEjecutado = false;
        }
    }
    
    @Override
    public void deshacer() {
        if (fueEjecutado) {
            System.out.print("Deshaciendo cambio de canal... ");
            tv.canalAnterior();
        }
    }
}

class ControlRemotoTV {

    private Stack<Comando> historial;

    public ControlRemotoTV() {
        this.historial = new Stack<>();
    }

    public void presionarBoton(Comando cmd) {
        cmd.ejecutar();

        historial.push(cmd);
    }

    public void presionarBotonDeshacer() {
        if (!historial.isEmpty()) {
            Comando ultimo = historial.pop();
            ultimo.deshacer();
        } else {
            System.out.println("No hay acciones para deshacer en el historial");
        }
    }
}

public class Command {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Televisor miTV = new Televisor();
        ControlRemotoTV control = new ControlRemotoTV();

        Comando btnEncender = new CmdEncender(miTV);
        Comando btnApagar = new CmdApagar(miTV);
        Comando btnVolMas = new CmdSubirVolumen(miTV);
        Comando btnVolMenos = new CmdBajarVolumen(miTV);
        Comando btnCanalMas = new CmdSiguienteCanal(miTV);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- CONTROL REMOTO UNIVERSAL (5 Funciones) ---");
            System.out.println("Estado TV: " + (miTV.estaEncendido() ? "ENCENDIDO" : "APAGADO"));
            System.out.println("1 Boton ENCENDER");
            System.out.println("2 Boton APAGAR");
            System.out.println("3 Boton SUBIR VOLUMEN (+)");
            System.out.println("4 Boton BAJAR VOLUMEN (-)");
            System.out.println("5 Boton SIGUIENTE CANAL (Ch+)");
            System.out.println("6 Boton DESHACER (Undo)");
            System.out.println("7 Guardar control y Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        control.presionarBoton(btnEncender);
                        break;
                    case 2:
                        control.presionarBoton(btnApagar);
                        break;
                    case 3:
                        control.presionarBoton(btnVolMas);
                        break;
                    case 4:
                        control.presionarBoton(btnVolMenos);
                        break;
                    case 5:
                        control.presionarBoton(btnCanalMas);
                        break;
                    case 6:
                        control.presionarBotonDeshacer();
                        break;
                    case 7:
                        continuar = false;
                        System.out.println("Guardando control");
                        break;
                    default:
                        System.out.println("Opcion no valida en el control");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes presionar un numero valido");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}