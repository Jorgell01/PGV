#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

// Función para calcular el sumatorio
int calcularSumatorio(int n) {
    int sumatorio = 0;
    for (int i = 1; i <= n; i++) {  // Corregido el operador `<=` y el incremento
        sumatorio += i;
    }
    return sumatorio;
}

// Función para calcular el factorial
int calcularFactorial(int n) {
    int factorial = 1;
    for (int i = 1; i <= n; i++) {  // Corregido el operador `<=`
        factorial *= i;
    }
    return factorial;
}

int main() {
    int n;
    printf("Introduce un número entero: ");
    scanf("%d", &n);

    // Crear el primer proceso hijo
    pid_t pid1 = fork();
    if (pid1 == 0) {  // Corregido el operador `==` en la comparación
        // Este es el primer hijo
        int sumatorio = calcularSumatorio(n);
        printf("Hijo 1 ......(PID %d): Sumatorio = %d\n", getpid(), sumatorio);
        exit(0); // El primer hijo termina
    } else if (pid1 < 0) {
        // Error al crear el primer hijo
        perror("Error al crear el primer hijo");
        exit(1);
    }

    // Crear el segundo proceso hijo
    pid_t pid2 = fork();
    if (pid2 == 0) {  // Corregido el operador `==` en la comparación
        // Este es el segundo hijo
        int factorial = calcularFactorial(n);
        sleep(50); // solo para dar tiempo a verlo desde el task manager
        printf("Hijo 2 ...... (PID %d): Factorial = %d\n", getpid(), factorial);
        exit(0); // El segundo hijo termina
    } else if (pid2 < 0) {
        // Error al crear el segundo hijo
        perror("Error al crear el segundo hijo");
        exit(1);
    }

    // El padre espera a que ambos hijos terminen
    int status;
    waitpid(pid1, &status, 0);
    waitpid(pid2, &status, 0);
    printf("Adios!\n");
    return 0;
}

