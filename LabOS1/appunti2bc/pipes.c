#include <stdio.h>
#include <unistd.h>
#include <wait.h>
#include <sys/types.h>

int main(int argc, char *argv[])
{
  int pipefd[2];

  pipe (pipefd);

  if (fork() == (pid_t)0) {
	/* primo figlio esegue "wc -l " (conta righe) prendendo
           l'input da pipefd[0] */
	dup2(pipefd[0],0); //duplica file descriptor su stdin
	close(pipefd[0]);  //chiuso perché inutilizzato (è su stdin grazie alla precedente dup)
	close(pipefd[1]);   //chiude perche non è il compito del processo figlio 1 (si occupa solo di leggere)
	execlp("wc","wc","-l",NULL);
	perror("exec wc fallita");
  }
  else if (fork() == (pid_t)0) {
	/* secondo figlio esegue "ls -l" mandando l'output
	   su pipefd[1] */
	dup2(pipefd[1],1);  //duplica file descriptor su stdout
	close(pipefd[0]);   //chiude perche non è il compito del processo figlio 2 (si occupa solo di scrivere)
	close(pipefd[1]);   //chiuso perché inutilizzato (è su stdout grazie alla precedente dup)
	execlp("ls","ls","-l",NULL);
	perror("exec ls fallita");
       }
       else
       { /* processo padre chiude entrambi gli estremi della pipe
	    e attende i figli */
         close(pipefd[0]);
	 close(pipefd[1]);      //chiudono i file descriptor non utilizzati dal padre
	 wait(NULL);
	 wait(NULL);
       }
  return 0;
}
