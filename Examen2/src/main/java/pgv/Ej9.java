package pgv;

public class Ej9 implements Runnable{
    private String nombreHilo;

    public Ej9(String nombre) {
        this.nombreHilo = nombre;
    }

    @Override
    public void run() {
        synchronized (Ej9.class) {
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
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Ej9("Hilo 1"));
        Thread hilo2 = new Thread(new Ej9("Hilo 2"));
        hilo1.start();
        hilo2.start();
    }
}
