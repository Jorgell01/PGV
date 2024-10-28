package pgv;

import java.util.concurrent.*;

public class AltaConcurrencia {

    private final ExecutorService executor;

    public AltaConcurrencia() {

        executor = Executors.newCachedThreadPool();

    }

    public void enviarTarea(Runnable task) {
        executor.submit(task);
    }

    public void apagar() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public static void main(String[] args) {
        AltaConcurrencia altaConcurrencia = new AltaConcurrencia();

        // Simulación de envío de tareas
        for (int i = 0; i < 100; i++) {
            altaConcurrencia.enviarTarea(() -> {
                System.out.println("Ejecutando tarea: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // Simulación de trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        altaConcurrencia.apagar();
    }
}
