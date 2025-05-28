#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

#include <unistd.h>
#include <stdlib.h>
#include <stdint.h>

pthread_barrier_t b;

void *tf(void *p)
{
  unsigned long n;	

  n=(unsigned long) p;  
  printf("Thread %lu prima della pthread_barrier_wait\n",n);
  pthread_barrier_wait(&b);
  printf("Thread %lu dopo la pthread_barrier_wait\n",n);
  pthread_exit(NULL);
}

int main()
{
uintptr_t i;	/* il tipo intero corrispondente ai puntatori: unsigned long */
pthread_t t[5];


pthread_barrier_init(&b, NULL, 5);

for(i=0;i<5;i++)
	 { pthread_create(&t[i], NULL, tf, (void *)i);sleep(1);}

for(i=0;i<5;i++)
	{ pthread_join(t[i], NULL);
	  printf("Terminato thread %lu\n",i);
	}

return 0;

}
