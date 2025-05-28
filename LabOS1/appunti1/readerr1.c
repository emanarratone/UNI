#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include "call.h"

int main(int argc, char *argv[])
{
  int f,i,n,ne;
  char buf[20];

  call(f=open("fileprova",O_RDONLY),"Errore apertura file");
  printf("Numero bytes da leggere (max 20): ");
  scanf("%d",&n);
  call(ne=read(f,buf,n),"Errore Read");
  printf("letti %d caratteri: ",ne);
  for (i=0;i<ne;i++) putchar(buf[i]);
  printf("\n");
  return 0;
}

