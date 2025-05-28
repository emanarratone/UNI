#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>

int k;
int n=10;

sem_t s;

void *tbody(void *arg)
{

        int j;

        for (j=0;j<k;j++)  {
		sem_wait(&s);
		n++;
		sem_post(&s);
	}			

        pthread_exit(NULL); 
}

int main(int argc, char *argv[])
{
        int j;
        pthread_t t;

	sem_init(&s,0,1);

	if (argc!=2) {
		fprintf(stderr,"Chiamare con un argomento numerico\n");
		exit(1);
	}
 
        k = atoi(argv[1]);

        pthread_create(&t, NULL, tbody, NULL);

        for (j=0;j<k;j++)  {
		sem_wait(&s);
		n--;
		sem_post(&s);
	}			

        pthread_join(t, NULL);

        printf(" n = %d \n",n);

	return 0;
}


