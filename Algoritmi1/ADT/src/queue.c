#include "queue_private.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

upo_queue_node_t* newBottom(upo_queue_t queue)
;

upo_queue_t upo_queue_create()
{
    upo_queue_t q = malloc(sizeof(struct upo_queue_s));

    if(q == NULL){
        perror("Unable to create a queue");
        abort();
    }

    q->top = NULL;
    q->bottom = NULL;
    q->size = 0;
    return q;
}

void upo_queue_destroy(upo_queue_t queue, int destroy_data)
{
    if(queue != NULL){
        upo_queue_clear(queue, destroy_data);
        free(queue);
    }
}

void upo_queue_clear(upo_queue_t queue, int destroy_data)
{
    if(queue != NULL){
        while(queue->bottom != NULL){
            upo_queue_node_t* btm = queue->bottom;
            queue->bottom = newBottom(queue);
            if(destroy_data)free(btm->data);
            free(btm);
            queue->size--;
        }
    }
}

void upo_queue_enqueue(upo_queue_t queue, void* data)
{
    if(queue != NULL && data != NULL){

        if(queue->bottom == NULL){ //coda vuota
            upo_queue_node_t* newNode = malloc(sizeof(struct upo_queue_node_s));
            newNode->data = data;
            newNode->next = NULL;
            queue->top = newNode;
            queue->bottom = newNode;
        }
        else{
            upo_queue_node_t* newNode = queue->bottom->next;
            newNode = malloc(sizeof(struct upo_queue_node_s));
            newNode->data = data;
            newNode->next = NULL;
            queue->bottom = newNode;
        }

        queue->size ++;
    }
}

upo_queue_node_t* newBottom(upo_queue_t queue)
{
    if(queue == NULL) return NULL;
    upo_queue_node_t *top = queue->top;
    if(top == NULL) return NULL;
    while(top->next != NULL && top->next->next != NULL) top = top->next;
    return top->next;
}

void upo_queue_dequeue(upo_queue_t queue, int destroy_data)
{
    if(queue != NULL){
        upo_queue_node_t *bottom = queue->bottom;
        queue->bottom = newBottom(queue);
        if(destroy_data)free(bottom->data);
        free(bottom);
        queue->size--;
    }
}

int upo_queue_is_empty(upo_queue_t queue)
{
    if(queue == NULL) return 1;
    else return (queue->size == 0) ? 1 : 0;
}

size_t upo_queue_size(upo_queue_t queue)
{
    return (queue == NULL) ? 0 : queue->size;
}

void* upo_queue_peek(const upo_queue_t queue)
{
    return (queue == NULL) ? NULL : queue->top->data;
}

void* upo_queue_bottom(const upo_queue_t queue)
{
    return (queue == NULL) ? NULL : queue->bottom->data;
}




