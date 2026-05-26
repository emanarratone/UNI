import it.uniupo.graphLib.DirectedGraph;
import com.kosaraju.Kosaraju;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KosarajuTest {
    @Test
    public void testCreate() {
        DirectedGraph g = new DirectedGraph(1);
        Kosaraju k = new Kosaraju(g);
        assertNotNull(k);
    }
    @Test
    public void testofvInverso() {
        DirectedGraph g = new DirectedGraph("5;0 1;1 2;2 0;1 3;3 4");
        Kosaraju k = new Kosaraju(g);
        assertEquals("[0, 1, 3, 4, 2]", k.getOfvInverso().toString());
    }
    @Test
    public void testConnectedComponents() {
        DirectedGraph g = new DirectedGraph("5; 1 4; 4 3; 3 1; 1 2; 4 0; 2 0; 0 2;");
        Kosaraju k = new Kosaraju(g);
        assertArrayEquals(new int[]{0,1,0,1,1}, k.ConnectedComponents());
    }
    @Test
    public void testKosaraju(){
        DirectedGraph g = new DirectedGraph("5; 1 4; 4 3; 3 1; 1 2; 4 0; 2 0; 0 2;");
        Kosaraju k = new Kosaraju(g);
        assertArrayEquals(new int[]{0,1,0,1,1}, k.ConnectedComponents());
    }
}
