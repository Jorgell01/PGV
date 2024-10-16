package pgv;

class Escenario {
    private String nombre_escenario;
    private boolean disponible = true; //De inicio está libre

    public Escenario(String nombre_escenario) {
        this.nombre_escenario = nombre_escenario;
    }

    //Método para que un grupo suba al escenario
    public void usarEscenario(String nombreGrupo, int duracion) {
        while (!disponible) {
            System.out.println(nombreGrupo + " está esperando a que el escenario " + nombre_escenario + " esté libre");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupción durante la espera");
            }
        }

        disponible = false;
        System.out.println("Grupo " + nombreGrupo + " está utilizando el escenario " + nombre_escenario + " por " + duracion + " horas");
        try {
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupción durante el uso de escenario");
        }

        //Liberar el escenario
        System.out.println("Grupo " + nombreGrupo + " ha terminado de usar el escenario " + nombre_escenario);
        disponible = true;
    }
}

class Grupo extends Thread {
    private String nombreGrupo;
    private Escenario escenario;
    private int duracion;

    public Grupo(String nombreGrupo, Escenario escenario, int duracion) {
        this.nombreGrupo = nombreGrupo;
        this.escenario = escenario;
        this.duracion = duracion;
    }

    @Override
    public void run() {
        //Grupo intenta usar escenario
        escenario.usarEscenario(nombreGrupo, duracion);
    }
}

public class SimulacionEscenario {
    public static void main(String[] args) {
        //Crear escenarios
        Escenario escenario1 = new Escenario("Aguere");
        Escenario escenario2 = new Escenario("Recinto Ferial");

        //Crear varios grupos (hilos)
        Grupo grupo1 = new Grupo("Gatitas", escenario1, 3);
        Grupo grupo2 = new Grupo("Mamelungas", escenario2, 1);
        Grupo grupo3 = new Grupo("Bufarras", escenario1, 4);
        Grupo grupo4 = new Grupo("Cachorras", escenario2, 5);

        //Iniciar hilos
        grupo1.start();
        grupo2.start();
        grupo3.start();
        grupo4.start();

        //Esperar a que acaben los hilos
        try {
            grupo1.join();
            grupo2.join();
            grupo3.join();
            grupo4.join();
        } catch (InterruptedException e) {
            System.out.println("Error en la espera de los hilos");
        }

        System.out.println("Todos los grupos han terminado de tocar");

    }
}
