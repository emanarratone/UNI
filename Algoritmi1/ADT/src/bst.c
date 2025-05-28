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

#include "bst_private.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

upo_bst_node_t* upo_bst_putImpl(upo_bst_node_t* n, void *key, void *value, void* v_old, upo_bst_comparator_t cmp);
upo_bst_node_t* upo_bst_insertImpl(upo_bst_node_t* n, void *key, void *value, upo_bst_comparator_t cmp);
upo_bst_node_t* upo_bst_getImpl(upo_bst_node_t* n, const void *k, upo_bst_comparator_t cmp);
upo_bst_node_t* upo_bst_deleteImpl(upo_bst_node_t* n, const void *k, upo_bst_comparator_t cmp, int destroy_data);
upo_bst_node_t* delete1CImpl(upo_bst_node_t* n, int destroy_data);
upo_bst_node_t* delete2CImpl(upo_bst_node_t* n, upo_bst_comparator_t cmp, int destroy_data);
upo_bst_node_t* maxImpl(upo_bst_node_t* n);
size_t upo_bst_sizeImpl(upo_bst_node_t* n);
size_t upo_bst_heightImpl(upo_bst_node_t* n);
bool isLeaf(upo_bst_node_t* n);
void upo_bst_traverse_in_order_impl(upo_bst_node_t* n, upo_bst_visitor_t visit, void *visit_context);
int max(int a, int b);
void* upo_bst_min_impl(upo_bst_node_t* n);
void* upo_bst_max_impl(upo_bst_node_t* n);
void* upo_bst_floor_impl(upo_bst_node_t* n, const void* key, upo_bst_comparator_t cmp, upo_bst_node_t *floor);
void* upo_bst_ceiling_impl(upo_bst_node_t* n, const void* key, upo_bst_comparator_t cmp, upo_bst_node_t *ceiling);
upo_bst_key_list_t keysRangeImpl(upo_bst_node_t* n, const void *lo, const void *hi, upo_bst_key_list_t lis, upo_bst_comparator_t cmp);
upo_bst_key_list_t keysImpl(upo_bst_node_t* n, upo_bst_key_list_t lis);
int upo_bst_is_bst_impl(upo_bst_node_t *node, upo_bst_comparator_t key_cmp, const void* min_key, const void *max_key);


/**** EXERCISE #1 - BEGIN of FUNDAMENTAL OPERATIONS ****/


upo_bst_t upo_bst_create(upo_bst_comparator_t key_cmp)
{
    upo_bst_t tree = malloc(sizeof(struct upo_bst_s));
    if (tree == NULL)
    {
        perror("Unable to create a binary search tree");
        abort();
    }

    tree->root = NULL;
    tree->key_cmp = key_cmp;

    return tree;
}

void upo_bst_destroy(upo_bst_t tree, int destroy_data)
{
    if (tree != NULL)
    {
        upo_bst_clear(tree, destroy_data);
        free(tree);
    }
}

void upo_bst_clear_impl(upo_bst_node_t *node, int destroy_data)
{
    if (node != NULL)
    {
        upo_bst_clear_impl(node->left, destroy_data);
        upo_bst_clear_impl(node->right, destroy_data);

        if (destroy_data)
        {
            free(node->key);
            free(node->value);
        }

        free(node);
    }
}

void upo_bst_clear(upo_bst_t tree, int destroy_data)
{
    if (tree != NULL)
    {
        upo_bst_clear_impl(tree->root, destroy_data);
        tree->root = NULL;
    }
}

void* upo_bst_put(upo_bst_t tree, void *key, void *value)
{
    if(tree != NULL && key != NULL && value != NULL){
    void* v_old = NULL;
    tree->root = upo_bst_putImpl(tree->root, key, value, v_old, tree->key_cmp);
    return v_old;
    }
    else return NULL;
}

upo_bst_node_t* upo_bst_putImpl(upo_bst_node_t* n, void *key, void *value, void* v_old, upo_bst_comparator_t cmp)
{
    v_old = NULL;
    if(n == NULL){
        upo_bst_node_t* root = malloc(sizeof(struct upo_bst_node_s));
        root->key = key;
        root->value = value;
        root->left = NULL;
        root->right = NULL;
        return root;

    }
    else if(cmp(key, n->key) < 0) n->left = upo_bst_putImpl(n->left, key, value, v_old, cmp);
    else if(cmp(key, n->key) > 0) n->right = upo_bst_putImpl(n->right, key, value, v_old, cmp);
    else {
        v_old = n->value;
        n->value = value;
    }
    return n;
}

void upo_bst_insert(upo_bst_t tree, void *key, void *value)
{
    tree->root = upo_bst_insertImpl(tree->root, key, value, tree->key_cmp);
}

upo_bst_node_t* upo_bst_insertImpl(upo_bst_node_t* n, void *key, void *value, upo_bst_comparator_t cmp)
{
    if(n == NULL){
        upo_bst_node_t* root = malloc(sizeof(struct upo_bst_node_s));
        root->key = key;
        root->value = value;
        root->left = NULL;
        root->right = NULL;
        return root;
    }
    else if(cmp(key, n->key) < 0) n->left = upo_bst_insertImpl(n->left, key, value, cmp);
    else if(cmp(key, n->key) > 0) n->right = upo_bst_insertImpl(n->right, key, value, cmp);
    return n;
}

void* upo_bst_get(const upo_bst_t tree, const void *key)
{
    if(tree == NULL) return NULL;
    upo_bst_node_t* n = upo_bst_getImpl(tree->root, key, tree->key_cmp);
    if(n != NULL) return n->value;
    else return NULL;
}

upo_bst_node_t* upo_bst_getImpl(upo_bst_node_t* n, const void *k, upo_bst_comparator_t cmp)
{
    if(n == NULL) return NULL;
    if(cmp(k, n->key) < 0 && n->left != NULL) return upo_bst_getImpl(n->left, k, cmp);
    else if(cmp(k, n->key) > 0 && n->right != NULL) return upo_bst_getImpl(n->right, k, cmp);
    else return n;
}

int upo_bst_contains(const upo_bst_t tree, const void *key)
{
    if(upo_bst_get(tree, key) != NULL) return 1;
    else return 0;
}

void upo_bst_delete(upo_bst_t tree, const void *key, int destroy_data)
{
    tree->root = upo_bst_deleteImpl(tree->root, key, tree->key_cmp, destroy_data);
}

upo_bst_node_t* upo_bst_deleteImpl(upo_bst_node_t* n, const void *k, upo_bst_comparator_t cmp, int destroy_data)
{
    if(n == NULL) return NULL;
    if(cmp(k, n->key) < 0) n->left = upo_bst_deleteImpl(n->left, k, cmp, destroy_data);
    else if(cmp(k, n->key) > 0) n->right = upo_bst_deleteImpl(n->right, k, cmp, destroy_data);
    else if (n->left != NULL && n->right != NULL)n = delete2CImpl(n, cmp, destroy_data);
    else n = delete1CImpl(n, destroy_data);
    return n;
}

upo_bst_node_t* delete1CImpl(upo_bst_node_t* n, int destroy_data)
{
    if (n == NULL) return NULL;
    upo_bst_node_t *node = (n->left != NULL) ? n->left : n->right;
    if(destroy_data) {
        free(n->key);
        free(n->value);
    }
    free(n);
    return node;
}

upo_bst_node_t* delete2CImpl(upo_bst_node_t* n, upo_bst_comparator_t cmp, int destroy_data)
{
    upo_bst_node_t *m = NULL;
    if(n->left == NULL) m = n;
    else m = maxImpl(n->left);
    n->key = m->key;
    n->value = m->value;
    n->left = upo_bst_deleteImpl(n->left, m->key, cmp, destroy_data);
    return n;
}

upo_bst_node_t* maxImpl(upo_bst_node_t* n)
{
    if(n == NULL) return NULL;
    if(n->right != NULL) return maxImpl (n->right);
    else return n;
}

size_t upo_bst_size(const upo_bst_t tree)
{
    if(tree == NULL) return 0;
    else return upo_bst_sizeImpl(tree->root);
}

size_t upo_bst_sizeImpl(upo_bst_node_t* n)
{
    if(n == NULL) return 0;
    return 1 + upo_bst_sizeImpl(n->left) + upo_bst_sizeImpl(n->right);
}

size_t upo_bst_height(const upo_bst_t tree)
{
    if(tree == NULL) return 0;
    else return upo_bst_heightImpl(tree->root);
}

size_t upo_bst_heightImpl(upo_bst_node_t* n)
{
    if(n == NULL || isLeaf(n)) return 0;
    return 1 + max(upo_bst_heightImpl(n->left),  upo_bst_heightImpl(n->right));
}

int max(int a, int b)
{
    if(a > b) return a;
    else return b;
}

bool isLeaf(upo_bst_node_t* n)
{
    if(n->left == NULL && n->right == NULL) return true;
    return false;
}

void upo_bst_traverse_in_order(const upo_bst_t tree, upo_bst_visitor_t visit, void *visit_context)
{
    if(tree != NULL) upo_bst_traverse_in_order_impl(tree->root, visit, visit_context);
    else return;
}

void upo_bst_traverse_in_order_impl(upo_bst_node_t* n, upo_bst_visitor_t visit, void *visit_context)
{
    if(n != NULL){
        upo_bst_traverse_in_order_impl(n->left, visit, visit_context);
        visit(n->key, n->value, visit_context);
        upo_bst_traverse_in_order_impl(n->right, visit, visit_context);

    }
}

int upo_bst_is_empty(const upo_bst_t tree)
{
    if(tree == NULL || tree->root == NULL) return 1;
    else return 0;
}


/**** EXERCISE #1 - END of FUNDAMENTAL OPERATIONS ****/


/**** EXERCISE #2 - BEGIN of EXTRA OPERATIONS ****/


void* upo_bst_min(const upo_bst_t tree)
{
    if(tree == NULL) return NULL;
    else if(tree->root != NULL) return upo_bst_min_impl(tree->root);
    else return NULL;
}

void* upo_bst_min_impl(upo_bst_node_t* n)
{
    if(n == NULL) return NULL;
    else if(n->left != NULL) return upo_bst_min_impl(n->left);
    else return n->key;
}

void* upo_bst_max(const upo_bst_t tree)
{
    if(tree == NULL) return NULL;
    else if(tree->root != NULL) return upo_bst_max_impl(tree->root);
    else return NULL;
}

void* upo_bst_max_impl(upo_bst_node_t* n)
{
    if(n == NULL) return NULL;
    else if(n->right != NULL) return upo_bst_max_impl(n->right);
    else return n->key;
}

void upo_bst_delete_min(upo_bst_t tree, int destroy_data)
{
    void *del = upo_bst_min(tree);
    if(del != NULL) upo_bst_delete(tree, del, destroy_data);
}

void upo_bst_delete_max(upo_bst_t tree, int destroy_data)
{
    void *del = upo_bst_max(tree);
    if(del != NULL) upo_bst_delete(tree, del, destroy_data);
}

void* upo_bst_floor(const upo_bst_t tree, const void *key)
{
    if(tree == NULL || key == NULL || tree->root == NULL) return NULL;
    return upo_bst_floor_impl(tree->root, key, tree->key_cmp, NULL);
}

void* upo_bst_floor_impl(upo_bst_node_t* n, const void* key, upo_bst_comparator_t cmp, upo_bst_node_t *floor)
{
    if(n == NULL) return floor != NULL ? floor->key : NULL; //arrivati in fondo floor è l'ultimo nodo minore della chiave corrente
    if(cmp(key, n->key) < 0) return upo_bst_floor_impl(n->left, key, cmp, floor);   //se la chiave è minore va a sx e floor = floor
    else if(cmp(key, n->key) > 0) return upo_bst_floor_impl(n->right, key, cmp, n); //se la chiave è maggiore va a dx e floor = node
    else return n->key;
}

void* upo_bst_ceiling(const upo_bst_t tree, const void *key)
{
    if(tree == NULL || key == NULL || tree->root == NULL) return NULL;
    return upo_bst_ceiling_impl(tree->root, key, tree->key_cmp, NULL);
}

void* upo_bst_ceiling_impl(upo_bst_node_t* n, const void* key, upo_bst_comparator_t cmp, upo_bst_node_t *ceiling)
{       //viceversa dell'altro
    if(n == NULL) return ceiling != NULL ? ceiling->key : NULL;
    if(cmp(key, n->key) < 0) return upo_bst_ceiling_impl(n->left, key, cmp, n);
    else if(cmp(key, n->key) > 0) return upo_bst_ceiling_impl(n->right, key, cmp, ceiling);
    else return n->key;
}

upo_bst_key_list_t upo_bst_keys_range(const upo_bst_t tree, const void *low_key, const void *high_key)
{
    if(tree == NULL || tree->root == NULL) return NULL;
    upo_bst_key_list_t lis = NULL;
    lis = keysRangeImpl(tree->root, low_key, high_key, NULL, tree->key_cmp);
    if(lis == NULL) fprintf(stderr, ".-.-.");
    return lis;
}

upo_bst_key_list_t keysRangeImpl(upo_bst_node_t* n, const void *lo, const void *hi, upo_bst_key_list_t lis, upo_bst_comparator_t cmp)
{
    if(n != NULL){
        lis = keysRangeImpl(n->left, lo, hi, lis, cmp);
        if(cmp(n->key, lo) >= 0 && cmp(n->key, hi) <= 0){
            upo_bst_key_list_node_t* newnode = malloc(sizeof(upo_bst_key_list_node_t));
            newnode->key = n->key;
            newnode->next = NULL;
            if (lis == NULL || cmp(lis->key, n->key) >= 0){
                newnode->next = lis;
                lis = newnode;
            }
            else {
                upo_bst_key_list_node_t* temp = lis;
                while (temp->next != NULL && cmp(temp->next->key, n->key) < 0) {
                    temp = temp->next;
                }
                newnode->next = temp->next;
                temp->next = newnode;
            }
        }
        lis = keysRangeImpl(n->right, lo, hi, lis, cmp);
    }
    return lis;
}

upo_bst_key_list_t upo_bst_keys(const upo_bst_t tree)
{
    if(tree == NULL || tree->root == NULL) return NULL;
    else return keysImpl(tree->root, NULL);
}

upo_bst_key_list_t keysImpl(upo_bst_node_t* n, upo_bst_key_list_t lis)
{
    if(n != NULL){
         lis = keysImpl(n->left, lis);
        upo_bst_key_list_node_t* new_node = malloc(sizeof(upo_bst_key_list_node_t));
            new_node->key = n->key;
            new_node->next = NULL;
            new_node->next = lis;
            lis = new_node;
        lis = keysImpl(n->right, lis);
    }
    return lis;
}

int upo_bst_is_bst(const upo_bst_t tree, const void *min_key, const void *max_key)
{
    if(tree != NULL) return upo_bst_is_bst_impl(tree->root, tree->key_cmp, min_key, max_key);
    return 0;
}

int upo_bst_is_bst_impl(upo_bst_node_t *node, upo_bst_comparator_t key_cmp, const void* min_key, const void *max_key)
{
    if(node != NULL){
        int cmp_min = key_cmp(min_key, node->key);
        int cmp_max = key_cmp(node->key, max_key);
        if(cmp_min > 0 || cmp_max > 0){
            return 0;
        }
        return upo_bst_is_bst_impl(node->left, key_cmp, min_key, node->key) && upo_bst_is_bst_impl(node->right, key_cmp, node->key, max_key);
    }
    else return 1;
}


/**** EXERCISE #2 - END of EXTRA OPERATIONS ****/


upo_bst_comparator_t upo_bst_get_comparator(const upo_bst_t tree)
{
    if (tree == NULL)
    {
        return NULL;
    }

    return tree->key_cmp;
}
