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
### Dopo ogni compilazione (ad esempio dopo make clean all), è consigliato eseguire:

```sh
sh valgrind --tool=memcheck --leak-check=full ./nomefile
```
