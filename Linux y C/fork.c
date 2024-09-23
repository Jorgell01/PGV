#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
int main (void) {
	int contador=0;
	printf("Comenzando la ejecucion\n");
	pid_t idHijo;
	pid_t idPadre;
	idPadre = getpid();
	printf("Antes de bifurcar\n");
	contador++;
	idHijo = fork();
	contador++;
	printf("Despues de bifurcar\n");
	if (idHijo == 0) {
		printf("Id. hijo:%ld Id. padre:%ld Contador:%d \n", (long)getpid(), (long)idPadre, contador);
	} else {
		printf("Id. padre:%ld Id. hijo:%ld Contador:%d \n", (long)getpid(), (long)idPadre, contador);	      }
	return 0;
	}
