package pgv;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ej12 implements Runnable{
    private String nombreTarea;

    public Ej12(String nombre) {
        this.nombreTarea = nombre;
    }

    @Override
    public void run() {
        System.out.println(nombreTarea + " está siendo ejecutada por " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(nombreTarea + " fue interrumpida");
        }
        System.out.println(nombreTarea + " ha terminado");
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            Runnable tarea = new Ej12("Tarea " + i);
            executor.execute(tarea);
        }

        executor.shutdown();
    }
}
