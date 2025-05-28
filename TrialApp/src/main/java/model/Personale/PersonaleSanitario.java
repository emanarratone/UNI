package model.Personale;

import model.Pazienti.Pazienti.Record;
import model.Pazienti.Visita;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static model.Pazienti.Pazienti.RecordManager.makeID;

/**
 * La classe astratta Personale Sanitario introduce l'ereditarietà e introduce versioni diverse
 * delle schermate in javaFX
 */
public abstract class PersonaleSanitario implements Serializable {

    @Serial
    private static final long serialVersionUID = 9096149685198435684L;

    /** Nome del personale sanitario */
    protected String Nome;

    /** Cognome del personale sanitario */
    protected String Cognome;

    /** Data di nascita del personale sanitario */
    protected LocalDate dataN;

    /** ID del personale sanitario */
    protected String id;

    /** Data di assunzione del personale sanitario */
    protected LocalDate dataAss;

    /** Reparto di lavoro del personale sanitario */
    protected Reparto Reparto;

    /** Password associata al personale sanitario */
    protected String password;

    /**
     * Enumerazione per i reparti in cui può lavorare il personale sanitario.
     */
    public enum Reparto {
        ProntoSoccorso,
        Pediatria,
        Cardiologia,
        Chirurgia,
        TerapiaIntensiva,
        Dermatologia
    }

    /**
     * Costruttore della classe PersonaleSanitario che inizializza i dati del personale.
     *
     * @param Nome      Nome del personale sanitario.
     * @param Cognome   Cognome del personale sanitario.
     * @param dataN     Data di nascita del personale sanitario.
     * @param dataAss   Data di assunzione del personale sanitario.
     * @param Reparto   Reparto di assegnazione del personale sanitario.
     * @param password  Password associata al personale sanitario.
     */
    public PersonaleSanitario(String Nome, String Cognome, LocalDate dataN,
                              LocalDate dataAss, Reparto Reparto, String password) {
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.dataN = dataN;
        this.dataAss = dataAss;
        this.Reparto = Reparto;
        this.password = password;
        this.id = makeID();
    }

    /**
     * Costruttore di default per l'uso di login o autenticazione.
     */
    public PersonaleSanitario() {}

    /**
     * Ritorna il nome del personale sanitario.
     *
     * @return Il nome del personale sanitario.
     */
    public String getNome() {
        return Nome;
    }

    /**
     * Imposta il nome del personale sanitario.
     *
     * @param nome Il nuovo nome da assegnare.
     */
    public void setNome(String nome) {
        Nome = nome;
    }

    /**
     * Ritorna il cognome del personale sanitario.
     *
     * @return Il cognome del personale sanitario.
     */
    public String getCognome() {
        return Cognome;
    }

    /**
     * Imposta il cognome del personale sanitario.
     *
     * @param cognome Il nuovo cognome da assegnare.
     */
    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    /**
     * Ritorna la data di nascita del personale sanitario.
     *
     * @return La data di nascita.
     */
    public LocalDate getDataN() {
        return dataN;
    }

    /**
     * Imposta la data di nascita del personale sanitario.
     *
     * @param dataN La nuova data di nascita.
     */
    public void setDataN(LocalDate dataN) {
        this.dataN = dataN;
    }

    /**
     * Ritorna l'ID del personale sanitario.
     *
     * @return L'ID del personale sanitario.
     */
    public String getId() {
        return id;
    }

    /**
     * Ritorna la data di assunzione del personale sanitario.
     *
     * @return La data di assunzione.
     */
    public LocalDate getDataAss() {
        return dataAss;
    }

    /**
     * Imposta la data di assunzione del personale sanitario.
     *
     * @param dataAss La nuova data di assunzione.
     */
    public void setDataAss(LocalDate dataAss) {
        this.dataAss = dataAss;
    }

    /**
     * Ritorna il reparto di lavoro del personale sanitario.
     *
     * @return Il reparto di lavoro.
     */
    public Reparto getReparto() {
        return Reparto;
    }

    /**
     * Imposta il reparto di lavoro del personale sanitario.
     *
     * @param reparto Il nuovo reparto da assegnare.
     */
    public void setReparto(Reparto reparto) {
        Reparto = reparto;
    }

    /**
     * Ritorna la password del personale sanitario.
     *
     * @return La password del personale sanitario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password del personale sanitario.
     *
     * @param password La nuova password da assegnare.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *  La funzione inserisciRecord serve per inserire un record nella lista dei pazienti seguiti da un medico
     *
     * @param record Il record del paziente da inserire.
     */
    public abstract void inserisciRecord(Record record);

    /**
     * Metodo astratto per cancellare un record paziente.
     *
     * @param record Il record del paziente da  cancellare.
     */
    public abstract void cancellaRecord(Record record);

    /**
     * La funzione cercaRecord ritorna, se presente, l'indice del record ricercato all'interno della lista
     * di pazienti seguiti da un medico, 0 altrimenti
     *
     * @param record Il record del paziente da cercare.
     * @return L'indice del paziente nella lista dei pazienti seguiti, 0 se non presente
     */
    public abstract int cercaRecord(Record record);

    /**
     * Metodo astratto per eseguire una nuova visita su un paziente.
     *
     * @param record Il record del paziente.
     * @param newvis La nuova visita da aggiungere al record.
     */
    public abstract void eseguiVisita(Record record, Visita newvis);

    /**
     * Metodo astratto per visualizzare tutti i record nella lista personale dei pazienti
     * di un membro del personale sanitario.
     *
     * @return Una lista di record paziente.
     */
    public abstract List<Record> visualizzaRecord();

    /**
     * Metodo astratto per salvare i dati del personale sanitario.
     */
    public abstract void salvaP();

    /**
     * Metodo astratto per caricare i dati del personale sanitario in base all'ID.
     *
     * @param id L'ID del personale sanitario.
     */
    public abstract void caricaP(String id);

    /**
     * Restituisce una rappresentazione in stringa del personale sanitario.
     *
     * @return Una stringa che descrive il personale sanitario.
     */
    @Override
    public String toString() {
        return "Nome: " + Nome +
                " Cognome: " + Cognome +
                "\nNato il:" + dataN +
                " Assunto in data: " + dataAss +
                "\nLavora presso: " + Reparto +
                "\nID UTENTE: " + id +
                " Password: " + password + "\n";
    }
}
