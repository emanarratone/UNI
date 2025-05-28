#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    int n,i;

    if (argc==2) n=atoi(argv[1]); else return 1;

    for(i=0;i<n;i++)
        putchar('q');
}
