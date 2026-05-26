import com.kruskal.pisteCiclabili;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testPisteCiclabili {

    @Test
    public void create(){
        pisteCiclabili pc = new pisteCiclabili(new UndirectedGraph(1), new int[1][1]);
        assertNotNull(pc);
    }

    @Test
    public void testPiste(){
        UndirectedGraph g = new UndirectedGraph("5; 0 1 3; 0 2 2; 0 3 7; 1 3 4; 1 4 5; 2 3 12; 3 4 6");
        int[][] tempo = new int[5][5];
        tempo[0][0] = 0;
        tempo[1][1] = 0;
        tempo[2][2] = 0;
        tempo[3][3] = 0;
        tempo[4][4] = 0;
        tempo[0][1] = 6;
        tempo[0][2] = 5;
        tempo[0][3] = 9;
        tempo[1][3] = 7;
        tempo[1][4] = 4;
        tempo[2][3] = 8;
        tempo[3][4] = 3;

        pisteCiclabili pc = new pisteCiclabili(g, tempo);
        assertTrue(pc.possibileEntro(25));
        assertFalse(pc.possibileEntro(21));
        assertTrue(pc.possibileEntro(22));

    }
}
