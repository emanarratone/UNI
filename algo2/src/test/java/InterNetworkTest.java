import com.dijkstra.*;
import com.dijkstra.InterNetwork.InterNetwork;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class InterNetworkTest {

    @Test
    public void testCreate() {
        DirectedGraph g = new DirectedGraph(1);
        InterNetwork in = new InterNetwork(g,1);
        assertNotNull(in);
    }

    @Test
    public void testOneNode() {
        DirectedGraph g = new DirectedGraph(1);
        InterNetwork in = new InterNetwork(g,0);
        assertEquals(0, in.getDistanza()[0]); //la distanza della sorgente da se stessa è 0
    }

    @Test
    public void testNoH() {
        DirectedGraph g = new DirectedGraph("6;0 1 2 ;0 2 3;1 3 1;1 4 3;3 5 5;2 5 2");
        InterNetwork in = new InterNetwork(g,0);
        assertEquals(3, in.numberOfHopps(0, 2));
    }


}
