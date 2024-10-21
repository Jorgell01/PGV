package pgv.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintMultipleQueueSystem {
    public static void main(String[] args) {
        // Supongamos que hay 2 impresoras en la oficina.
        ExecutorService printers = Executors.newFixedThreadPool(2);

        // Empleados enviando documentos para imprimir
        printers.execute(() -> {
            System.out.println("Imprimiendo documento 1 en impresora A");
            try {
                Thread.sleep(2000); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 1 impreso en impresora A");
        });

        printers.execute(() -> {
            System.out.println("Imprimiendo documento 2 en impresora B");
            try {
                Thread.sleep(1500); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 2 impreso en impresora B");
        });

        // Añadiendo un tercer documento
        printers.execute(() -> {
            System.out.println("Imprimiendo documento 3");
            try {
                Thread.sleep(2500); // Simula el tiempo de impresión
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 3 impreso");
        });
    }
}
