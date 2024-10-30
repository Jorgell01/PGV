package pgv;

import java.util.concurrent.ExecutorService;

public class Ej1 {
    public static void main(String[] args) {
        ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int prioridad = (i % 2 == 0) ? 1 : 2;
            executor.submit(new Documento(prioridad, "Documento " + i));
        }

        executor.shutdown();

    }
}

class Documento implements Runnable {
    private int prioridad;
    private String nombre;

    public Documento(int prioridad, String nombre) {
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Procesando " + nombre + " con prioridad " + prioridad + " en " + Thread.currentThread().getName());
    }
}
