package pgv.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintQueueSystem {
    public static void main(String[] args) {
        ExecutorService printer = Executors.newSingleThreadExecutor();

        // Empleados enviando documentos para imprimir
        printer.execute(() -> {
            System.out.println("Imprimiendo documento 1");
            try {
                Thread.sleep(2000); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 1 impreso");
        });

        printer.execute(() -> {
            System.out.println("Imprimiendo documento 2");
            try {
                Thread.sleep(1500); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 2 impreso");
        });

        // Aunque los trabajos de impresión se envían casi al mismo tiempo,
        // la impresora procesa los trabajos uno por uno en orden.

        // No olvides cerrar el executor
        printer.shutdown();
    }
}
