import com.dijkstra.Hogwarts;
import it.uniupo.graphLib.DirectedGraph;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class HogwartsTest {

    @Test
    public void testCreate(){
        DirectedGraph g = new DirectedGraph(1);
        Hogwarts h = new Hogwarts(g, new int[1][1]);
        assertNotNull(h);
    }

    @Test
    public void testHogwarts(){
        DirectedGraph g = new DirectedGraph("5; 0 1 5; 1 3 7; 2 0 4; 3 0 8; 3 2 2; 4 1 7; 4 3 4");
        int[][] costo  = new int[5][5];
        costo[0][0] = 0;
        costo[1][1] = 0;
        costo[2][2] = 0;
        costo[3][3] = 0;
        costo[4][4] = 0;
        costo[0][1] = 3;
        costo[1][3] = 5;     //dovrei farlo anche all'esame sta roba? in mezz'ora? o lo copio o devo inventarmene uno, nosense
        costo[2][0] = 5;
        costo[3][0] = 7;
        costo[3][2] = 12;
        costo[4][1] = 5;
        costo[4][3] = 6;

        Hogwarts h = new Hogwarts(g, costo);

        assertThrows(IllegalArgumentException.class, ()->{h.vitaFinale(6,6,6,6);});
        assertEquals(15, h.vitaFinale(4, 1, 7, 20));
        assertEquals(13, h.vitaFinale(3, 0, 9, 20));
        assertEquals(3, h.vitaFinale(3, 0, 4, 20));
        assertEquals(0, h.vitaFinale(3, 0, 5, 15));
        assertEquals(-1, h.vitaFinale(2, 0, 3, 20));
        assertEquals(-1, h.vitaFinale(0, 4, 1, 12));
        assertEquals(-1, h.vitaFinale(2, 1, 0, 10));
        assertEquals(10, h.vitaFinale(1, 1, 0, 10));

    }
}
