#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>


void proc(int i)
{
  int n;

  printf("Processo %d con pid %d\n",i,getpid());
  for (n=0;n<500000000;n++);
}

int main(int argc, char *argv[])
{
  int i;
  pid_t pid;

  for(i=0;i<5;i++)
	if (fork()==0)
		{ proc(i); exit(0);};
  for(i=0;i<5;i++)
	{ pid=wait(0);
	  printf("Terminato processo %d\n",pid);
	}
  return 0; /* ... ma facciamo male a non verificare errori nelle system calls */
}
