#include <stdio.h>
#include <string.h>

void upo_hex_fprint(FILE *stream, const void *p, size_t n){

    const unsigned char *str = (const unsigned char *)p;

    for(size_t i=0; i<n; i++){
        fprintf(stream, "%02x ", str[i]);   //02x stampa su stream in formato binario
    }
    fprintf(stream, "\n");
}

int main(){
    char *s = "Hello, World!";
    char cary[] = "GNU is Not Unix";
    char larry[] = "i gabinetti nella villa non ci stanno la gente va a urinare nei giardinetti e puzza come le la le latrine";

    fprintf(stdout, "%s\n", s);
    upo_hex_fprint(stdout, s, strlen(s));

    fprintf(stdout, "%s\n", cary);
    upo_hex_fprint(stdout, cary + (sizeof cary)/2, sizeof cary - (sizeof cary)/2);

    fprintf(stdout, "%s\n", larry);
    upo_hex_fprint(stdout, larry + (sizeof larry)/2, sizeof larry - (sizeof larry)/2);

    return 0;
}

