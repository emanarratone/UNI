import com.dijkstra.Game.Game;
import it.uniupo.graphLib.DirectedGraph;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;



public class gameTest {


    @Test
    public void testCreate() {
        DirectedGraph g = new DirectedGraph(1);
        int[] config = {0, 0, 1, 0, 2, 0, 1};
        Game in = new Game(g,config);
        assertNotNull(in);
    }


    @Test
    public void testGame(){
        DirectedGraph g = new DirectedGraph("7; 0 3; 1 4; 2 5; 3 2; 3 6; 4 3; 5 0; 5 1");
        Game in = new Game(g, new int[]{0, 0, 1, 0, 2, 0, 1});
        assertEquals(1, in.wins(5, 2, 2));
        assertEquals(0, in.wins(5, 1, 2));
        assertEquals(-1, in.wins(3, 0, 1));
        assertEquals(-1, in.wins(8, 1, -1));

    }
}
