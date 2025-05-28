-- Tabella ListaSocial
CREATE TABLE ListaSocial (
    idlista SERIAL PRIMARY KEY,
    nome_social VARCHAR(50),
    link_social VARCHAR(255)
);

-- Tabella Canale
CREATE TABLE Canale (
    NomeCanale VARCHAR(100) PRIMARY KEY,
    N_follower INT default 0,
    idlista INT,
    descrizione TEXT,
    trailer TEXT,
    FOREIGN KEY (idlista) REFERENCES ListaSocial(idlista) ON DELETE SET NULL
);

-- Tabella Contenuti
CREATE TABLE Contenuti (
    id_contenuto SERIAL PRIMARY KEY,
    Canale VARCHAR(100),
    titolo VARCHAR(100),
    durata INT,
    descrizione TEXT,
    categoria VARCHAR(50),
    hashtag TEXT,
    emoji TEXT,
    inclusivo BOOLEAN,
    commenti TEXT,
    reazioni TEXT,
    media_spettatori FLOAT default 0,
    premium BOOLEAN default false,
    n_visual INT default 0,
    tipo VARCHAR(50),
    FOREIGN KEY (Canale) REFERENCES Canale(NomeCanale) ON DELETE CASCADE
);
-- Tabella Utente
CREATE TABLE Utente (
    id_utente SERIAL PRIMARY KEY
);

-- Tabella Utente_registrato
CREATE TABLE Utente_registrato (
    Utente INT PRIMARY KEY,
    nome_utente VARCHAR(50),
    psw VARCHAR(255),
    dataRegistrazione DATE,
    dataNascita DATE,
    telefono VARCHAR(20),
    email VARCHAR(100),
    fragile BOOLEAN default false,
    FOREIGN KEY (Utente) REFERENCES Utente(id_utente) ON DELETE CASCADE
);

-- Tabella Utente_nonregistrato
CREATE TABLE Utente_nonregistrato (
    Utente INT PRIMARY KEY,
    FOREIGN KEY (Utente) REFERENCES Utente(id_utente)  ON DELETE CASCADE
);

-- Tabella UtentePremium
CREATE TABLE UtentePremium (
    Utente INT PRIMARY KEY,
    dataInizio DATE,
    FOREIGN KEY (Utente) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE
);

-- Tabella Diventa
CREATE TABLE Diventa (
    Utente_registrato INT,
    Utente_premium INT,
    PRIMARY KEY (Utente_registrato, Utente_premium),
    FOREIGN KEY (Utente_registrato) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE,
    FOREIGN KEY (Utente_premium) REFERENCES UtentePremium(Utente) ON DELETE CASCADE
);

-- Tabella Spettatore_NonRegistrato
CREATE TABLE Spettatore_NonRegistrato (
    Utente INT PRIMARY KEY,
    FOREIGN KEY (Utente) REFERENCES Utente_nonregistrato(Utente) ON DELETE CASCADE
);

-- Tabella Spettatore_Registrato
CREATE TABLE Spettatore_Registrato (
    Utente INT PRIMARY KEY,
    FOREIGN KEY (Utente) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE
);

-- Tabella Chat
CREATE TABLE Chat (
    Mittente INT,
    Destinatario INT,
    PRIMARY KEY (Mittente, Destinatario),
    FOREIGN KEY (Mittente) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE,
    FOREIGN KEY (Destinatario) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE
);

-- Tabella Calendario
CREATE TABLE Calendario (
    id_calendario SERIAL PRIMARY KEY,
    idlive INT,
    datalive DATE,
    oralive TIME,
    titolo VARCHAR(255),
    FOREIGN KEY (idlive) REFERENCES Contenuti(id_contenuto) ON DELETE CASCADE
);

-- Tabella Streamer
CREATE TABLE Streamer (
    Utente INT PRIMARY KEY,
    nomeCanale VARCHAR(50),
    media_spettatori FLOAT default 0.0,
    affiliate BOOLEAN default false,
    id_calendario INT,
    n_live INT default 0,
    tot_min INT default 0,
    FOREIGN KEY (nomeCanale) REFERENCES Canale(nomeCanale) ON DELETE CASCADE,
    FOREIGN KEY (Utente) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE,
    FOREIGN KEY (id_calendario) REFERENCES Calendario(id_calendario) ON DELETE SET NULL
);

-- Tabella Dona
CREATE TABLE Dona (
    Utente_registrato INT,
    Streamer INT,
    PRIMARY KEY (Utente_registrato, Streamer),
    FOREIGN KEY (Utente_registrato) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE,
    FOREIGN KEY (Streamer) REFERENCES Streamer(Utente) ON DELETE CASCADE
);

-- Tabella Segue
CREATE TABLE Segue (
    Utente_registrato INT,
    Streamer INT,
    PRIMARY KEY (Utente_registrato, Streamer),
    FOREIGN KEY (Utente_registrato) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE,
    FOREIGN KEY (Streamer) REFERENCES Streamer(Utente) ON DELETE CASCADE
);


-- Tabella Host
CREATE TABLE Host (
    IP_host VARCHAR(45) PRIMARY KEY
);

-- Tabella Paga
CREATE TABLE Paga (
    Streamer INT,
    Host VARCHAR(45),
    dataInizio DATE,
    PRIMARY KEY (Streamer, Host),
    FOREIGN KEY (Streamer) REFERENCES Streamer(Utente) ON DELETE CASCADE,
    FOREIGN KEY (Host) REFERENCES Host(IP_host) ON DELETE CASCADE
);

-- Tabella Abbona
CREATE TABLE Abbona (
    Canale VARCHAR(50),
    UtentePremium INT,
    PRIMARY KEY (Canale, UtentePremium),
    FOREIGN KEY (Canale) REFERENCES Canale(NomeCanale) ON DELETE CASCADE,
    FOREIGN KEY (UtentePremium) REFERENCES UtentePremium(Utente) ON DELETE CASCADE
);

-- Tabella Valuta
CREATE TABLE Valuta (
    Utente INT,
    Contenuti INT,
    voto INT CHECK (voto between 1 and 10),
    PRIMARY KEY (Utente, Contenuti),
    FOREIGN KEY (Utente) REFERENCES Utente(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (Contenuti) REFERENCES Contenuti(id_contenuto) ON DELETE CASCADE
);

-- Tabella Gestisce
CREATE TABLE Gestisce (
    Host VARCHAR(15),
    Contenuti INT,
    PRIMARY KEY (Host, Contenuti),
    FOREIGN KEY (Host) REFERENCES Host(IP_host) ON DELETE CASCADE,
    FOREIGN KEY (Contenuti) REFERENCES Contenuti(id_contenuto) ON DELETE CASCADE
);

-- Tabella ListaContenuti
CREATE TABLE ListaContenuti (
    idlista SERIAL PRIMARY KEY,
    Utente INT,
    FOREIGN KEY (Utente) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE
);

-- Tabella RaccoglieContenuti
CREATE TABLE RaccoglieContenuti (
    ListaContenuti INT,
    Contenuti INT,
    PRIMARY KEY (ListaContenuti, Contenuti),
    FOREIGN KEY (ListaContenuti) REFERENCES ListaContenuti(idlista) ON DELETE CASCADE,
    FOREIGN KEY (Contenuti) REFERENCES Contenuti(id_contenuto) ON DELETE CASCADE
);

-- Tabella Notifica
CREATE TABLE Notifica (
    Utente_registrato INT,
    Contenuti INT,
    PRIMARY KEY (Utente_registrato, Contenuti),
    FOREIGN KEY (Utente_registrato) REFERENCES Utente_registrato(Utente) ON DELETE CASCADE,
    FOREIGN KEY (Contenuti) REFERENCES Contenuti(id_contenuto) ON DELETE CASCADE
);
