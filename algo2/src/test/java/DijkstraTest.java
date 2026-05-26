import com.dijkstra.*;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {

    @Test
    public void testCreate() {
        DirectedGraph g = new DirectedGraph(1);
        Dijkstra dijkstra = new Dijkstra(g);
        assertNotNull(dijkstra);
    }

    @Test
    public void testOneNode() {
        DirectedGraph g = new DirectedGraph(1);
        Dijkstra dijkstra = new Dijkstra(g);
        assertEquals(0, dijkstra.getdistanza()[0]); //la distanza della sorgente da se stessa è 0
    }

    @Test
    public void DirectedDijkstra() {
        DirectedGraph g = new DirectedGraph("6;0 1 2 ;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        Dijkstra dijkstra = new Dijkstra(g);
        assertArrayEquals(new int[]{0, 2, 3, 3, 5, 5}, dijkstra.getdistanza());
    }

    @Test
    public void UndirectedDijkstra() {
        UndirectedGraph g = new UndirectedGraph("6;0 1 2 ;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        Dijkstra dijkstra = new Dijkstra(g);
        assertArrayEquals(new int[] {0, 2, 3, 3, 5, 5}, dijkstra.getdistanza());
    }
}
