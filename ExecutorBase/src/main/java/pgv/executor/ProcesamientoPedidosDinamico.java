package pgv.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcesamientoPedidosDinamico {

    public static void main(String[] args) {
        // Crear un pool de hilos dinámico utilizando newCachedThreadPool()
        ExecutorService poolDeHilos = Executors.newCachedThreadPool();

        // Enviar 10 pedidos al pool de hilos para ser procesados
        for (int i = 1; i <= 10; i++) {
            Pedido pedido = new Pedido(i);
            poolDeHilos.execute(pedido);
        }

        // Cerrar el pool de hilos después de enviar todas las tareas
        poolDeHilos.shutdown();
    }
}

class Pedido implements Runnable {
    private final int numeroPedido;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    @Override
    public void run() {
        // Obtener el número del empleado a partir del nombre del hilo
        String nombreHilo = Thread.currentThread().getName();
        String numeroEmpleado = nombreHilo.substring(nombreHilo.lastIndexOf('-') + 1);

        System.out.println("Empleado #" + numeroEmpleado + " está procesando el pedido #" + numeroPedido);
        try {
            // Simular el tiempo de procesamiento del pedido (2 segundos)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("El procesamiento del pedido #" + numeroPedido + " fue interrumpido.");
        }
        System.out.println("Empleado #" + numeroEmpleado + " ha terminado de procesar el pedido #" + numeroPedido);
    }
}