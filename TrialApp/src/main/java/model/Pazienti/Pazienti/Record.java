package model.Pazienti.Pazienti;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import static model.Pazienti.Pazienti.RecordManager.*;

/**
 * La classe Record rappresenta un record medico associato a un paziente.
 * Contiene informazioni come l'identificatore univoco, l'anno di nascita e la data di registrazione.
 */
public class Record implements Serializable {

    /**
     * Identificatore univoco del paziente.
     */
    private final String id;

    /**
     * Anno di nascita del paziente.
     */
    private int AnnoN;

    /**
     * Data di registrazione del record medico.
     */
    private LocalDate DataR;

    /**
     * Costruttore per la creazione di un nuovo record medico.
     *
     * @param id     Identificatore univoco del paziente.
     * @param annoN  Anno di nascita del paziente.
     * @param dataR  Data di registrazione del record medico.
     */
    public Record(String id, int annoN, LocalDate dataR) {
        this.id = id;
        this.AnnoN = annoN;
        this.DataR = dataR;
    }

    /**
     * Costruttore per la creazione di un nuovo record medico senza specificare l'id.
     * L'id viene generato casualmente controllando prima se è gia presente in ids.
     *
     * @param annoN Anno di nascita del paziente.
     * @param dataR Data di registrazione del record medico.
     */
    public Record(int annoN, LocalDate dataR) {
        this.id = makeID();
        this.AnnoN = annoN;
        this.DataR = dataR;
    }

    /**
     * Costruttore per la creazione di un nuovo record medico specificando solo l'id e l'anno di nascita.
     * La data di registrazione viene impostato sulla data corrente.
     *
     * @param id    Identificatore univoco del paziente.
     * @param annoN Anno di nascita del paziente.
     */
    public Record(String id, int annoN) {
        this.id = id;
        ids.add(id);
        this.AnnoN = annoN;
        this.DataR = LocalDate.now();
    }

    /**
     * Costruttore per la creazione di un nuovo record medico senza specificare l'id e la data di registrazione.
     * L'identificatore viene generato casualmente, e la data di registrazione viene impostato sulla data corrente.
     *
     * @param annoN Anno di nascita del paziente.
     */
    public Record(int annoN) {
        this.id = makeID();
        this.AnnoN = annoN;
        this.DataR = LocalDate.now();
    }


    /**
     * Restituisce l'identificatore univoco del paziente.
     *
     * @return Identificatore univoco del paziente.
     */
    public String getId() {
        return id;
    }
    /**
     * Restituisce l'anno di nascita del paziente.
     *
     * @return Anno di nascita del paziente.
     */
    public int getAnnoN() {
        return AnnoN;
    }

    /**
     * Imposta l'anno di nascita del paziente.
     *
     * @param annoN Nuovo anno di nascita.
     */
    public void setAnnoN(int annoN) {
        AnnoN = annoN;
    }

    /**
     * Restituisce la data di registrazione del record medico.
     *
     * @return Data di registrazione del record medico.
     */
    public LocalDate getDataR() {
        return DataR;
    }

    /**
     * Imposta la data di registrazione del record medico.
     *
     * @param dataR Nuova data di registrazione.
     */
    public void setDataR(LocalDate dataR) {
        DataR = dataR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return AnnoN == record.AnnoN && Objects.equals(id, record.id) && Objects.equals(DataR, record.DataR);
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Record.
     *
     * @return Stringa che rappresenta l'oggetto Record.
     */
    @Override
    public String toString() {
        return "ID: " + this.id + " Nato il: " + this.AnnoN + " Registrato in data: " + this.DataR + "\n";
    }
}