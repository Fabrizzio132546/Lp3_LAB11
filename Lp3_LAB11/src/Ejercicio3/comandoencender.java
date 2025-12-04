package ejercicio3;

public class comandoencender implements comando {

    private dispositivo disp;

    public comandoencender(dispositivo disp) {
        this.disp = disp;
    }

    public void ejecutar() {
        disp.encender();
    }

    public void deshacer() {
        disp.apagar();
    }

}
