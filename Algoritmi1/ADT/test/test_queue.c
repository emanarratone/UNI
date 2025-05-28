#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <upo/queue.h>

int main(){

    upo_queue_t queue = upo_queue_create();

    assert(queue);

    int* q = malloc(sizeof(int));
    *q = 1;

    upo_queue_enqueue(queue, q);

    assert(upo_queue_size(queue) == 1);

    assert(!upo_queue_is_empty(queue));

    assert(*(int *)upo_queue_peek(queue) == *q);
    assert(*(int *)upo_queue_bottom(queue) == *q);

    upo_queue_dequeue(queue, 1);

    assert(upo_queue_size(queue) == 0);

    assert(upo_queue_is_empty(queue));

    upo_queue_clear(queue, 1);

    upo_queue_destroy(queue, 1);

    fprintf(stdout, "\nTest Ultimato\n\n");


    return 0;
}
