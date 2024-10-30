package pgv;

public class Ej8 implements Runnable{
    private static int contador = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            incrementarContador();
        }
    }

    private synchronized void incrementarContador() {
        contador++;
        System.out.println(Thread.currentThread().getName() + ": " + contador);
    }

    public static void main(String[] args) {
        Ej8 tarea = new Ej8();
        Thread hilo1 = new Thread(new Ej8());
        Thread hilo2 = new Thread(new Ej8());
        hilo1.start();
        hilo2.start();
    }
}
