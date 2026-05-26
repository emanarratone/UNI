import com.prim.Prim;

import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimTest {

    @Test
    public void testCreate() {
        UndirectedGraph g = new UndirectedGraph(1);
        Prim prim = new Prim(g);
        assertNotNull(prim);
    }

    @Test
    public void testOneNode() {
        UndirectedGraph g = new UndirectedGraph(1);
        Prim prim = new Prim(g);
        assertEquals(0, prim.getCost(), "Il costo dell’MST in un grafo con un solo nodo deve essere 0");
    }

    @Test
    public void testSimpleGraph() {
        // Grafo: 3 nodi, archi 0-1 (1), 1-2 (2), 0-2 (3)
        UndirectedGraph g = new UndirectedGraph("3;0 1 1;1 2 2;0 2 3");
        Prim prim = new Prim(g);
        assertEquals(3, prim.getCost(), "Il costo minimo dell’MST deve essere 3 (archi 0-1 e 1-2)");
    }

    @Test
    public void testComplexGraph() {
        // Grafo con 6 nodi come nel test di Dijkstra
        UndirectedGraph g = new UndirectedGraph("6;0 1 2;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        Prim prim = new Prim(g);

        // MST atteso: (1-3, 0-1, 2-5, 1-4, 0-2) con costo 1+2+2+3+3 = 11
        // In realtà l’algoritmo può scegliere un insieme diverso ma con stesso costo minimo.
        assertEquals(11, prim.getCost(), "Il costo dell’MST per questo grafo deve essere 11");
    }

    @Test
    public void testDisconnectedGraph() {
        // Grafo disconnesso: due componenti separate
        UndirectedGraph g = new UndirectedGraph("4;0 1 5;2 3 7");
        Prim prim = new Prim(g);

        // L’algoritmo esplora solo la componente connessa al nodo 0 → costo = 5
        assertEquals(5, prim.getCost(), "Il costo deve essere limitato alla componente connessa contenente il nodo sorgente");
    }
}

