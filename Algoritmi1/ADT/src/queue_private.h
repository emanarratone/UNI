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
    upo_queue_node_t *top; /**< The front of the list. */
    upo_queue_node_t *bottom; //ultimo elemento della lista, per avere O(1), viva l'anarchia, fuoco al commissariato
    size_t size; /**< The size of the list. This field allows to guarantee a constant complexity for the `size` operation. */
};

#endif // QUEUE_PRIVATE_H_INCLUDED
