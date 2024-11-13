package pgv.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeServer {
    public static void main(String[] args) {
        int port = 1234; // Puerto donde el servidor escuchará
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor de hora iniciado. Esperando conexiones...");

            while (true) {
                // Espera una conexión de un cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                // Enviar la conexión del cliente al pool de hilos
                threadPool.execute(() -> {
                    try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                         PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                        String request;
                        while ((request = input.readLine()) != null) {
                            if ("time".equalsIgnoreCase(request)) {
                                // Obtener la fecha y hora actual
                                LocalDateTime now = LocalDateTime.now();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                String currentTime = now.format(formatter);

                                // Enviar la fecha y hora al cliente
                                output.println("Hora actual: " + currentTime);
                            } else if ("exit".equalsIgnoreCase(request)) {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error en el manejador de cliente: " + e.getMessage());
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (Exception e) {
                            System.out.println("Error al cerrar el socket del cliente: " + e.getMessage());
                        }
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}