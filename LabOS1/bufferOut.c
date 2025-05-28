#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    int k,s;
 pid_t m,n;
 k=0;
 n=fork();
 if (n==(pid_t)0) {
 k=k+5;
 }
 else {
 k=k+10;
 m=wait(&s);
 printf("k=%d\n",k);
 }
}
