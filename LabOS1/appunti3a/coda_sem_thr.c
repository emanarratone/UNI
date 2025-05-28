#include <stdlib.h>
#include <stdio.h>
#include <semaphore.h>
#include <pthread.h>

#include <unistd.h>
#include <stdlib.h>
#include <stdint.h>

/* verifica gestione coda semafori */

sem_t sem; /* creo semaforo posix*/


void *tf(void *p)
{
  printf("Thread %lu prima della sem_wait\n",(uintptr_t)p);
  sem_wait(&sem);
  printf("Thread %lu dopo la sem_wait\n",(uintptr_t)p);
  pthread_exit(NULL);
}

int main()
{
uintptr_t i;
pthread_t t[5];


/* inizializzo semaforo sem a 0 (setta "rosso") */
if(sem_init(&sem,0,0)==-1) {perror("sem_init"); exit(0);}


for(i=0;i<5;i++)
	 { pthread_create(&t[i], NULL, tf, (void *)i);sleep(1);}

for(i=0;i<5;i++)
	{sem_post(&sem);
	sleep(1);
	}
for(i=0;i<5;i++)
	{ pthread_join(t[i], NULL);
	  printf("Terminato thread %lu\n",i);
	}
sem_destroy(&sem);

return 0;

}
