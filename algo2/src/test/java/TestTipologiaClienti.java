import com.kruskal.TipologiaClienti;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTipologiaClienti {

    @Test
    public void create(){
        UndirectedGraph g = new UndirectedGraph(1);
        TipologiaClienti tc = new TipologiaClienti(g);
        assertNotNull(tc);
    }

    @Test
    public void testTipologie(){
        UndirectedGraph g = new UndirectedGraph("4; 0 1 -2; 0 2 3; 0 3 8; 1 2 4; 1 3 7; 2 3 9");
        TipologiaClienti tc = new TipologiaClienti(g);
        assertEquals(4, tc.numeroTipologie(-5));
        assertEquals(3, tc.numeroTipologie(1));
        assertEquals(1, tc.numeroTipologie(10));
        assertEquals(2, tc.numeroTipologie(4));
        assertEquals(2, tc.numeroTipologie(7));
        assertEquals(1, tc.numeroTipologie(8));

    }
}
