package model.Pazienti.Pazienti;

import model.Pazienti.Visita;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RecordManager {

    /**
     * Elenco degli id (utile per l'univocità degli id).
     */
    public static ArrayList<String> ids = new ArrayList<>();

    /**
     * HashMap che associa ogni record medico alle visite mediche associate, ogni Record (key)
     * ha il proprio Arraylist di visite (value), in questo modo ogni paziente ha a disposizione una lista
     * di capacità dinamica (incrementa da sola la sua grandezza) mantenendo la complessità in tempo
     * nell'utilizzo dell'hashmap costante O(1).
     */
    public static HashMap<Record, ArrayList<Visita>> visite = new HashMap<>();

    /**
     * Per aggiunta record da javaFX
     */
    public static ArrayList<Record> listaUtenti = new ArrayList<>();

    /**
       Genera Un id univoco
     */
    public static String makeID(){
        SecureRandom random = new SecureRandom();
        String id;
        do {
            id = random.nextInt(1000, 9999) + "";
        } while (ids.contains(id));
        ids.add(id);
        return id;
    }


    /**
     * Il metodo salva consente di salvare un record su directory e file specificata
     */
    public static void salva(Record persona){

        File path = new File("./archive/user/"+ persona.getId() +".dat");
        File arcDir = new File("./archive");
        File recordDir = new File("./archive/user");
        if (!arcDir.exists()) {
            boolean ad = arcDir.mkdir();
        }
        if (!recordDir.exists()) {
            boolean rd = recordDir.mkdir();
        }
        if(!path.exists())creaFile(path);

        if(path.canWrite()) Scrittura(path, persona);
    }

    /**
     * Procedura interna del metodo salva che si occupa della creazione del file se insesistente (nuovo record)
     * @param path Il percorso del file da creare
     */
    public static void creaFile(File path) {
            try {
                boolean p = path.createNewFile();
            } catch (IOException e) {
                System.err.println("Errore durante la creazione del file: " + e.getMessage());
            }
    }

    /**
     * Procedura del metodo salva che si occupa della scrittura su file
     * @param path il percorso su cui scrivere
     * @param persona il record da salvare
     */
    public static void Scrittura(File path, Record persona) {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))){
            ArrayList<Visita> listaV = visite.get(persona);

                os.writeObject(persona);

                if(listaV != null){
                    for(Visita V: listaV){
                        os.writeObject(V);
                        os.flush();
                    }
                }
        }
        catch (IOException e) {
            System.err.println("Errore durante il salvataggio. "+e.getMessage());
        }
    }

    /**
     * Metodo per il caricamento delle informazioni di oggetti utente da file
     * @param id identificazione dell'utente
     */
    public static void carica(String id) {
        File path = new File("./archive/user/"+ id +".dat");
        if (path.exists()) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(path))) {

                Record record = (Record) is.readObject();

                if (!visite.containsKey(record)) {
                    visite.put(record, new ArrayList<>());
                }

                while (is.available()>0) {
                    Visita visita = (Visita) is.readObject();
                    visite.get(record).add(visita);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Errore durante il caricamento. \n" + e);
            }
        }
    }


    /**
     * Metodo per la ricerca tramite file di pazienti
     * @param id identificazione dell'utente da cercare
     * @return l'utente cercato, null altrimenti
     */
    public static Record getRecord(String id) {
        File path = new File("./archive/user/" + id + ".dat");
        if(path.exists()){
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(path))) {
                return (Record) is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Errore durante il caricamento. \n" + e);
                return null;
            }
        }
        else{
            return null;
        }
    }


    /**
     * Metodo che riordina le visite di un paziente in ordine di valore di un parametro particolare
     * @param ord crescente o decrescente
     * @param rec il paziente in questione
     * @param par parametro preso in esame
     * @return una lista di visite riordinate secondo i criteri
     */
    public static List<Visita> byValues(Record rec, Visita.Parametro par, boolean ord){
        carica(rec.getId());
        ArrayList<Visita> vis = visite.get(rec);
        if(vis == null) return null;
        vis.sort((v1, v2) -> {
            Object val1 = v1.getValori(par);
            Object val2 = v2.getValori(par);
            if (par.getType().equals("Numeric")) {
                return Double.compare((Double) val1, (Double) val2);
            } else {
                Visita.Parametro.Livelli scal1 = (Visita.Parametro.Livelli) val1;
                Visita.Parametro.Livelli scal2 = (Visita.Parametro.Livelli) val2;
                return Integer.compare(scal1.ordinal(), scal2.ordinal());
            }
        });
        if (ord){
            Collections.reverse(vis);
        }
        return vis;
    }


    /**
     * Metodo che riordina le visite di un paziente in ordine cronologico
     * @param ord crescente o decrescente
     * @param rec il paziente in questione
     * @return una lista di visite riordinate secondo i criteri
     */
    public static List<Visita> byData(Record rec, boolean ord){
        carica(rec.getId());
        ArrayList<Visita> vis = visite.get(rec);
        if(vis == null) return null;
        vis.sort((v1, v2) -> {
            LocalDate val1 = v1.getDataV();
            LocalDate val2 = v2.getDataV();
            return val1.compareTo(val2);
        });
        if (ord){
            Collections.reverse(vis);
            return vis;
        }
        return vis;
    }

    /**
     * Restituisce una visita medica associata al paziente dato l'indice (es. la 4a visita).
     *
     * @param persona Il record del paziente.
     * @param index   Indice della visita nella lista delle visite associate al paziente.
     * @return Visita medica associata al paziente.
     */
    public static Visita getVisita(Record persona, int index) {
        ArrayList<Visita> visita = visite.get(persona);
        return visita.get(index-1);         //parte da 0
    }

    /**
     * Aggiunge una nuova visita medica al record del paziente.
     *
     * @param persona Il record del paziente.
     * @param newvis  La nuova visita medica da aggiungere.
     */
    public static void addVisit(Record persona, Visita newvis) {
        ArrayList<Visita> vis = new ArrayList<>();
        if (visite.containsKey(persona)) {
            vis = visite.get(persona);
            vis.add(newvis);
            visite.put(persona, vis);
        } else {
            vis.add(newvis);
            visite.put(persona, vis);
        }
    }

    /**
     * Verifica se una visita medica è presente nei record dei pazienti.
     *
     * @param visita La visita medica da cercare.
     * @return true se la visita è presente, false altrimenti.
     */
    public static boolean containsVisita(Visita visita) {
        for (Record persona: visite.keySet()){
            for(Visita V: visite.get(persona)){
                if(V.equals(visita)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Rimuove tutte le visite associate a un record del paziente.
     *
     * @param persona Il record del paziente.
     */
    public static void deleteVisita(Record persona) {
        ArrayList<Visita> vis = visite.get(persona);
        vis.clear();
        visite.put(persona, vis);
    }

}
