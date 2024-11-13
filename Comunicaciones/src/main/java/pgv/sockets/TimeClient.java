package pgv.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Dirección del servidor (local)
        int port = 1234; // Puerto en el que el servidor está escuchando
        int numberOfClients = 5; // Número de clientes a emular

        ExecutorService clientPool = Executors.newCachedThreadPool();

        for (int i = 0; i < numberOfClients; i++) {
            clientPool.execute(() -> {
                try (Socket socket = new Socket(serverAddress, port)) {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    // Enviar solicitud de tiempo
                    output.println("time");
                    String serverResponse = input.readLine();
                    System.out.println("Respuesta del servidor: " + serverResponse);

                    // Cerrar la conexión
                    output.println("exit");
                } catch (Exception e) {
                    System.out.println("Error en el cliente: " + e.getMessage());
                }
            });

            //Esperar 5 segundos antes de crear el siguiente cliente
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        clientPool.shutdown();
    }
}