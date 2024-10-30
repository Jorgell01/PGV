package pgv;

public class Ej15 {
    public static void main (String[] args) {
        for (int i = 1; i <= 5; i++) {
            int tareaNumero = i;
            Thread hilo = new Thread(new TareaRunnable(tareaNumero));
            hilo.start();
        }
    }
}

    class TareaRunnable implements Runnable {
        private int tareaNumero;

        public TareaRunnable(int tareaNumero) {
            this.tareaNumero = tareaNumero;
        }

        @Override
        public void run() {
            System.out.println("Ejecutando tarea " + tareaNumero + " ejecutandose en " + Thread.currentThread().getName());
        }
    }
