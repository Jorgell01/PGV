package com.mycompany.herrero_santana_jorge_alberto_actpgv02a;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Jorge Alberto Herrero Santana
 */
public class Herrero_Santana_Jorge_Alberto_ActPGV02A {

    public static void main(String[] args) {
        try {
            // Detectar correctamente el sistema operativo
            String osName = System.getProperty("os.name").toLowerCase();
            String command = "";
            
            if (osName.contains("win")) {
                command = "systeminfo"; // Comando para Windows
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
                command = "uname -a"; // Comando para Linux/macOS
            } else {
                System.out.println("Sistema operativo fuera de los manejados");
                return;
            }
            
            // Ejecutar el comando
            Process process = Runtime.getRuntime().exec(command);
            
            // Leer la salida que devuelve el comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder(); // Crear un StringBuilder para almacenar la salida
            
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n"); // Añadir cada línea leída al StringBuilder
                System.out.println(line); // Imprimir cada línea en la consola
            }
            
            // Mostrar la salida por consola
            System.out.println("Información del sistema capturada.");
            
            // Guardar la salida del comando en un archivo
            try (FileWriter fileWriter = new FileWriter("Sysinfo.txt");
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.write(output.toString()); // Escribir el contenido de output en el archivo
                System.out.println("La información del sistema se ha guardado en Sysinfo.txt");
            }
            
            // Esperar a que el proceso finalice
            process.waitFor();
            
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
