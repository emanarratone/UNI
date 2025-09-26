#ifndef QUEUE_PRIVATE_H_INCLUDED
#define QUEUE_PRIVATE_H_INCLUDED

#include <stddef.h>
#include <upo/queue.h>

typedef struct upo_queue_node_s{

    void *data;
    struct upo_queue_node_s *next;

}upo_queue_node_t;

struct upo_queue_s
{
    upo_queue_node_t *top; 
    upo_queue_node_t *bottom; //ultimo elemento della lista, per avere O(1)
    size_t size; 
};

#endif // QUEUE_PRIVATE_H_INCLUDED
