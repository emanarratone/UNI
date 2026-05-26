import com.kruskal.Clustering;

import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClusteringTest {

    @Test
    public void testCreate() {
        UndirectedGraph g = new UndirectedGraph(1);
        Clustering clustering = new Clustering(g, 1);
        assertNotNull(clustering);
    }

    @Test
    public void testOneNodeOneCluster() {
        UndirectedGraph g = new UndirectedGraph(1);
        Clustering clustering = new Clustering(g, 1);
        assertEquals(0, clustering.getSpaziamento(), "Spaziamento in un grafo con un solo nodo deve essere 0");
    }

    @Test
    public void testSimpleGraphTwoClusters() {
        // Grafo: 3 nodi, archi 0-1 (1), 1-2 (2), 0-2 (3)
        UndirectedGraph g = new UndirectedGraph("3;0 1 1;1 2 2;0 2 3");
        Clustering clustering = new Clustering(g, 2);

        // MST ha costo 3: archi 0-1 e 1-2 → separando in 2 cluster, lo spaziamento = arco più piccolo che collega i due cluster = 2
        assertEquals(2, clustering.getSpaziamento(), "Spaziamento corretto per 2 cluster deve essere 2");
    }

    @Test
    public void testSimpleGraphThreeClusters() {
        // Grafo: 3 nodi, archi 0-1 (1), 1-2 (2), 0-2 (3)
        UndirectedGraph g = new UndirectedGraph("3;0 1 1;1 2 2;0 2 3");
        Clustering clustering = new Clustering(g, 3);

        // 3 cluster → ogni nodo separato, spaziamento = arco più piccolo tra due cluster = 1
        assertEquals(1, clustering.getSpaziamento(), "Spaziamento corretto per 3 cluster deve essere 1");
    }

    @Test
    public void testComplexGraph() {
        // Grafo con 6 nodi
        UndirectedGraph g = new UndirectedGraph("6;0 1 2;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        Clustering clustering = new Clustering(g, 3);

        // Lo spaziamento dovrebbe essere l'arco minimo che collega due cluster rimanenti
        int spacing = clustering.getSpaziamento();
        assertEquals(3, clustering.getSpaziamento(), "Spaziamento corretto per 3 cluster deve essere 1");
    }

    @Test
    public void testSameCluster() {
        UndirectedGraph g = new UndirectedGraph("3;0 1 1;1 2 2");
        Clustering clustering = new Clustering(g, 1);

        // In un solo cluster, tutti i nodi devono essere nello stesso cluster
        assertTrue(clustering.sameCluster(0, 1));
        assertTrue(clustering.sameCluster(1, 2));
        assertTrue(clustering.sameCluster(0, 2));
    }
}

