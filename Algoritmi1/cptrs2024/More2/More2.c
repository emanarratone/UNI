#include<stdio.h>
#include <string.h>

void upo_mem_set(void *p, unsigned char c, size_t n){

    unsigned char* ptr = (unsigned char*)p;

    for(size_t i=0; i<n; i++){
        memcpy(&ptr[i], &c, sizeof(c));     //easiest shit ever
    }
}

int main(){

char cary[] = "Hello, World!";
upo_mem_set(cary, '?', strlen(cary));

fprintf(stdout, "%s", cary);

int i = 10;
upo_mem_set(&i, 0, sizeof i);

fprintf(stdout, "%d", i);

    return 0;
}
