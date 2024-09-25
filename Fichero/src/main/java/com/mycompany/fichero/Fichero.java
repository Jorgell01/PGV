package com.mycompany.fichero;

import java.io.*;

/**
 *
 * @author Mondongo
 */
public class Fichero {
    
    public static void main (String[] args) {
        try {
            Process proceso = new 
                                ProcessBuilder("Python",
                                "C:\\Users\\Mondongo\\Desktop\\PGV\\proceso_python.py").start();
                                
                                BufferedReader br = new
                                                    BufferedReader(new 
                                InputStreamReader(proceso.getInputStream()));
                                                  proceso.waitFor();
                                                  int exitStatus = proceso.exitValue();
                                                  System.out.println("Retorno:" + br.readLine());
                                                  System.out.println("Valor de la salida:" + exitStatus);                                             
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
