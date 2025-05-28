#include<stdio.h>
#include <string.h>
#include <assert.h>

int is_even(const void *v)
{
assert( v );
return *((const int*) v) % 2;
}

int upo_all_of(const void *base, size_t n, size_t sz, int (*pred)(const void *)){

    const unsigned char* pc = (const unsigned char*)base;

    int res = 0;

    for(size_t i=0; i<n; i++){
        res = pred(pc+i*sz);
        if(res) break;
    }
    return res;

}


int main(){

    int arr[] = {2, 4, 6, 8, 10};

    fprintf(stdout, "\n%d\n", upo_all_of(arr, 5, sizeof(arr), is_even));

    return 0;
}
