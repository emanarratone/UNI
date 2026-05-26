import com.kruskal.Kruskal;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KruskalTest {

    @Test
    public void testCreate() {
        UndirectedGraph g = new UndirectedGraph(1);
        Kruskal kruskal = new Kruskal(g);
        assertNotNull(kruskal, "L'oggetto Kruskal deve essere creato correttamente");
    }

    @Test
    public void testOneNode() {
        UndirectedGraph g = new UndirectedGraph(1);
        Kruskal kruskal = new Kruskal(g);
        assertEquals(0, kruskal.getCost(), "Un grafo con un solo nodo ha MST di costo 0");
    }

    @Test
    public void testSimpleGraph() {
        // 3 nodi, archi: 0-1 (1), 1-2 (2), 0-2 (3)
        // MST: 0-1 (1) + 1-2 (2) = costo 3
        UndirectedGraph g = new UndirectedGraph("3;0 1 1;1 2 2;0 2 3");
        Kruskal kruskal = new Kruskal(g);
        assertEquals(3, kruskal.getCost(), "Il costo minimo dell’MST deve essere 3");
    }

    @Test
    public void testComplexGraph() {
        // 6 nodi come nel test di Prim
        // Archi: 0-1 (2), 0-2 (3), 1-3 (1), 1-4 (3), 3-5 (5), 2-5 (2)
        // MST atteso = 1 + 2 + 2 + 3 + 3 = 11
        UndirectedGraph g = new UndirectedGraph("6;0 1 2;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        Kruskal kruskal = new Kruskal(g);
        assertEquals(11, kruskal.getCost(), "Il costo dell’MST deve essere 11");
    }

    @Test
    public void testDisconnectedGraph() {
        // Due componenti: (0-1) e (2-3)
        UndirectedGraph g = new UndirectedGraph("4;0 1 5;2 3 7");
        Kruskal kruskal = new Kruskal(g);
        // L’MST sarà la somma dei minimi archi di ciascuna componente: 5 + 7 = 12
        assertEquals(12, kruskal.getCost(), "In un grafo disconnesso, il costo dell’MST è la somma dei minimi per componente");
    }

    @Test
    public void testNoEdges() {
        // Nessun arco
        UndirectedGraph g = new UndirectedGraph("4");
        Kruskal kruskal = new Kruskal(g);
        assertEquals(0, kruskal.getCost(), "Un grafo senza archi ha costo MST pari a 0");
    }

    @Test
    public void testEdgesSimpleGraph() {
        // Grafo: 3 nodi, archi 0-1 (1), 1-2 (2), 0-2 (3)
        UndirectedGraph g = new UndirectedGraph("3;0 1 1;1 2 2;0 2 3");
        Kruskal kruskal = new Kruskal(g);
        UndirectedGraph mst = kruskal.getMst();

        List<String> expectedEdges = List.of("0-1", "1-2"); // MST possibile

        List<String> actualEdges = new ArrayList<>();
        for (int i = 0; i < mst.getOrder(); i++) {
            for (Edge e : mst.getOutEdges(i)) {
                if (e.getTail() < e.getHead()) {
                    actualEdges.add(e.getTail() + "-" + e.getHead());
                }
            }
        }

        for (String edge : expectedEdges) {
            assertTrue(actualEdges.contains(edge), "L'MST deve contenere l'arco " + edge);
        }
        assertEquals(expectedEdges.size(), actualEdges.size(), "L'MST non deve contenere archi extra");
    }

    @Test
    public void testEdgesComplexGraph() {
        // Grafo con 6 nodi
        UndirectedGraph g = new UndirectedGraph("6;0 1 2;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        Kruskal kruskal = new Kruskal(g);
        UndirectedGraph mst = kruskal.getMst();

        // MST atteso: alcuni archi possibili con costo minimo 11
        List<String> possibleEdges = List.of("0-1", "0-2", "1-3", "1-4", "2-5", "3-5");
        List<String> actualEdges = new ArrayList<>();
        for (int i = 0; i < mst.getOrder(); i++) {
            for (Edge e : mst.getOutEdges(i)) {
                if (e.getTail() < e.getHead()) {
                    actualEdges.add(e.getTail() + "-" + e.getHead());
                }
            }
        }

        // Controlliamo che tutti gli archi dell'MST siano tra quelli possibili
        for (String edge : actualEdges) {
            assertTrue(possibleEdges.contains(edge), "L'MST contiene un arco non valido: " + edge);
        }
        assertEquals(mst.getOrder() - 1, actualEdges.size(), "L'MST deve avere esattamente n-1 archi");
    }
}

