#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

// ABUELO-HIJO-NIETO
int main() {
    pid_t pid, Hijo_pid, pid2, Hijo2_pid;

    pid = fork(); // Soy el Abuelo, creo a Hijo

    if (pid == -1) { // Ha ocurrido un error
        printf("No se ha podido crear el proceso hijo...\n");
        exit(-1);
    }

    if (pid == 0) { // Nos encontramos en el Proceso Hijo
        pid2 = fork(); // Soy el Hijo, creo a Nieto

        if (pid2 == -1) { // Ha ocurrido un error al crear el nieto
            printf("No se ha podido crear el proceso hijo en el HIJO...\n");
            exit(-1);
        }

        if (pid2 == 0) { // Nos encontramos en el proceso Nieto
            printf("\t\tSoy el proceso NIETO %d; Mi padre es = %d\n", getpid(), getppid());
        } else { // Nos encontramos en el proceso Hijo (padre del Nieto)
            Hijo2_pid = wait(NULL); // El Hijo espera que el Nieto termine
            printf("\tSoy el proceso HIJO %d, Mi padre es: %d.\n", getpid(), getppid());
            printf("\tMi hijo: %d terminó.\n", Hijo2_pid);
        }

    } else { // Nos encontramos en el Proceso Abuelo
        Hijo_pid = wait(NULL); // El Abuelo espera que el Hijo termine
        printf("Soy el proceso ABUELO: %d, Mi HIJO: %d terminó.\n", getpid(), pid);
    }

    exit(0);
}

