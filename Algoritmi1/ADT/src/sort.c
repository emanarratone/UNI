/* vim: set tabstop=4 expandtab shiftwidth=4 softtabstop=4: */

/*
 * Copyright 2015 University of Piemonte Orientale, Computer Science Institute
 *
 * This file is part of UPOalglib.
 *
 * UPOalglib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UPOalglib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UPOalglib.  If not, see <http://www.gnu.org/licenses/>.
 */

#include <assert.h>
#include "sort_private.h"
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void swap(void *a, void *b, size_t n);
void upo_mergesort_rec(void *base, size_t lo, size_t hi, size_t size, upo_sort_comparator_t cmp);
void upo_merge(void *base, size_t lo, size_t mid, size_t hi, size_t size, upo_sort_comparator_t cmp);
void upo_quicksort_rec(void *base, size_t lo, size_t hi, size_t size, upo_sort_comparator_t cmp);
int partition(void *base, size_t lo, size_t hi, size_t size, upo_sort_comparator_t cmp);


void upo_insertion_sort(void *base, size_t n, size_t size, upo_sort_comparator_t cmp)
{
    unsigned char *pc = base;
    size_t j;
    size_t i;
    for(i = 1; i <= n-1; i++){
        j = i;
        while(j > 0 && cmp(pc+j*size, pc+(j-1)*size) < 0){
            swap(pc+j*size, pc+(j-1)*size, size);
            j--;
        }
    }
}

void swap(void *a, void *b, size_t n)
{
    char *a1 = (char *)a;
    char *b1 = (char *)b;
    char temp;
        for(size_t i = 0; i < n; i++){
            temp = a1[i];
            a1[i] = b1[i];
            b1[i] = temp;
        }
}

void upo_merge_sort(void *base, size_t n, size_t size, upo_sort_comparator_t cmp)
{
    upo_mergesort_rec(base, 0, n-1, size, cmp);
}

void upo_mergesort_rec(void *base, size_t lo, size_t hi, size_t size, upo_sort_comparator_t cmp)
{
    if(lo >= hi) return;
    size_t mid = (lo + hi) / 2;
    upo_mergesort_rec(base, lo, mid, size, cmp);
    upo_mergesort_rec(base, mid+1, hi, size, cmp);
    upo_merge(base, lo, mid, hi, size, cmp);
}

void upo_merge(void *base, size_t lo, size_t mid, size_t hi, size_t size, upo_sort_comparator_t cmp)
{
    size_t i = 0;
    size_t j = mid + 1 - lo;
    unsigned char *pc = base;
    unsigned char *aux = malloc((hi-lo+1)*size); //malloc necessaria->complessità in spazio aumentata
    
    
    memcpy(aux, pc+lo*size, (hi-lo+1)*size); //devo passare i valori ad aux a partire dal primo (pc+lo*size), se utilizzassi solo 
                                             //pc non accederei ad alcun dato, (hi-lo+1)*size rappresenta il numero di elementi
                                             //che vanno trasferiti, moltiplicati per la loro dimensione
    
    for(size_t k = lo; k <= hi; k++){
        if(i > (mid - lo)){
            memmove(pc+k*size, aux+j*size, size);
            j++;
        }
        else if(j > (hi - lo)){
            memmove(pc+k*size, aux+i*size, size);
            i++;
        }
        else if(cmp(aux+j*size, aux+i*size) < 0){
             memmove(pc+k*size, aux+j*size, size);
            j++;
        }
        else{
            memmove(pc+k*size, aux+i*size, size);
            i++;
        }
    }
    free(aux);//free dopo malloc
}

void upo_quick_sort(void *base, size_t n, size_t size, upo_sort_comparator_t cmp)
{
    upo_quicksort_rec(base, 0, n-1, size, cmp);
}

void upo_quicksort_rec(void *base, size_t lo, size_t hi, size_t size, upo_sort_comparator_t cmp)
{
    if(lo >= hi) return;
    size_t j = partition(base, lo, hi, size, cmp);
    if(j > 0) upo_quicksort_rec(base, lo, j-1, size, cmp);
    upo_quicksort_rec(base, j+1, hi, size, cmp);
}

int partition(void *base, size_t lo, size_t hi, size_t size, upo_sort_comparator_t cmp)
{
    size_t p = lo;
    size_t i = lo;
    size_t j = hi + 1;
    unsigned char *pc = base;
    while(true){
        do{
            i++;
        }while(i <= hi && cmp(pc+i*size, pc+p*size) <= 0);  //i segni sono fucked up, unexplained
    
        do{
            j--;
        }while(j >= lo && cmp(pc+j*size, pc+p*size) >= 0);  //i segni sono fucked up, unexplained
        if(i >= j) break;
        swap(pc+i*size, pc+j*size, size);
    }
    swap(pc+p*size, pc+j*size, size);
    return j;
    
}


