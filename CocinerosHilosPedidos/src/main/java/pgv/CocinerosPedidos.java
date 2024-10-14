package pgv;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CocinerosPedidos {
    public static void main (String[] args) {
        //Cantidad cocineros
        int numCocineros = 4;

        //Cantidad de pedidos
        int numPedidos = 20;
        List<Integer> pedidos = new ArrayList<>();
        for (int i = 1; i <= numPedidos; i++) {
            pedidos.add(i);
        }

        //Distribuir los pedidos equitativamente
        List<List<Integer>> pedidosPorCocinero = distribuirPedidos(pedidos, numCocineros);

        //Crear los hilos para los cocineros
        List<Thread> cocineros = new ArrayList<>();
        for (int i = 0; i < numCocineros; i++) {
            List<Integer> pedidosCocinero = pedidosPorCocinero.get(i);
            //Crear un nuevo hilo
            Thread cocinero = new Thread(new Cocinero(i + 1, pedidosCocinero));
            //Agregar el hilo a la lista de cocineros e iniciarlo
            cocineros.add(cocinero);
            cocinero.start(); //Aquí se pone a currar
        }

        //Eseperar a que acaben todos los hilos
        for (Thread cocinero : cocineros) {
            try {
                cocinero.join(); //Aqui se espera a que acabe el pedido
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los pedidos han sido procesados");

    }

    //Metodo para distribuitr pedidos equitativamente
    private static List<List<Integer>> distribuirPedidos(List<Integer> pedidos, int numCocineros) {
        List<List<Integer>> pedidosPorCocinero = new ArrayList<>();
        for (int i = 0; i < numCocineros; i++) {
            pedidosPorCocinero.add(new ArrayList<>());
        }

        for (int i = 0; i < pedidos.size(); i++) {
            pedidosPorCocinero.get(i % numCocineros).add(pedidos.get(i));
        }

        return pedidosPorCocinero;

    }

}

class Cocinero implements Runnable {
    private int id;

    private List<Integer> pedidos; //Lista de los pedidos que le tocan procesar

    private Random random = new Random();

    public Cocinero(int id, List<Integer> pedidos) {
        this.id = id;
        this.pedidos = pedidos;
    }

    @Override
    public void run() {
        for (Integer pedido : pedidos) {
            System.out.println("Cocinero " + id + " está preparando el pedido #" + pedido);

            try {
                int tiempoPreparacion = 1000 + random.nextInt(1000);
                Thread.sleep(tiempoPreparacion);
                System.out.println("Cocinero " + id + " ha terminado el pedido #" + pedido + " en " + tiempoPreparacion + " ms ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
