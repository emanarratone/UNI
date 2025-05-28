package UPO20050533.trials;

import model.Pazienti.Pazienti.Record;
import model.Pazienti.Pazienti.RecordManager;
import model.Pazienti.Visita;
import model.Personale.Medico;
import model.Personale.PersonaleSanitario;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static UPO20050533.trials.VisitaTest.val;
import static org.junit.jupiter.api.Assertions.*;

public class MedicoTest {

    Medico medico = new Medico("Mario", "Rossi", LocalDate.of(1980, 1, 1),
            LocalDate.of(2020, 1, 1), PersonaleSanitario.Reparto.Cardiologia,
            "password", "Cardiologo");

    Record record = new Record("1234", 2003, LocalDate.now());

    Visita visita = new Visita(val, LocalDate.now());

    @Test
    public void testInserisciRecord() {
        medico.inserisciRecord(record);
        assertEquals(1, medico.getPazientiSeguiti().size());
        assertTrue(medico.getPazientiSeguiti().contains(record));
    }

    @Test
    public void testCancellaRecord() {
        medico.inserisciRecord(record);
        medico.cancellaRecord(record);
        assertEquals(0, medico.getPazientiSeguiti().size());
        assertFalse(medico.getPazientiSeguiti().contains(record));
    }

    @Test
    public void testCercaRecord() {
        medico.inserisciRecord(record);
        int index = medico.cercaRecord(record);
        assertEquals(0, index); // record è il primo elemento
    }

    @Test
    public void testCercaRecordNotFound() {
        int index = medico.cercaRecord(record);
        assertEquals(-1, index); // record non è stato inserito
    }

    @Test
    public void testEseguiVisita() {
        medico.eseguiVisita(record, visita);
        ArrayList<String> terapie = medico.fornisciTerapie(visita);
        assertEquals(visita, RecordManager.getVisita(record,  1));
        //la visita in questione ha la pressione alta, verrà registrata la terapia per la pressione alta
        // + la terapia per la massa corporea (se fuori norma)
        assertFalse(terapie.isEmpty());
    }

    @Test
    public void testSalvaPCreatesFile() {
        medico.salvaP();
        File f = new File("TrialAppFX/archive/Med/"+ medico.getId()+".dat");
        assertNotNull(f);
    }


    @Test
    public void testCaricaP() {
        Medico medicoN = new Medico("Mario", "Rossi", LocalDate.of(1980, 1, 1),
                LocalDate.of(2020, 1, 1), PersonaleSanitario.Reparto.Cardiologia,
                "password", "Cardiologo");      //per lavorare con lo stesso oggetto
        medicoN.salvaP();
        Medico medico1 = new Medico();
        medico1.caricaP(medicoN.getId());
        System.out.println(medicoN.getId());
        assertEquals(medicoN.toString(), medico1.toString());
        //caricando da file non sono lo stesso oggetto quindi non passerebbe assertEquals
    }
}
