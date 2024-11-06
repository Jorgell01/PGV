package pgv.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) { // 1
            System.out.println("Servidor en espera de conexiones...");

            Socket socket = serverSocket.accept(); // 2
            System.out.println("Cliente conectado");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 3
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // 4

            String mensaje;
            while ((mensaje = in.readLine()) != null) { // 5
                System.out.println("Cliente: " + mensaje);
                out.println("Mensaje recibido: " + mensaje); // 6

                if (mensaje.equals("exit")) { // 9
                    break; // 10
                }
            }

            socket.close(); // 7
        } catch (Exception e) {
            e.printStackTrace(); // 8
        }
    }
}
