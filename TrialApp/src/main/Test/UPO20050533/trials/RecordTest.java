package UPO20050533.trials;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Pazienti.Visita.*;
import model.Pazienti.Pazienti.Record;
import model.Pazienti.Visita;

import static model.Pazienti.Pazienti.RecordManager.*;
import static org.junit.jupiter.api.Assertions.*;

class RecordTest {
        HashMap<Parametro,Object> val = new HashMap<>();
        {
            val.put(Parametro.TEMPERATURA, 37.0);
            val.put(Parametro.PRESSIONE, "MEDIA");
            val.put(Parametro.CAPACITA_POLMONARE, "ALTA");
            val.put(Parametro.PESO, 70);
        }

        @Test
        void testRecordVuoto(){
            Record persona = new Record("1234",2003, LocalDate.now());
            assertNotNull(persona);
        }
        @Test
        void testAddVisit() {
            Record persona = new Record("1234",2003, LocalDate.now());
            Visita visita1 = new Visita(val ,LocalDate.now());
            addVisit(persona, visita1);
            ArrayList<Visita> lisvis = visite.get(persona);
            assertNotNull(lisvis);
            Visita vis = lisvis.get(0);
            assertEquals(visita1, vis);

        }

        @Test
        void testGetVisita() {
            Record persona = new Record("1234",2003, LocalDate.now());
            Visita visita2 = new Visita(val ,LocalDate.now());
            addVisit(persona, visita2);
            Visita v = getVisita(persona, 1);       //1a visita
            assertEquals(v, visita2);
        }

        @Test
        void testContainsVisita(){
            Record persona = new Record("1234",2003, LocalDate.now());
            Visita visita3 = new Visita(val,LocalDate.now());
            addVisit(persona, visita3);
            assertTrue(containsVisita(visita3));
            Visita nonpresente = new Visita();
            assertFalse(containsVisita(nonpresente));
        }

        @Test
        void testDeleteVisita() {
            Record persona = new Record("1234",2003, LocalDate.now());
            Visita visita4 = new Visita();
            addVisit(persona, visita4);
            deleteVisita(persona);
            assertFalse(containsVisita(visita4));
        }

        @Test
        void getId() {
            Record persona = new Record("1234",2003, LocalDate.now());
            assertEquals("1234", persona.getId());
        }

        @Test
        void getAnnoN() {
            Record persona = new Record("1234",2003, LocalDate.now());
            assertEquals(2003, persona.getAnnoN());
        }

        @Test
        void setAnnoN() {
            Record persona = new Record("1234",2003, LocalDate.now());
            persona.setAnnoN(2004);
            assertEquals(2004, persona.getAnnoN());
        }

        @Test
        void getDataR() {
            Record persona = new Record("1234",2003, LocalDate.now());
            assertEquals(LocalDate.now(), persona.getDataR());
        }

        @Test
        void setDataR() {
            LocalDate newDate = LocalDate.of(2023, 1, 1);
            Record persona = new Record("1234",2003, LocalDate.now());
            persona.setDataR(newDate);
            assertEquals(newDate, persona.getDataR());
        }

        @Test
        void test_byValues() {
            Record persona = new Record("1234",2003, LocalDate.now());
            Visita visita1 = new Visita(val,LocalDate.now());
            addVisit(persona, visita1);
            Visita visita2 = new Visita(val,LocalDate.now());
            visita2.setParametro(Parametro.TEMPERATURA, 38.0);
            addVisit(persona, visita2);
            Visita visita3 = new Visita(val,LocalDate.now());
            visita3.setParametro(Parametro.TEMPERATURA, 40.0);
            addVisit(persona, visita3);
            List<Visita> sortd = byValues(persona, Parametro.TEMPERATURA, false);
            System.out.println(sortd);
            assertTrue((Double)sortd.get(0).getValori(Parametro.TEMPERATURA) <= (Double) sortd.get(1).getValori(Parametro.TEMPERATURA));
            sortd = byValues(persona, Parametro.TEMPERATURA, true);
            System.out.println(sortd);
            assertTrue((Double)sortd.get(0).getValori(Parametro.TEMPERATURA) >= (Double) sortd.get(1).getValori(Parametro.TEMPERATURA));
        }

        @Test
        void test_byData() {
            Record persona = new Record("1234",2003, LocalDate.now());
            Visita visita1 = new Visita(val,LocalDate.now());
            addVisit(persona, visita1);
            Visita visita2 = new Visita(val,LocalDate.now());
            visita2.setDataV(LocalDate.of(2016, 1, 1));
            addVisit(persona, visita2);
            List<Visita> sortd = byData(persona, true);
            Assertions.assertTrue(sortd.get(0).getDataV().isAfter(sortd.get(1).getDataV()));
        }

        @Test
        void test_salva() {
            Visita visita = new Visita(val,LocalDate.now());
            Record persona = new Record("1234",2003, LocalDate.now());
            addVisit(persona, visita);
            salva(persona);
            File f = new File("TrialAppFX/archive/user/"+ persona.getId()+".dat");
            assertNotNull(f);
        }

        @Test
        void test_carica(){
            Visita visita = new Visita(val,LocalDate.now());
            Record persona = new Record("1234",2003, LocalDate.now());
            addVisit(persona, visita);
            salva(persona);
            File f = new File("TrialAppFX/archive/user/"+ persona.getId()+".dat");
            assertNotNull(f);
            carica(persona.getId());
            assertTrue(visite.containsKey(persona));
        }
}

