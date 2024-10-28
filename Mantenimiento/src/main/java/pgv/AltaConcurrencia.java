package pgv;

import java.util.concurrent.*;

public class AltaConcurrencia {

    private final ThreadPoolExecutor executor;

    public AltaConcurrencia() {
        // Configurar el ThreadPoolExecutor con un número mínimo y máximo de hilos
        int corePoolSize = 10; // Número mínimo de hilos
        int maximumPoolSize = 100; // Número máximo de hilos
        long keepAliveTime = 60L; // Tiempo que los hilos inactivos esperarán antes de ser terminados
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
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
        for (int i = 0; i < 50; i++) {
            altaConcurrencia.enviarTarea(() -> {
                System.out.println("Ejecutando tarea: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulación de trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        altaConcurrencia.apagar();
    }
}
