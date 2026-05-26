import com.dfs.SS;
import it.uniupo.graphLib.DirectedGraph;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class SSTest {

    @Test
    public void testCreate(){
        DirectedGraph g = new DirectedGraph(1);
        SS ss = new SS(g);
        assertNotNull(ss);
    }

    @Test
    public void testDepends(){
        DirectedGraph g = new DirectedGraph("7; 0 3; 0 4; 0 5; 1 0; 1 3; 2 5; 3 2; 4 1; 4 3; 6 2");
        SS ss = new SS(g);
        assertEquals(1, ss.depends(3, 1));
        assertEquals(2, ss.depends(3, 5));
        assertEquals(0, ss.depends(3, 6));
        assertEquals(-1, ss.depends(1, 7));
        assertEquals(-2, ss.depends(1, 1));
        assertEquals(-3, ss.depends(0, 4));

    }
}
