package ejercicio3;

import java.util.Scanner;

public class maincommand {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        luz luz1 = new luz();
        ventilador vent = new ventilador();
        aireacondicionado aire = new aireacondicionado();

        controlremoto cr = new controlremoto();

        while (true) {

            System.out.println("**********");
            System.out.println("control remoto");
            System.out.println("1. encender luz");
            System.out.println("2. apagar luz");
            System.out.println("3. encender ventilador");
            System.out.println("4. apagar ventilador");
            System.out.println("5. encender aire acondicionado");
            System.out.println("6. apagar aire acondicionado");
            System.out.println("7. deshacer");
            System.out.println("8. salir");
            System.out.println("**********");
            System.out.print("opcion: ");

            int op = sc.nextInt();

            if (op == 8) return;

            switch (op) {
                case 1: cr.ejecutar(new comandoencender(luz1)); break;
                case 2: cr.ejecutar(new comandoapagar(luz1)); break;
                case 3: cr.ejecutar(new comandoencender(vent)); break;
                case 4: cr.ejecutar(new comandoapagar(vent)); break;
                case 5: cr.ejecutar(new comandoencender(aire)); break;
                case 6: cr.ejecutar(new comandoapagar(aire)); break;
                case 7: cr.deshacer(); break;
                default: System.out.println("opcion no valida"); break;
            }

        }

    }

}
