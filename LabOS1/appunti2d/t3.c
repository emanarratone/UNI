#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void *tbody(void *arg)
{
        int i,j;

        for (j=0;j<10;j++) {
                for (i=0; i< 500000000; i++);
                printf("Thread %c %d\n",*(char *)arg,j);
        }

        pthread_exit(NULL);
}


int main(int argc, char *argv[])
{
        pthread_t t[10];
        void *status;

	char *a;
        int i,n;

        if (argc!=2) { fprintf(stderr,"Passare un argomento da 1 a 10\n"); exit(1);};

        n=atoi(argv[1]);

        if (n<1 || n >10) { fprintf(stderr,"Passare un argomento da 1 a 10\n"); exit(1);};

	a=(char *)malloc(11);
        strcpy(a,"abcdefghij");

        for(i=0;i<n;i++)
                 pthread_create(&t[i], NULL, tbody, (void *)&a[i]);

        for(i=0;i<n;i++) pthread_join(t[i], &status);

	return 0;
}


