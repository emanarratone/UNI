import com.BF.BellmanFord;
import static org.junit.jupiter.api.Assertions.*;

import it.uniupo.graphLib.DirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BellmanFordTest {

    private DirectedGraph g;
    private BellmanFord bf;

    @BeforeEach
    void setUp() {
        // Grafo con 6 nodi e archi con peso
        g = new DirectedGraph("6;0 1 2;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        bf = new BellmanFord(g, 0, g.getOrder());
    }

    @Test
    void testDistanzeMinime() {
        // Verifica le distanze minime dal nodo 0
        assertEquals(0, bf.getDist(0), "Distanza dal nodo 0 a 0 deve essere 0");
        assertEquals(2, bf.getDist(1), "Distanza minima dal nodo 0 a 1");
        assertEquals(3, bf.getDist(2), "Distanza minima dal nodo 0 a 2");
        assertEquals(3, bf.getDist(3), "Distanza minima dal nodo 0 a 3");
        assertEquals(5, bf.getDist(4), "Distanza minima dal nodo 0 a 4");
        assertEquals(5, bf.getDist(5), "Distanza minima dal nodo 0 a 5");
    }

    @Test
    void testNoNegativeCycle() {
        assertFalse(bf.isNegCycle(), "Il grafo non dovrebbe avere cicli negativi");
    }

    @Test
    void testNegativeCycle() {
        // Creo un grafo con ciclo negativo
        DirectedGraph gNeg = new DirectedGraph("3;0 1 1;1 2 -2;2 0 -2");
        BellmanFord bfNeg = new BellmanFord(gNeg, 0, gNeg.getOrder());
        assertTrue(bfNeg.isNegCycle(), "Il grafo dovrebbe avere un ciclo negativo");
        // Le distanze devono restituire Integer.MIN_VALUE
        for (int v = 0; v < gNeg.getOrder(); v++) {
            assertEquals(Integer.MIN_VALUE, bfNeg.getDist(v));
        }
    }
}

