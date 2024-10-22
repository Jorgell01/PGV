import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SimpleExecutor {
    public static void main(String[] args) {
        // Utilizando Executors.newSingleThreadExecutor() para crear un ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Aquí puedes enviar tareas al executor
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("La tarea se está ejecutando por el hilo: " + Thread.currentThread().getName());
            }
        });

        // No olvides cerrar el executor
        executor.shutdown();
    }
}
