from pathlib import Path

# Contenuto del README per la directory Algoritmi1
algoritmi1_readme_content = """\
# Algoritmi1

Questa directory contiene materiali, esercizi e implementazioni relativi al corso di **Algoritmi 1**. La maggior parte dei file è scritta in linguaggio **C**.

## Struttura

- `/ADT` — Implementazioni di Abstract Data Types (tipi di dato astratti) come:
  - Liste
  - Stack
  - Code
  - Alberi

Altre cartelle e file possono contenere:
- Esercizi
- Esempi svolti a lezione
- Soluzioni di compiti

## Come compilare ed eseguire

### Con `make`
Se è presente un `Makefile`, puoi usare:

```sh
make clean all
```

### Manualmente con gcc
```sh
gcc -o nome_programma nome_file.c
```
### Dopo ogni compilazione  è consigliato eseguire:

```sh
valgrind --tool=memcheck --leak-check=full ./nomefile
```
Per verificare anche la presenza di **memory leak** o simili (dangling pointer, wild pointer...) che rappresenta il focus principale del corso e di questa directory.
