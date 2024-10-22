package pgv.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintCachedQueueSystem {
    public static void main(String[] args) {
        // Sistema con capacidad para ajustar el número de impresoras según la demanda.
        ExecutorService printers = Executors.newCachedThreadPool();

        // Empleados enviando documentos para imprimir
        printers.execute(() -> {
            System.out.println("Imprimiendo documento 1");
            try {
                Thread.sleep(2000); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 1 impreso");
        });

        printers.execute(() -> {
            System.out.println("Imprimiendo documento 2");
            try {
                Thread.sleep(1500); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 2 impreso");
        });

        // Añadiendo más documentos
        printers.execute(() -> {
            System.out.println("Imprimiendo documento 3");
            try {
                Thread.sleep(2500); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 3 impreso");
        });

        printers.execute(() -> {
            System.out.println("Imprimiendo documento 4");
            try {
                Thread.sleep(1000); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 4 impreso");
        });

        printers.shutdown();
    }
}
