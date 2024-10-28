package pgv;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TareaMantenimiento {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startMantenimiento() {
        // Tarea para eliminar registros viejos
        Runnable deleteOldRecords = () -> {
            System.out.println("Eliminando registros viejos...");
            // Lógica para eliminar registros viejos
        };

        // Tarea para realizar copias de seguridad de archivos
        Runnable backupFiles = () -> {
            System.out.println("Realizando copias de seguridad de archivos...");
            // Lógica para realizar copias de seguridad de archivos
        };

        // Tarea para enviar informes de actividad
        Runnable sendActivityReports = () -> {
            System.out.println("Enviando informes de actividad...");
            // Lógica para enviar informes de actividad
        };

        // Programar tareas para que se ejecuten cada cierto tiempo
        scheduler.scheduleAtFixedRate(deleteOldRecords, 0, 20, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(sendActivityReports, 5, 30, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(backupFiles, 10, 60, TimeUnit.SECONDS);
    }

    public void stopMantenimiento() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    public static void main(String[] args) {
        TareaMantenimiento tarea = new TareaMantenimiento();
        tarea.startMantenimiento();
    }
}