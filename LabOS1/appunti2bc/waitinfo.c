#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

int main(int argc, char *argv[])
{

  int i,j,k,s;
  pid_t n,ret;

  if (argc==2) k=atoi(argv[1]);
	else { fprintf(stderr,"un numero di giri come argomento\n"); return 1; }

	signal(SIGINT, SIG_IGN);
  n=fork();

  if (n==(pid_t)-1)
	{perror("fork fallita");
	 exit(1);
	};

  if (n==(pid_t)0) {
      for (j=0;j<k;j++) {
        for (i=0; i< 1000000000; i++);
        printf("     Figlio %d di %d giro %d \n",getpid(),getppid(),j);
      }
  }
  else { /* eseguito dal genitore */
      ret=wait(&s);
      if (ret== -1) perror("errore wait");
      else {
        if WIFEXITED(s) printf("processo %ld uscito\n",(long)ret);
        if WIFSIGNALED(s) printf("processo %ld terminato dal segnale %d \n",(long)ret,WTERMSIG(s));
      }
  }
  return 0;
}
