-- Popolamento tabella ListaSocial
INSERT INTO ListaSocial (idLista, nome_social, link_social)
VALUES 
(1, 'Facebook', 'https://facebook.com'),
(2, 'Twitter', 'https://twitter.com'),
(3, 'Instagram', 'https://instagram.com');

-- Popolamento tabella Canale
INSERT INTO Canale (NomeCanale, N_follower, idlista, descrizione, trailer)
VALUES 
('TechChannel', 1000, 1, 'Canale dedicato alla tecnologia.', 'https://example.com/trailer1'),
('Foodies', 500, 2, 'Canale per gli amanti del cibo.', 'https://example.com/trailer2'),
('FitnessLife', 750, 3, 'Canale per il fitness e lo sport.', 'https://example.com/trailer3');

-- Popolamento tabella Contenuti
INSERT INTO Contenuti (id_Contenuto, Canale, titolo, durata, descrizione, categoria, hashtag, emoji, inclusivo, commenti, reazioni, media_spettatori, premium, n_visual, tipo)
VALUES 
(1, 'TechChannel', 'Recensione gadget 2025', 10, 'Una dettagliata recensione sui migliori gadget del 2025.', 'Tecnologia', '#tech #gadget', '👍', true, 'Ottimo contenuto', '❤️🔥', 100.0, true, 500, 'Video'),
(2, 'Foodies', 'Ricette veloci', 15, 'Scopri 5 ricette facili e veloci per il pranzo.', 'Cucina', '#food #ricette', '🍝', false, 'Molto utile!', '👍💬', 50.0, false, 300, 'Video'),
(3, 'FitnessLife', 'Allenamento HIIT', 20, 'Un allenamento intenso per bruciare calorie in poco tempo.', 'Fitness', '#workout #hiit', '💪', true, 'Energizzante!', '🔥👏', 200.0, false, 700, 'Video');

-- Popolamento tabella Utente
INSERT INTO Utente(id_utente) values (1);
INSERT INTO Utente(id_utente) values (2);
INSERT INTO Utente(id_utente) values (3);
INSERT INTO Utente(id_utente) values (4);



-- Popolamento tabella Utente_registrato
INSERT INTO Utente_registrato (Utente, nome_utente, psw, dataRegistrazione, dataNascita, telefono, email, fragile)
VALUES 
(1, 'MarioRossi', 'password123', '2025-01-01', '1990-05-15', '1234567890', 'mario.rossi@example.com', false),
(2, 'AnnaBianchi', 'securepass', '2025-01-15', '1985-03-20', '0987654321', 'anna.bianchi@example.com', true),
(3, 'EdoardoNero', 'securepass', '2025-01-15', '1985-03-20', '0987654321', 'Edoardo.Nero@example.com', true);



-- Popolamento tabella Utente_nonregistrato
INSERT INTO Utente_nonregistrato (Utente)
VALUES 
(4);

-- Popolamento tabella UtentePremium
INSERT INTO UtentePremium (Utente, dataInizio)
VALUES 
(1, '2025-01-20');

-- Popolamento tabella Diventa
INSERT INTO Diventa (Utente_registrato, Utente_premium)
VALUES 
(1, 1);


-- Popolamento tabella Calendario
INSERT INTO Calendario (id_Calendario, idlive, datalive, oralive, titolo)
VALUES 
(1, 1, '2025-01-30', '20:00:00', 'Live sul futuro della tecnologia'),
(2, 2, '2025-01-30', '20:00:00', 'Live sul futuro della tecnologia'),
(3, 3, '2025-01-30', '20:00:00', 'Live sul futuro della tecnologia');


-- Popolamento tabella Streamer
INSERT INTO Streamer (Utente, nomeCanale, media_spettatori, affiliate, id_calendario, n_live, tot_min)
VALUES 
(1, 'TechChannel', 500.0, true, 1, 20, 1200),
(2, 'Foodies', 300.0, false, 2, 15, 900),
(3, 'FitnessLife', 150.0, false, 3, 5, 700);



-- Popolamento tabella Contenuti
INSERT INTO Contenuti (Canale, titolo, durata, descrizione, categoria, hashtag, emoji, inclusivo, commenti, reazioni, media_spettatori, premium, n_visual, tipo)
VALUES
('TechChannel', 'Recensione gadget 2025 - Parte 1', 10, 'Dettagli sui migliori gadget del 2025, prima parte.', 'Tecnologia', '#tech #gadget', '👍', true, 'Ottimo contenuto', '❤️🔥', 100.0, true, 500, 'Video'),
('TechChannel', 'Recensione gadget 2025 - Live', 12, 'Live sui gadget del 2025 con demo in diretta.', 'Tecnologia', '#techlive', '👍', true, 'Molto interessante!', '🔥👍', 120.0, false, 600, 'Live'),
('Foodies', 'Ricette veloci - Live', 15, '5 ricette facili e veloci per il pranzo, in live.', 'Cucina', '#foodie #cooking', '🍝', false, 'Davvero utile!', '👍💬', 50.0, false, 300, 'Live'),
('FitnessLife', 'Allenamento HIIT - Live', 25, 'Allenamento intenso per calorie, ora in diretta!', 'Fitness', '#workout #live', '💪', true, 'Energizzante e motivante!', '🔥👏', 200.0, false, 700, 'Live'),
('TechChannel', 'Recensione gadget 2025 - Replay', 10, 'Registrazione della live sui migliori gadget del 2025.', 'Tecnologia', '#techreplay', '👍', true, 'Ripetizione utile.', '❤️🔥', 100.0, true, 550, 'Live passata'),
('Foodies', 'Ricette veloci - Replay', 15, 'Replay di 5 ricette facili e veloci per il pranzo.', 'Cucina', '#foodie #replay', '🍝', false, 'Molto utile, anche in replica!', '👍💬', 50.0, false, 350, 'Live passata'),
('FitnessLife', 'Allenamento HIIT - Replay', 20, 'Replay dell’allenamento intenso HIIT.', 'Fitness', '#workout #replay', '💪', true, 'Grande motivazione anche in replica.', '🔥👏', 200.0, false, 800, 'Live passata');


-- Popolamento tabella Host
INSERT INTO Host (IP_host)
VALUES 
('192.168.0.1'),
('192.168.0.2'),
('192.168.0.3');


-- Popolamento tabella Paga
INSERT INTO Paga (Streamer, Host, dataInizio)
VALUES 
(1, '192.168.0.1', '2023-01-01'),
(2, '192.168.0.2', '2023-02-01'),
(3, '192.168.0.3', '2023-03-01');

-- Popolamento tabella Segue
INSERT INTO Segue (Utente_registrato, Streamer)
VALUES 
(1, 1),
(2, 2);

-- Popolamento tabella Dona
INSERT INTO Dona (Utente_registrato, Streamer)
VALUES 
(1, 2);

-- Popolamento tabella Abbona
INSERT INTO Abbona (Canale, UtentePremium)
VALUES 
('TechChannel', 1);

-- Popolamento tabella Valuta
INSERT INTO Valuta (Utente, Contenuti, voto)
VALUES 
(1, 1, 5);

-- Popolamento tabella Gestisce
INSERT INTO Gestisce (Host, Contenuti)
VALUES 
('192.168.0.1', 1), 
('192.168.0.1', 2),
('192.168.0.1', 3);


-- Popolamento tabella ListaContenuti
INSERT INTO ListaContenuti (Utente)
VALUES 
(1);

-- Popolamento tabella RaccoglieContenuti
INSERT INTO RaccoglieContenuti (ListaContenuti, Contenuti)
VALUES 
(1, 1);

-- Popolamento tabella Notifica
INSERT INTO Notifica (Utente_registrato, Contenuti)
VALUES 
(1, 1);
