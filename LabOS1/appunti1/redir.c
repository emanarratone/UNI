#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
   //andrebbe fatta call() con gestione dell'errore (solo visione del messaggio)
   int n=creat("pippo",0600);  /* ahi, non controlliamo eventuali errori... */

   //chiude il file descriptor 1 (standard output)
   close(1); /* chiude lo standard output */

   //n è il file descriptor associato al file pippo, viene duplicato nel primo posto disponibile (quello dello stdout)
   dup(n);   /* ora il descrittore 1 e' associato a pippo */

   close(n);
   //invece di stampare su stdout (quindi su finestra terminale) stampa sul file descriptor 1 (inizialmente assegnato a stdout) che in questo caso è associato ad n tramite la dup
   printf("Hello \n");  /* oppure write(1,"Hello \n",7); */
   return 0;
}
