package ejercicio3;

public class comandoapagar implements comando {

    private dispositivo disp;

    public comandoapagar(dispositivo disp) {
        this.disp = disp;
    }

    public void ejecutar() {
        disp.apagar();
    }

    public void deshacer() {
        disp.encender();
    }

}
