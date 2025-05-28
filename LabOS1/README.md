# LabOS1

Questo progetto contiene esercizi e materiali relativi al laboratorio di Sistemi Operativi (LabOS1) dell’Università.  
Le attività sono principalmente in linguaggio C, con focus su concetti fondamentali dei sistemi operativi.

## Contenuto

- Esempi di processi e thread
- Gestione della memoria
- Sincronizzazione e comunicazione tra processi
- Script di compilazione (`Makefile`)
- Altri esercizi pratici

## Struttura delle Cartelle

```
/src    — Codice sorgente degli esercizi  
/doc    — Documentazione e tracce delle esercitazioni  
Makefile — Script per la compilazione automatica  
```

## Requisiti

- GCC (compilatore C)
- Make

## Compilazione

Per compilare tutti gli esercizi principali:
```sh
make
```

Per compilare un file specifico (ad esempio `esempio.c`):
```sh
gcc -o esempio esempio.c
```

## Uso

Dopo la compilazione, puoi eseguire i programmi generati:
```sh
./nome_programma
```
