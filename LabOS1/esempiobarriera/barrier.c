#include "apue.h"
#include <pthread.h>
#include <stdlib.h>
#include <limits.h>
#include <sys/time.h>

#define NTHR   16		/* max number of threads */
#define NUMNUM 8000000L		/* number of numbers to sort */

int nthr;			/* actual number of threads */
long tnum;			/* number to sort per thread */

long nums[NUMNUM];
long snums[NUMNUM];

pthread_barrier_t b;

extern void qsort(void *, size_t, size_t,
                    int (*)(const void *, const void *));

/*
 * Compare two long integers (helper function for qsort)
 */
int complong(const void *arg1, const void *arg2)
{
	long l1 = *(long *)arg1;
	long l2 = *(long *)arg2;

	if (l1 == l2)
		return 0;
	else if (l1 < l2)
		return -1;
	else
		return 1;
}

/*
 * Worker thread to sort a portion of the set of numbers.
 */
void * thr_fn(void *arg)
{
	long	idx = (long)arg;

	qsort(&nums[idx], tnum, sizeof(long), complong);
	pthread_barrier_wait(&b);

	return((void *)0);
}

/*
 * Merge the results of the individual sorted ranges.
 */
void merge()
{
	long	idx[NTHR];
	long	i, minidx, sidx, num;

	for (i = 0; i < nthr; i++)
		idx[i] = i * tnum;
	for (sidx = 0; sidx < NUMNUM; sidx++) {
		num = LONG_MAX;
		for (i = 0; i < nthr; i++) {
			if ((idx[i] < (i+1)*tnum) && (nums[idx[i]] < num)) {
				num = nums[idx[i]];
				minidx = i;
			}
		}
		snums[sidx] = nums[idx[minidx]];
		idx[minidx]++;
	}
}

int main(int argc, char **argv)
{
	unsigned long	i;
	struct timeval	start, end;
	long long	startusec, endusec;
	double		elapsed;
	int		err;
	pthread_t	tid;


	/*
	 * set nthr to first argument and tnum accordingly
         */

	if ((argc < 2) || argc > 3) { fprintf(stderr,"call: barrier num_of_threads(1-16) [outfile] \n"); exit(1);}

	nthr = atoi(argv[1]);

	if ((nthr > 16) || (nthr < 1)) { fprintf(stderr,"call: barrier num_of_threads (1-16) [outfile] \n"); exit(1);}

	tnum = NUMNUM/nthr;

	/*
	 * Create the initial set of numbers to sort.
	 */
	srandom(1);
	for (i = 0; i < NUMNUM; i++)
		nums[i] = random();

	/*
	 * Create nthr threads to sort the numbers.
	 */
	gettimeofday(&start, NULL);
	pthread_barrier_init(&b, NULL, nthr+1);
	for (i = 0; i < nthr; i++) {
		err = pthread_create(&tid, NULL, thr_fn, (void *)(i * tnum));
		if (err != 0)
			err_exit(err, "can't create thread");
	}
	pthread_barrier_wait(&b);
	merge();
	gettimeofday(&end, NULL);

	/*
	 * Print the sorted list.
	 */
	startusec = start.tv_sec * 1000000 + start.tv_usec;
	endusec = end.tv_sec * 1000000 + end.tv_usec;
	elapsed = (double)(endusec - startusec) / 1000000.0;
	printf("sort took %.4f seconds\n", elapsed);

	/* if there was a filename, print the numbers */

	if (argc == 3) {
	
		FILE *f = fopen(argv[2],"w");
	 	for (i = 0; i < NUMNUM; i++)
			fprintf(f,"%ld\n", snums[i]); 

	}

	exit(0);
}
