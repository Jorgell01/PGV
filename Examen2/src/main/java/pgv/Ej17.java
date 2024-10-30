package pgv;

public class Ej17 implements Runnable {
    private String nombreHilo;

    public Ej17(String nombre) {
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
        System.out.print(nombreHilo + " ha terminado");
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Ej17("Hilo 1"));
        Thread hilo2 = new Thread(new Ej17("Hilo 2"));
        hilo1.start();
        hilo1.interrupt();
        hilo2.start();
    }
}
