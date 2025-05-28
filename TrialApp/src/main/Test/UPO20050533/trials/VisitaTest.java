package UPO20050533.trials;

import model.Pazienti.Visita;
import model.Pazienti.Visita.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VisitaTest {
    static HashMap<Parametro,Object> val = new HashMap<>();
    static {
        val.put(Parametro.TEMPERATURA, 37.0);
        val.put(Parametro.PRESSIONE, Parametro.Livelli.ALTA);
        val.put(Parametro.CAPACITA_POLMONARE, Parametro.Livelli.ALTA);
        val.put(Parametro.PESO, 70.0);
        val.put(Parametro.ALTEZZA, 1.80);
    }
    static ArrayList<Visita> Vis = new ArrayList<>();
    static{
        Visita vis1 = new Visita(val, LocalDate.now());
        Vis.add(vis1);
        Visita vis2 = new Visita(val, LocalDate.now());
        Vis.add(vis2);
        Visita vis3 = new Visita(val, LocalDate.now());
        Vis.add(vis3);
    }
    @Test
    void testVisitaVuota(){
        Visita visita = new Visita(val ,LocalDate.now());
        assertNotNull(visita);
    }
    @Test
    void getDataV() {
        Visita visita = new Visita(val, LocalDate.now());
        assertEquals(LocalDate.now(), visita.getDataV());
    }

    @Test
    void setDataV() {
        LocalDate newDate = LocalDate.of(2023, 1, 1);
        Visita visita = new Visita(val, LocalDate.now());
        visita.setDataV(newDate);
        assertEquals(newDate, visita.getDataV()) ;
    }

    @Test
    void testEquals() {
        Visita visita = new Visita(val, LocalDate.now());
        Visita visita1 = new Visita(val, LocalDate.now());
        assertTrue(visita.equals(visita1));
    }

    @Test
    void setParametro(){
        Visita visita = new Visita(val, LocalDate.now());
        Visita visita1 = new Visita(val, LocalDate.now());
        visita1.setParametro(Parametro.TEMPERATURA, 38.2);
        assertNotEquals(visita, visita1);
    }
}
