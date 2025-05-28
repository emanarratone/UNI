#include <pthread.h>
#include <stdio.h>

void *tbody(void *arg)
{

        int m,n;

	for (m=0;m<20;m++)
	{

	  printf("working\n");
	}

	pthread_exit(NULL);

}


int main(int argc, char *argv[])
{
        pthread_t t;
        void *status;

        pthread_create(&t, NULL, tbody, NULL);

	printf("un carattere, prego\n");

        getchar(); /* il thread si sospende */

	printf("visto il carattere\n");

        pthread_join(t, &status);

	return 0;
}

