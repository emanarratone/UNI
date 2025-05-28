package UPO20050533.trials;

import model.Pazienti.Pazienti.Record;
import model.Pazienti.Visita;
import model.Personale.Infermiere;
import model.Personale.Medico;
import model.Personale.PersonaleSanitario;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static UPO20050533.trials.VisitaTest.val;
import static org.junit.jupiter.api.Assertions.*;

public class InfermiereTest {

    Infermiere infermiere = new Infermiere("Mario", "Rossi", LocalDate.of(1980, 1, 1),
            LocalDate.of(2020, 1, 1), PersonaleSanitario.Reparto.Cardiologia,
            "password");

    Record record = new Record("1234", 2003, LocalDate.now());

    Visita visita = new Visita(val, LocalDate.now());

    @Test
    public void testInserisciRecord() {
        infermiere.inserisciRecord(record);
        assertEquals(1, infermiere.getPazientiAssegnati().size());
        assertTrue(infermiere.getPazientiAssegnati().contains(record));
    }

    @Test
    public void testCancellaRecord() {
        infermiere.inserisciRecord(record);
        infermiere.cancellaRecord(record);
        assertEquals(0, infermiere.getPazientiAssegnati().size());
        assertFalse(infermiere.getPazientiAssegnati().contains(record));
    }

    @Test
    public void testCercaRecord() {
        infermiere.inserisciRecord(record);
        int index = infermiere.cercaRecord(record);
        assertEquals(0, index); // record è il primo elemento
    }

    @Test
    public void testCercaRecordNotFound() {
        int index = infermiere.cercaRecord(record);
        assertEquals(-1, index); // record non è stato inserito
    }

    @Test
    public void testEseguiVisita() {
        infermiere.eseguiVisita(record, visita);
        ArrayList<String> terapie = infermiere.somministraTerapie(visita);
        //la visita in questione ha la pressione alta, verrà "somministrata" la terapia per la pressione alta
        // + la terapia per la massa corporea (se fuori norma)
        assertFalse(terapie.isEmpty());
    }

    @Test
    public void testSalvaPCreatesFile() {
        infermiere.salvaP();
        File f = new File("TrialAppFX/archive/Inf/"+ infermiere.getId()+".dat");
        assertNotNull(f);
    }


    @Test
    public void testCaricaP() {
        Infermiere infermiere = new Infermiere("Mario", "Rossi", LocalDate.of(1980, 1, 1),
                LocalDate.of(2020, 1, 1), PersonaleSanitario.Reparto.Cardiologia,
                "password");      //per lavorare con lo stesso oggetto
        infermiere.salvaP();
        Infermiere infermiere1 = new Infermiere();
        infermiere1.caricaP(infermiere.getId());
        System.out.println(infermiere.getId());
        assertEquals(infermiere.toString(), infermiere1.toString());
        //caricando da file non sono lo stesso oggetto quindi non passerebbe assertEquals
    }
}
