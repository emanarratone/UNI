package model.Pazienti;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


/**
 * La classe Visita rappresenta una visita medica.
 */
public class Visita implements Serializable {
    static Scanner tastiera = new Scanner(System.in);

    /** Data della visita. */
    private LocalDate dataV;

    /** Mappa contenente i valori delle visite*/
    private HashMap<Parametro, Object> valori;

    /** Lista contenente la terapia associata alla visita */
    private ArrayList<String> Terapia;

    /**
     * Costruttore per la classe Visita con data specifica.
     *
     * @param valori    Mappa contenente i parametri della visita.
     * @param data   La data della visita.
     */
    public Visita(HashMap<Parametro, Object> valori, LocalDate data) {
        this.valori = valori;
        this.dataV = data;
        this.Terapia = new ArrayList<>();
    }

    /**
     * Costruttore di default per la classe Visita che inizializza con la data di oggi.
     */
    public Visita(){
        this.dataV = LocalDate.now();
        this.valori = new HashMap<>();
        this.Terapia = new ArrayList<>();
    }

    /**
     * Costruttore per la classe Visita con data di oggi.
     *
     * @param valori    Mappa contenente i parametri della visita.
     */
    public Visita(HashMap<Parametro, Object> valori) {
        this.valori = valori;
        this.dataV = LocalDate.now();
        this.Terapia = new ArrayList<>();
    }

    /**
     * Getter per i valori di visita
     *
     * @return Un tipo di valore di una singola visita
     */
    public Object getValori(Parametro p) {
        return this.valori.get(p);
    }

    /**
     * Ritorna tutti i valori dei parametri della visita.
     *
     * @return Una mappa contenente i valori dei parametri della visita.
     */
    public HashMap<Parametro, Object> getValori(){return this.valori;}

    /**
     * Imposta il valore di un parametro della visita, con verifica del tipo del valore.
     *
     * @param parametro Il parametro della visita.
     * @param valore    Il valore da assegnare al parametro.
     * @throws IllegalArgumentException Se il valore non corrisponde al tipo richiesto dal parametro.
     */
    public void setValori(Parametro parametro, Object valore) {
        // Effettua controlli o cast basati sul type di Parametro
        if (parametro.getType().equals("Numeric")) {
            if (!(valore instanceof Number)) {
                throw new IllegalArgumentException("Il valore deve essere del tipo specificato!");
            }
        } else if (parametro.getType().equals("Scalar")) {
            if (!(valore instanceof Parametro.Livelli)) {
                throw new IllegalArgumentException("Il valore deve essere del tipo specificato!");
            }
        }
        if(parametro == Parametro.TEMPERATURA) {
            assert valore instanceof Double;
            if ((Double) valore < 36.0 || (Double) valore > 42.0) {
                throw new IllegalArgumentException("Il valore deve essere del tipo specificato!");
            }
        }

        valori.put(parametro, valore);
    }

    /**
     * Restituisce la data della visita.
     *
     * @return La data della visita.
     */
    public LocalDate getDataV() {
        return this.dataV;
    }

    /**
     * Imposta la data della visita.
     *
     * @param dataV La nuova data della visita.
     */
    public void setDataV(LocalDate dataV) {
        this.dataV = dataV;
    }

    /**
     * Ritorna la lista di terapie della visita.
     *
     * @return La lista di terapie della visita.
     */
    public ArrayList<String> getTerapia() {
        return Terapia;
    }

    /**
     * Imposta la lista di terapie della visita.
     *
     * @param terapia La lista di terapie da assegnare alla visita.
     */
    public void setTerapia(ArrayList<String> terapia) {
        Terapia = terapia;
    }

    /**
     * Imposta un nuovo valore per un parametro nella mappa dei valori.
     *
     * @param p   Il parametro della visita.
     * @param val Il valore da assegnare al parametro.
     */
    public void setParametro(Parametro p, Object val){
        HashMap<Parametro, Object> newValues = new HashMap<>(valori);
        newValues.put(p, val);
        valori = newValues;
    }

    /** Classe annidata per l'enumerazione dei parametri. */
    public enum Parametro {
        TEMPERATURA("Numeric"),
        PRESSIONE("Scalar"),
        CAPACITA_POLMONARE("Scalar"),
        PESO("Numeric"),
        ALTEZZA("Numeric");
        private final String type;

        /**
         * Enumerazione dei valori scalari nei parametri.
         */

        public enum Livelli {
            BASSA,
            MEDIA,
            ALTA
        }

        /**
         * Costruttore per l'enumerazione Parametro.
         *
         * @param type Il tipo di parametro (ad esempio "Numeric" o "Scalar").
         */
        Parametro(String type) {this.type = type;}

        /**
         * Restituisce il tipo del parametro.
         *
         * @return Il tipo del parametro come stringa.
         */
        public String getType() {
            return type;
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visita vis = (Visita) o;
        return Objects.equals(valori, vis.valori) && Objects.equals(dataV, vis.dataV);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Visita del:" + this.dataV + ":" + this.valori +"\n");
        for (String terapia : Terapia) {
            s.append(terapia).append(" ");
        }
        return s.toString();
    }

}