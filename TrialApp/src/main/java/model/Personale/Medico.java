package model.Personale;

import model.Pazienti.Pazienti.Record;
import model.Pazienti.Visita;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static model.Pazienti.Pazienti.RecordManager.*;

public class Medico extends PersonaleSanitario implements Serializable {

    @Serial
    private static final long serialVersionUID = -5864834046076592759L;

    /** Specializzazione del medico */
    private String specializzazione;

    /** Orari di lavoro del medico */
    private HashMap<String, String> orari;

    /** Lista dei pazienti seguiti dal medico */
    private ArrayList<Record> pazientiSeguiti;

    /**
     * Costruttore della classe Medico.
     *
     * @param Nome              Nome del medico.
     * @param Cognome           Cognome del medico.
     * @param dataN             Data di nascita del medico.
     * @param dataAss           Data di assunzione del medico.
     * @param Reparto           Reparto di lavoro del medico.
     * @param password          Password del medico.
     * @param specializzazione  Specializzazione del medico.
     */
    public Medico(String Nome, String Cognome, LocalDate dataN,
                  LocalDate dataAss, Reparto Reparto, String password,
                  String specializzazione) {
        super(Nome, Cognome, dataN, dataAss, Reparto, password);
        this.specializzazione = specializzazione;
        this.pazientiSeguiti = new ArrayList<>();
        this.orari = new HashMap<>();
    }

    /**
     * Costruttore di default della classe medico per il login.
     */
    public Medico(){}

    /**
     * Restituisce la specializzazione del medico.
     *
     * @return La specializzazione del medico.
     */
    public String getSpecializzazione() {
        return specializzazione;
    }

    /**
     * Imposta la specializzazione del medico.
     *
     * @param specializzazione La nuova specializzazione del medico.
     */
    public void setSpecializzazioni(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    /**
     * Restituisce gli orari di lavoro del medico.
     *
     * @return Gli orari di lavoro del medico.
     */
    public HashMap<String, String> getOrari() {
        return orari;
    }

    /**
     * Imposta gli orari di lavoro del medico.
     *
     * @param orari Gli orari di lavoro da impostare.
     */
    public void setOrari(HashMap<String, String> orari) {
        this.orari = orari;
    }

    /**
     * Restituisce la lista dei pazienti seguiti dal medico.
     *
     * @return La lista dei pazienti seguiti.
     */
    public ArrayList<Record> getPazientiSeguiti() {
        return pazientiSeguiti;
    }

    /**
     * Imposta la lista dei pazienti seguiti dal medico.
     *
     * @param pazientiSeguiti La lista dei pazienti seguiti da impostare.
     */
    public void setPazientiSeguiti(ArrayList<Record> pazientiSeguiti) {
        this.pazientiSeguiti = pazientiSeguiti;
    }

    @Override
    public void inserisciRecord(Record record) {
        this.pazientiSeguiti.add(record);
    }

    @Override
    public void cancellaRecord(Record record) {
        this.pazientiSeguiti.remove(record);
    }

    @Override
    public int cercaRecord(Record record) {
        return (pazientiSeguiti.contains(record)) ? this.pazientiSeguiti.indexOf(record) : -1;
    }

    @Override
    public void salvaP() {
        File path = new File("./archive/Med/"+ this.getId() +".dat");

        File arcDir = new File("./archive");
        File recordDir = new File("./archive/Med");
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
                System.err.println("Errore durante il salvataggio. "+e.getMessage());
            }
        }
    }

    @Override
    public void caricaP(String id) {
        File path = new File("./archive/Med/"+ id +".dat");
        if (path.exists()) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(path))) {

                Medico med = (Medico) is.readObject();
                this.Nome = med.Nome;
                this.Cognome = med.Cognome;
                this.dataN = med.dataN;
                this.id = med.id;
                this.dataAss = med.dataAss;
                this.Reparto = med.Reparto;
                this.password = med.password;
                this.pazientiSeguiti = med.pazientiSeguiti;
                this.orari = med.orari;
                this.specializzazione = med.specializzazione;

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Errore durante il caricamento. \n" + e);
            }
        }
    }

    @Override
    public void eseguiVisita(Record record, Visita newvis) {
        addVisit(record, newvis);
        newvis.setTerapia(fornisciTerapie(newvis));
        salva(record);
    }

    @Override
    public List<Record> visualizzaRecord(){
        return this.pazientiSeguiti;
    }

    /**
     * Metodo per suggerire esami in base ai parametri, sono consigli generici e terapie.
     *
     * @param vis la visita presa in questione
     * @return La lista delle terapie (da 1 a N terapie)
     */
    public ArrayList<String> fornisciTerapie(Visita vis) {

        HashMap<Visita.Parametro, Object> stats = vis.getValori();
        ArrayList<String> Terapie = new ArrayList<>();

        for(Visita.Parametro par: stats.keySet()){

            if(par == Visita.Parametro.TEMPERATURA && (Double) stats.get(par)>= 37.5){

                Terapie.add("Il Dottor "+ super.getNome() + super.getCognome()+ " consiglia una terapia a base di paracetamolo");

            }

            if(par == Visita.Parametro.PRESSIONE){

                Visita.Parametro.Livelli lv = (Visita.Parametro.Livelli) stats.get(par);

                if(lv.equals(Visita.Parametro.Livelli.ALTA)){
                    Terapie.add("Il Dottor "+ super.getNome() + super.getCognome()+ " consiglia una terapia a base di farmaci antipertensivi.");
                }

            }
            if(par == Visita.Parametro.PESO){
                double altezza = (Double) vis.getValori(Visita.Parametro.ALTEZZA);
                double IMC = (double) stats.get(par) / (altezza * altezza);
                    if(18.5 <= IMC && IMC <= 24.9){
                        Terapie.add("Il paziente è normopeso, il Dottor "+ super.getNome() + super.getCognome()+
                                " consiglia uno stile di vita equilibrato con una dieta sana e attività fisica regolare.");
                    }
                    else if(IMC < 18.5){
                        Terapie.add("Il paziente è sottopeso, il Dottor "+ super.getNome() + super.getCognome()+
                                " consiglia una dieta ipercalorica e ricca di nutrienti per raggiungere un peso salutare.");
                    }

                    else if(25.0 <= IMC && IMC <= 29.9){
                        Terapie.add("Il paziente è sovrappeso, il Dottor "+ super.getNome() + super.getCognome()+
                                " consiglia una dieta ipocalorica bilanciata e attività fisica regolare per perdere peso in modo graduale.");
                    }

                    else if(IMC >= 30.0){
                        Terapie.add("Il paziente è obeso, il Dottor "+ super.getNome() + super.getCognome()+
                                " consiglia una terapia a base di farmaci per il controllo del peso o un consulto " +
                                "con uno specialista per eventuali interventi e che indirizzerà il paziente a una dieta specifica ed" +
                                "eventualmente attività fisica.");
                    }
            }
            if(par == Visita.Parametro.CAPACITA_POLMONARE){

                Visita.Parametro.Livelli lv = (Visita.Parametro.Livelli) stats.get(par);

                if(lv.equals(Visita.Parametro.Livelli.BASSA)){
                    Terapie.add("Il Dottor "+ super.getNome() + super.getCognome()+ " consiglia una terapia con farmaci broncodilatatori o un programma di riabilitazione polmonare.");
                }
            }
        }
        return Terapie;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Specializzato in: " + specializzazione;
    }
}

