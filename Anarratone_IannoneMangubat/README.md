# Streaming Platform - Relazione Sviluppo

## Descrizione
Piattaforma di live streaming con supporto a utenti registrati e non, contenuti multimediali in diretta o registrati, gestione delle donazioni e dei contenuti inclusivi.

**Nota:** Il progetto si è concentrato esclusivamente sulla progettazione e realizzazione del **database**, che rappresenta il focus principale del lavoro svolto.

## Entità principali

- **Utente**
  - Registrato: può chattare, seguire streamer, abbonarsi, votare contenuti.
  - Non registrato: può solo visualizzare live.
  - Premium: accesso a contenuti esclusivi.
  - Fragile: accesso a contenuti inclusivi.

- **Streamer**
  - Utente registrato con canale personale.
  - Può diventare "affiliate" al raggiungimento di determinati parametri (minuti trasmessi, spettatori medi, follower).
  - Trasmette live e programma eventi tramite calendario.

- **Canale**
  - Contiene live, video, clip.
  - Associa social, trailer, descrizione.

- **Contenuti**
  - Live (in diretta), Video (registrati), Clip.
  - Associati a hashtag, emoji, categoria.

- **Calendario**
  - Programmazione delle dirette.

- **Portafoglio**
  - Sistema di valuta virtuale (bit) per donazioni.

- **Hosting**
  - Ogni canale richiede pagamento mensile per hosting.

## Regole e vincoli principali

- Utenti registrati devono fornire dati identificativi.
- Streamer possiede un solo canale.
- Contenuti devono avere metadati completi.
- Sistema di raccomandazione (followee) per suggerire contenuti.
- Sistema di votazione su scala 1-10.

## Modellazione

- Schema E-R con eliminazione di generalizzazioni e accorpamento entità figlie (es. Clip e Video).
- Scelte di ridondanza valutate per performance (chat e portafoglio).
- Normalizzazione con vincoli di integrità e chiavi primarie/esterne esplicite.
