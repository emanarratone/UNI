# TrialApp

TrialApp è un'applicazione Java progettata per la **registrazione e il monitoraggio di pazienti** all’interno di una struttura sanitaria. È stata realizzata interamente da **Emanuele Anarratone (Matricola 20050533)**, seguendo i paradigmi **MVC** e **TDD**.

## Funzionalità principali

- Gestione **record dei pazienti**
- Gestione **visite mediche**
- Area personale per **medici** e **infermieri**
- **Login** e separazione delle funzionalità per tipologia di utente
- Salvataggio su file per ogni tipo di utente

## Architettura

### Modello

#### Pazienti
- `Visita`: rappresenta una visita medica.
- `Record`: contiene le informazioni del paziente.
- `RecordManager`: gestisce record e visite, con strutture dati statiche (`ArrayList`, `HashMap`).

#### Personale Sanitario
- `PersonaleSanitario`: classe astratta.
- `Medico` / `Infermiere`: sottoclassi concrete.
  - Ogni membro ha una lista di pazienti.
  - Supporto per inserimento, ricerca, rimozione e visita.
  - `eseguiVisita()` somministra anche terapie (arraylist di stringhe).

### Controller

- `TrialAppApplication`: entry point dell'app.
- `HomeController`: schermata principale (accesso libero), gestisce inserimenti, ricerca, statistiche.
- `PSHomeController`: area personale (accesso tramite login), per gestire visite, orari e pazienti assegnati.

Un oggetto statico `User` viene usato per gestire il contesto utente dopo il login.

## Caratteristiche tecniche

- Diagrammi UML del **modello** e dei **controller**
  #### Modello:
  ![image](https://github.com/user-attachments/assets/234dc167-7f7c-4964-9d61-b5896720e941)

  #### Controller:
  ![image](https://github.com/user-attachments/assets/f3a802ba-e01a-4d58-83da-b3dfb2576799)


- **JUnit test** per tutte le classi del modello
- **Javadoc** per i componenti pubblici
- Nessuna interazione tramite console
- Ampio uso di:
  - Ereditarietà
  - Riutilizzo del codice
  - Visibilità e incapsulamento
  - Eccezioni (es. `IOException` in I/O e FXML)
  - Classi annidate (es. per reparti, parametri clinici)

## Classi annidate

- In `Visita`: enumerazione dei parametri, classi interne per livelli clinici.
- In `PersonaleSanitario`: enumerazione dei reparti ospedalieri.

## Requisiti

- JavaFX
- Java >= 11
- File system access per salvataggio/caricamento dati
