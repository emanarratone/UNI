#include "bst_private.h"
#include <assert.h>

int getRank(upo_bst_node_t* node)
{
    if(node != NULL){
        if(node->left == NULL && node->right == NULL) return 1;
        else return 1 + getRank(node->left) + getRank(node->right);
    }
    else return 0;
}

int rangoImpl(upo_bst_node_t* node, void* key, upo_bst_comparator_t key_cmp)
{
    if(node == NULL) return -1;
    int cmp = key_cmp(key, node->key);
    if(cmp < 0) return rangoImpl(node->left, key, key_cmp);
    else if(cmp > 0) return rangoImpl(node->right, key, key_cmp);
    else return getRank(node->left);
}

int rangoNodo(upo_bst_t tree, void* key)
{
    if(tree != NULL && tree->root != NULL){
        return rangoImpl(tree->root, key, tree->key_cmp);

    }
    else return -1;
}

upo_bst_node_t* getPredecessor(upo_bst_node_t* node)
{
    if(node == NULL) return NULL;
    if(node->right != NULL) return getPredecessor(node->right);
    else return node;
}


upo_bst_node_t* predecessorImpl(upo_bst_node_t* node, const void* key, upo_bst_comparator_t key_cmp)
{
    if(node == NULL) return NULL;
    int cmp = key_cmp(key, node->key);
    if(cmp < 0) return predecessorImpl(node->left, key, key_cmp);
    else if(cmp < 0) return predecessorImpl(node->left, key, key_cmp);
    else return getPredecessor(node->left);    //accede al sotto albero sinistro del target
}

void* upo_bst_predecessor(const upo_bst_t bst, const void *key)
{
    if(bst == NULL) return NULL;
    else{
    upo_bst_node_t* res = predecessorImpl(bst->root, key, bst->key_cmp);
    if(res != NULL) return res->key;
    else return NULL;
    }

}

