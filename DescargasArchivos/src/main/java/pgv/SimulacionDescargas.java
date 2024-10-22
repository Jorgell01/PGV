package pgv;

class Descarga implements Runnable {
    private String nombre_archivo;
    private int tamanio; // en MB
    private int velocidadDescarga; // en MB por segundo

    public Descarga(String nombre_archivo, int tamanio, int velocidadDescarga) {
        this.nombre_archivo = nombre_archivo;
        this.tamanio = tamanio;
        this.velocidadDescarga = velocidadDescarga;
    }

    @Override
    public void run() {
        int descargado = 0;

        // Simular la descarga en un bucle, mostrando el progreso
        while (descargado < tamanio) {
            try {
                // Esperar 1 segundo para simular la descarga
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Descarga interrumpida: " + nombre_archivo);
                return;
            }

            // Incrementar la cantidad descargada según la velocidad
            descargado += velocidadDescarga;
            if (descargado > tamanio) {
                descargado = tamanio; // Para no sobrepasar el tamaño del archivo
            }

            // Mostrar el progreso
            System.out.println("Archivo: " + nombre_archivo + " - Descargado: " + descargado + "/" + tamanio + " MB");
        }

        // Notificar que la descarga ha terminado
        System.out.println("Descarga completada: " + nombre_archivo);
    }
}

public class SimulacionDescargas {
    public static void main(String[] args) {
        // Crear hilos para cada descarga
        Thread descarga1 = new Thread(new Descarga("Archivo1.mp4", 100, 10)); // 100 MB, 10 MB/s
        Thread descarga2 = new Thread(new Descarga("Archivo2.zip", 200, 20)); // 200 MB, 20 MB/s
        Thread descarga3 = new Thread(new Descarga("Archivo3.pdf", 50, 5));   // 50 MB, 5 MB/s

        // Iniciar los hilos (las descargas comenzarán al mismo tiempo)
        descarga1.start();
        descarga2.start();
        descarga3.start();

        // Esperar a que todos los hilos terminen antes de finalizar el programa
        try {
            descarga1.join(); // Espera a que este hilo termine
            descarga2.join(); // Espera a que este hilo termine
            descarga3.join(); // Espera a que este hilo termine
        } catch (InterruptedException e) {
            System.out.println("Error en la espera de las descargas.");
        }

        // Mensaje final
        System.out.println("Todas las descargas han finalizado.");
    }
}
