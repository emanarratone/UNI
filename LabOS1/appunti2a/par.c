#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>  // Necessario per wait()

int main(int argc, char *argv[])
{
    int i, j, status;
    pid_t n;

    n = fork();
    if (n == (pid_t)-1) {
        perror("Fork fallita");
        exit(1);
    }

    if (n == 0) { // Processo figlio
        for (j = 0; j < 5000; j++) {
            for (i = 0; i < 100000000; i++); // Simula lavoro pesante   anche sleep(1234) va bene (valore a caso)
            printf("     Figlio %d di %d giro %d \n", getpid(), getppid(), j);
        }
    } else { // Processo padre
        for (j = 0; j < 20; j++) {
            for (i = 0; i < 100000000; i++);
            printf("Padre %d di %d giro %d\n", getpid(), n, j);
        }

        wait(&status);

        // Controlla se il figlio è terminato correttamente
        if (WIFEXITED(status)) {
            printf("Il figlio %d ha terminato con codice %d\n", n, WEXITSTATUS(status));
        }
        else if(WIFSIGNALED(status)) {
            printf("Il figlio %d ha terminato con un segnale esterno con codice %d\n", n, WEXITSTATUS(status));
        }
    }
    return 0;
}
