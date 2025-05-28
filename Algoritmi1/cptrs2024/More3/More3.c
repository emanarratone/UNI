#include<stdio.h>
#include <string.h>

int upo_mem_cmp(const void *p1, const void *p2, size_t n){

    const unsigned char* ptr1 = (const unsigned char*)p1;   //va inizializzato, (null non farebbe funzionare memcpy)
    memcpy(&ptr1, (const unsigned char*)p1, n);             //questa memcpy serve per utilizzare solo i primi n byte come richiesto
    const unsigned char* ptr2 = (const unsigned char*)p2;
    memcpy(&ptr2, (const unsigned char*)p2, n);

    if(ptr1 == ptr2) return 0;
    else return (ptr1 > ptr2) ? 1 : -1;
}

int main(){
int a = 123456789;
int b = 123456789;
fprintf(stdout, "\n%d\n", upo_mem_cmp(&a, &b, 4));

    return 0;
}
