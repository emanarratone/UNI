Algoritmi1
Questa directory contiene materiali, esercizi e implementazioni relativi al corso di Algoritmi 1. La maggior parte dei file sono scritti in linguaggio C.

Struttura
/ADT — Implementazioni di Abstract Data Types (tipi di dato astratti) come liste, stack, code, alberi, ecc.
Altri file e cartelle possono contenere esercizi, esempi svolti a lezione e soluzioni di compiti.
Come compilare ed eseguire
Per compilare i programmi presenti in questa cartella, è possibile usare make (se disponibile un Makefile) o compilare manualmente con gcc. Ad esempio:

sh
make clean all
oppure

sh
gcc -o nome_programma nome_file.c
Dopo ogni compilazione (ad esempio dopo make clean all), è consigliato eseguire:

sh
valgrind --tool=memcheck --leak-check=full ./nomefile
Sostituisci nomefile con il nome dell’eseguibile generato, per verificare la presenza di eventuali memory leak o errori di gestione della memoria.
