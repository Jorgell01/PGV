package pgv;

public class Ej3 implements Runnable {
    private String nombreHilo;

    public Ej3(String nombre) {
        this.nombreHilo = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo " + nombreHilo + " - Contador: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(nombreHilo + " fue interrumpido");
            }
        }
        System.out.println(nombreHilo + " ha terminado");
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Ej3("Hilo 1"));
        Thread hilo2 = new Thread(new Ej3("Hilo 2"));
        hilo2.start();
        hilo1.start();
    }
}
