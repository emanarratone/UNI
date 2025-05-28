#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int k;
int n = 10;
pthread_mutex_t m;
pthread_cond_t zero, ten;

void *tbody(void *arg)
{
    int j;
    for (j = 0; j < k; j++) {
        pthread_mutex_lock(&m);
        while (n >= 10) { // Attendere che n sia modificabile
            pthread_cond_wait(&zero, &m);
        }
        n++;
        if(n == 10)pthread_cond_signal(&ten);
        pthread_mutex_unlock(&m);
    }
    pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
    int j;
    pthread_t t;

    if (argc != 2) {
        fprintf(stderr, "Chiamare con un argomento numerico\n");
        exit(1);
    }

    k = atoi(argv[1]);

    pthread_mutex_init(&m, NULL);
    pthread_cond_init(&zero, NULL);
    pthread_cond_init(&ten, NULL);

    pthread_create(&t, NULL, tbody, NULL);

    for (j = 0; j < k; j++) {
        pthread_mutex_lock(&m);
        while (n <= 0) { // Attendere che n sia modificabile
            pthread_cond_wait(&ten, &m);
        }
        n--;
        if(n == 0)pthread_cond_signal(&zero);
        pthread_mutex_unlock(&m);
    }

    pthread_join(t, NULL);

    printf("n = %d\n", n);


    return 0;
}
