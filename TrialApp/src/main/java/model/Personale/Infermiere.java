package model.Personale;

import model.Pazienti.Pazienti.Record;
import model.Pazienti.Visita;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static model.Pazienti.Pazienti.RecordManager.*;

public class Infermiere extends PersonaleSanitario implements Serializable{

    @Serial
    private static final long serialVersionUID = -3222080228624877454L;

    /** Turni dell'infermiere */
    private HashMap<String, String> turni;

    /** Lista di pazienti assegnati all'infermiere */
    private ArrayList<Record> pazientiAssegnati;

    /**
     * Costruttore della classe Infermiere che permette di creare un nuovo Infermiere con i parametri specificati.
     *
     * @param Nome        Nome dell'infermiere
     * @param Cognome     Cognome dell'infermiere
     * @param dataN       Data di nascita dell'infermiere
     * @param dataAss     Data di assunzione dell'infermiere
     * @param Reparto     Reparto di appartenenza
     * @param password    Password dell'infermiere
     */
    public Infermiere(String Nome, String Cognome, LocalDate dataN,
                      LocalDate dataAss, Reparto Reparto, String password) {

        super(Nome, Cognome, dataN, dataAss, Reparto, password);
        this.pazientiAssegnati = new ArrayList<>();
        this.turni = new HashMap<>();

    }

    /**
     * Costruttore vuoto della classe Infermiere per login.
     */
    public Infermiere() {}

    /**
     * Restituisce la mappa dei turni dell'infermiere.
     *
     * @return Una mappa che rappresenta i turni dell'infermiere
     */
    public HashMap<String, String> getTurni() {
        return turni;
    }

    /**
     * Imposta la mappa dei turni dell'infermiere.
     *
     * @param turni La mappa dei turni da assegnare all'infermiere
     */
    public void setTurni(HashMap<String, String> turni) {
        this.turni = turni;
    }

    /**
     * Restituisce la lista dei pazienti assegnati all'infermiere.
     *
     * @return Una lista dei record dei pazienti assegnati
     */
    public ArrayList<Record> getPazientiAssegnati() {
        return pazientiAssegnati;
    }

    /**
     * Imposta la lista dei pazienti assegnati all'infermiere.
     *
     * @param pazientiAssegnati La lista dei record dei pazienti da assegnare
     */
    public void setPazientiAssegnati(ArrayList<Record> pazientiAssegnati) {
        this.pazientiAssegnati = pazientiAssegnati;
    }


    @Override
    public void inserisciRecord(Record record) {
        this.pazientiAssegnati.add(record);
    }


    @Override
    public void cancellaRecord(Record record) {
        this.pazientiAssegnati.remove(record);
    }


    @Override
    public int cercaRecord(Record record) {
        return (pazientiAssegnati.contains(record)) ? this.pazientiAssegnati.indexOf(record) : -1;
    }

    @Override
    public List<Record> visualizzaRecord(){
        return this.pazientiAssegnati;
    }

    @Override
    public void salvaP() {
        File path = new File("./archive/Inf/"+ this.getId() +".dat");

        File arcDir = new File("./archive");
        File recordDir = new File("./archive/Inf");
        if (!arcDir.exists()) {
            boolean ad = arcDir.mkdir();
        }
        if (!recordDir.exists()) {
            boolean rd = recordDir.mkdir();
        }
        if(!path.exists())creaFile(path);

        if(path.canWrite()) {
            try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))) {
                os.writeObject(this);
            }
            catch (IOException e) {
                System.err.println("Errore durante il salvataggio."+e.getMessage());
            }
        }
    }

    @Override
    public void caricaP(String id) {
        File path = new File("./archive/Inf/"+ id +".dat");
        if (path.exists()) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(path))) {

                Infermiere inf = (Infermiere) is.readObject();
                this.Nome = inf.Nome;
                this.Cognome = inf.Cognome;
                this.dataN = inf.dataN;
                this.id = inf.id;
                this.dataAss = inf.dataAss;
                this.Reparto = inf.Reparto;
                this.password = inf.password;
                this.pazientiAssegnati = inf.pazientiAssegnati;
                this.turni = inf.turni;

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Errore durante il caricamento. \n" + e);
            }
        }
    }

    @Override
    public void eseguiVisita(Record record, Visita newvis) {
        addVisit(record, newvis);
        newvis.setTerapia(somministraTerapie(newvis));
        salva(record);
    }

    /**
     * Metodo per somministrare terapie (basato sul Metodo fornisciTerapie di Medico)
     * Visiona i parametri della visita e agisce di conseguenza, concettualmente "cura" il paziente
     * andando a modificare i parametri della visita
     * @param vis la visita presa in questione
     */
    public ArrayList<String> somministraTerapie(Visita vis) {

        HashMap<Visita.Parametro, Object> stats = vis.getValori();
        ArrayList<String> Terapie = new ArrayList<>();

        for(Visita.Parametro par: stats.keySet()){

            if(par == Visita.Parametro.TEMPERATURA && (Double) stats.get(par)>= 37.5){
                Terapie.add("L'infermiere "+ super.getNome() + super.getCognome()+ " somministra la terapia a base di paracetamolo");
            }

            if(par == Visita.Parametro.PRESSIONE){

                Visita.Parametro.Livelli lv = (Visita.Parametro.Livelli) stats.get(par);

                if(lv.equals(Visita.Parametro.Livelli.ALTA)){
                    Terapie.add("L'infermiere "+ super.getNome() + super.getCognome()+ " somministra la terapia a base di farmaci antipertensivi.");
                }

            }
            if(par == Visita.Parametro.CAPACITA_POLMONARE){

                Visita.Parametro.Livelli lv = (Visita.Parametro.Livelli) stats.get(par);

                if(lv.equals(Visita.Parametro.Livelli.BASSA)){
                    Terapie.add("L'infermiere "+ super.getNome() + super.getCognome()+ " somministra la terapia con farmaci broncodilatatori o un programma di riabilitazione polmonare.");
                }
            }
        }
        return Terapie;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
