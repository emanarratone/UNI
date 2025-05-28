#include <upo/bst.h>
#include <assert.h>
#include <stdio.h>

int int_compare(const void *a, const void *b)
{
    const int *aa = a;
    const int *bb = b;

    return (*aa > *bb) - (*aa < *bb);
}


int main(){
upo_bst_t tree = upo_bst_create(int_compare);

int keys[9] = {8, 3, 10, 1, 6, 14, 4, 7, 13};
int val[9] = {0, 1, 2, 3, 4, 5, 6, 7, 8};


for(int i=0; i<9; i++){
    upo_bst_put(tree, &keys[i], &val[i]);
}

assert(!upo_bst_is_empty(tree));
assert(upo_bst_size(tree) == 9);
int otto = 8;
int uno = 1;
assert(rangoNodo(tree, &otto) == 5);
assert(rangoNodo(tree, &uno) == 0);
assert(*(int *)(upo_bst_predecessor(tree, &otto)) == 7);
int zero = 0;
assert(upo_bst_predecessor(tree, &zero) == NULL);

upo_bst_destroy(tree, 0);
fprintf(stdout, "\nTest Finiti e Superati \n\n");
}
