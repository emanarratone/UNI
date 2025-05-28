#include <stdio.h>
#include <signal.h>

void f_intr(int sig)
{
  printf("ricevuto il segnale numero : %d\n",sig);
}
int main(int argc, char *argv[])
{
  int m,n;
  struct sigaction sa;

  sa.sa_handler = f_intr;
  sigemptyset(&sa.sa_mask);
  sa.sa_flags = 0;
  sigaction(SIGINT,&sa,NULL);
  for (m=0;m<1000;m++)
  {
    printf("%d\n", getpid());
  }
  return 0;
}
