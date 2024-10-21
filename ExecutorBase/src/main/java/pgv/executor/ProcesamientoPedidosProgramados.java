package pgv.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProcesamientoPedidosProgramados {

    public static void main(String[] args) {
        // Crear un ScheduledThreadPool con un tamaño fijo de 3 para simular los 3 empleados disponibles
        ScheduledExecutorService poolDeHilosProgramados = Executors.newScheduledThreadPool(3);

        // Programar 10 pedidos con un retraso incremental de 3 segundos entre cada uno
        for (int i = 1; i <= 10; i++) {
            Pedido pedido = new Pedido(i);
            // Programar la ejecución del pedido con un retraso de 3 segundos por cada número de pedido
            poolDeHilosProgramados.schedule(pedido, i * 3, TimeUnit.SECONDS);
        }

        // Cerrar el pool de hilos después de un tiempo razonable para permitir la ejecución de todos los pedidos
        poolDeHilosProgramados.shutdown();
    }
}

//class Pedido implements Runnable {
//    private final int numeroPedido;
//
//    public Pedido(int numeroPedido) {
//        this.numeroPedido = numeroPedido;
//    }
//
//    @Override
//    public void run() {
//        // Obtener el número del empleado a partir del nombre del hilo
//        String nombreHilo = Thread.currentThread().getName();
//        String numeroEmpleado = nombreHilo.substring(nombreHilo.lastIndexOf('-') + 1);
//
//        System.out.println("Empleado #" + numeroEmpleado + " está procesando el pedido #" + numeroPedido);
//        try {
//            // Simular el tiempo de procesamiento del pedido (2 segundos)
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            System.err.println("El procesamiento del pedido #" + numeroPedido + " fue interrumpido.");
//        }
//        System.out.println("Empleado #" + numeroEmpleado + " ha terminado de procesar el pedido #" + numeroPedido);
//    }
//}
