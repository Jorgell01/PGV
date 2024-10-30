package pgv;

import java.util.Comparator;
import java.util.concurrent.*;

public class Ej16 {
    public static void main(String[] args) {
        PriorityBlockingQueue<Runnable> priorityQueue = new PriorityBlockingQueue<>(10, new Comparator<Runnable>() {
            @Override
            public int compare(Runnable r1, Runnable r2) {
                if (r1 instanceof Documento16 && r2 instanceof Documento16) {
                    return Integer.compare(((Documento16) r2).getPrioridad(), ((Documento16) r1).getPrioridad());
                }
                return 0;
            }
        });

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, priorityQueue);

        for (int i = 0; i < 10; i++) {
            int prioridad = (i % 2 == 0) ? 1 : 2;
            executor.submit(new Documento16(prioridad, "Documento " + i));
        }

        executor.shutdown();
    }
}

class Documento16 implements Runnable, Comparable<Documento16> {
    private int prioridad;
    private String nombre;

    public Documento16(int prioridad, String nombre) {
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public void run() {
        System.out.println("Procesando " + nombre + " con prioridad " + prioridad + " en " + Thread.currentThread().getName());
    }

    @Override
    public int compareTo(Documento16 o) {
        return Integer.compare(o.getPrioridad(), this.prioridad);
    }
}