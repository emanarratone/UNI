# Quantum Identity Authentication (QIA)

### Autore: Emanuele Anarratone  
### Matricola: 20050533  

---

## Descrizione del progetto

Questo progetto implementa e analizza un protocollo di **Quantum Identity Authentication (QIA)**, ovvero un meccanismo di autenticazione dell'identità su canale quantistico basato su **entanglement** e protocolli di **Quantum Key Distribution (QKD)**.

L'obiettivo principale è dimostrare come, tramite uno scambio di stati entangled e l'uso di tecniche di hashing classico (HMAC), sia possibile verificare in maniera sicura l’identità di un mittente (Alice) verso un destinatario (Bob), anche in presenza di potenziali attacchi da parte di un intercettatore (Eve).

Il progetto è composto da due simulazioni:
- Una **senza attacco** (`QIA.ipynb`), in cui il protocollo si comporta correttamente in un ambiente ideale.
- Una **con attacco** (`QIA-Eve.ipynb`), in cui Eve interferisce con il canale quantistico per osservare l'effetto sull'autenticazione.

---

## Contesto teorico

Il protocollo sfrutta i concetti fondamentali della **meccanica quantistica**:
- **Entanglement** tra coppie di EPR generati da una sorgente.
- **Misurazioni casuali** in basi differenti (Z o X) da parte di Alice e Bob.
- **Verifica di correlazione** tra i risultati, utilizzati per identificare la presenza di un eavesdropper.
- **Hash-based Message Authentication Code (HMAC)** per garantire l’integrità e l’autenticità dei messaggi su canali classici.

In condizioni ideali, i risultati delle misure sono fortemente correlati. Tuttavia, se un'intercettazione avviene, l’entanglement viene disturbato e il tasso di errore cresce oltre una soglia tollerabile, portando a una negazione dell'autenticazione.

---

##  Struttura del repository

- `QIA.ipynb`: notebook che simula il protocollo QIA in assenza di attacco.
- `QIA-Eve.ipynb`: notebook che include una simulazione con l’interferenza attiva di Eve.

