package pgv.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

    public static void main(String[] args) {
        System.out.println("Escribe una ip: "); // 0
        String ip = new Scanner(System.in).nextLine();

        try(Socket socket = new Socket(ip,12345)) { // 1
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 2
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // 3
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); // 4

            String userInput;
            System.out.println("Escribe un mensaje: ");
            while ((userInput = stdIn.readLine()) != null) { // 5
                out.println(userInput); // 6
                System.out.println("Servidor: " + stdIn.readLine()); // 7

                if (userInput.equals("exit")) { // 8
                    break; // 9
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 8
        }
    }
}
