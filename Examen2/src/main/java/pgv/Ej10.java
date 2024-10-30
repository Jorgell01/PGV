package pgv;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ej10 {
    public static void main(String [] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            int prioridad = (i % 2 == 0) ? 1 : 2;
            executor.submit(new Documento(prioridad, "Documento " + i));
        }

        executor.shutdown();
    }
}

class Documento10 implements Runnable {
    private int prioridad;
    private String nombre;

    public Documento10(int prioridad, String nombre) {
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Procesando " + nombre + " con prioridad " + prioridad + " en " + Thread.currentThread().getName());
    }
}