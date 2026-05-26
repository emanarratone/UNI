import com.FW.FloydWarshall;
import static org.junit.jupiter.api.Assertions.*;

import it.uniupo.graphLib.DirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FloydWarshallTest {

    private DirectedGraph g;
    private FloydWarshall fw;

    @BeforeEach
    void setUp() {
        // Stesso grafo usato per Bellman-Ford
        g = new DirectedGraph("6;0 1 2;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        fw = new FloydWarshall(g, g.getOrder());
    }

    @Test
    void testDistanzeMinime() {
        // Controlliamo le distanze minime calcolate da Floyd-Warshall
        assertEquals(0, fw.getDist(0, 0));
        assertEquals(2, fw.getDist(0, 1));
        assertEquals(3, fw.getDist(0, 2));
        assertEquals(3, fw.getDist(0, 3));
        assertEquals(5, fw.getDist(0, 4));
        assertEquals(5, fw.getDist(0, 5));

        // Verifica di simmetria dove non ci sono archi diretti inversi
        assertEquals(Integer.MAX_VALUE, fw.getDist(5, 0));
        assertEquals(Integer.MAX_VALUE, fw.getDist(4, 2));
    }

    @Test
    void testNoNegativeCycle() {
        assertFalse(fw.isNegCycle(), "Il grafo non dovrebbe avere cicli negativi");
    }

    @Test
    void testNegativeCycle() {
        // Creo un grafo con ciclo negativo
        DirectedGraph gNeg = new DirectedGraph("3;0 1 1;1 2 -2;2 0 -2");
        FloydWarshall fwNeg = new FloydWarshall(gNeg, gNeg.getOrder());

        assertTrue(fwNeg.isNegCycle(), "Il grafo dovrebbe avere un ciclo negativo");

        // Le distanze non devono essere considerate valide se c’è un ciclo negativo
        for (int i = 0; i < gNeg.getOrder(); i++) {
            for (int j = 0; j < gNeg.getOrder(); j++) {
                assertEquals(Integer.MIN_VALUE, fwNeg.getDist(i, j));
            }
        }
    }
}

