import com.dfs.DFS;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DFSTest {
    GraphInterface testGraph;
    DFS dfsTest;

    //********************************
    //testCreate(): non ci sono errori nel costruttore

    @Test
    @Order(1)
    public void test00Create() {
        testGraph = new UndirectedGraph(1);
        dfsTest = new DFS(testGraph);
        assertNotNull(dfsTest);
    }


    //********test base***************

    //deve funzionare correttamente in un grafo banale con un solo nodo e senza archi
    @Test
    @Order(10)
    public void test10OneNodeVisited() { //DFS su grafo con un solo nodo
        testGraph = new UndirectedGraph(1);
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.getDFStree(0);
        int edges = tree.getEdgeNum();
        assertEquals(0, edges,"Non ci sono archi");
    }

    //deve funzionare correttamente in un grafo banale con due nodi e un arco
    @Test
    @Order(20)
    public void test11TwoNodesVisited() { //DFS su grafo con due nodi
        testGraph = new UndirectedGraph(2);
        testGraph.addEdge(1, 0);
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.getDFStree(0);
        int edges = tree.getEdgeNum();
        assertEquals(1, edges,"C'e' un arco");

    }

    //************verifica che la visita sia realmente una DFS***********
    @Test
    @Order(30)
    public void test15BFSOrder() {
        testGraph = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.getDFStree(2);
        assertTrue(!(tree.hasEdge(2,3))  ||
                !(tree.hasEdge(0,2)),"L'albero di visita in profondita' con sorgente 2 deve avere l'arco (2,3) o lo (0,2) ma non entrambi" );
        assertTrue((tree.hasEdge(1,3))  &&
                (tree.hasEdge(0,1)),"L'albero di visita in profondita' con sorgente 2 deve avere l'arco (2,3) o lo (0,2) ma non entrambi");

    }


    //*************test inizializzazione******************

    //numero di archi
    @Test
    @Order(40)
    public void test20InitArchi() {
        GraphInterface grafo =
                new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
        dfsTest = new DFS(grafo); //<<- creato una volta sola
        assertTrue(!(dfsTest.getDFStree(2).hasEdge(2,3))  ||
                !(dfsTest.getDFStree(2).hasEdge(0,2)),"L'albero di visita in profondita' con sorgente 2 deve avere l'arco (2,3) o lo (0,2) ma non entrambi" );
        assertTrue(!(dfsTest.getDFStree(0).hasEdge(0,1))  ||
                !(dfsTest.getDFStree(0).hasEdge(0,2)),"L'albero di visita in profondita' con sorgente 0 deve avere l'arco (0,2) o lo (0,1) ma non entrambi");
    }


    //***************test eccezione input metodo*************
    @Order(50)
    @Test
    public void test30illegalargBig() {
        GraphInterface grafo =
                new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
        DFS visit = new DFS(grafo);
        Assertions.assertThrows(IllegalArgumentException.class,() -> {  //solo JUnit5
            visit.getDFStree(5);
        });
    }

    @Test
    public void test35illegalargSmall() {
        GraphInterface grafo =
                new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
        DFS visit = new DFS(grafo);
        Assertions.assertThrows(IllegalArgumentException.class,() -> {  //solo JUnit5
            visit.getDFStree(-1);
        });
    }
    @Order(60)
    @Test
    public void test40illegalargOrd() {
        GraphInterface grafo =
                new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
        DFS visit = new DFS(grafo);
        Assertions.assertThrows(IllegalArgumentException.class,() -> {  //solo JUnit5
            visit.getDFStree(4);
        });
    }

    @Order(70)
    @Test
    public void test50illegalargLegal() {
        GraphInterface grafo =
                new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
        DFS visit = new DFS(grafo);
        Assertions.assertDoesNotThrow(() -> visit.getDFStree(0),"0 e' un argomento legittimo");
    }

    @Order(80)
    @Test
    public void test60DFSOrder() {
        testGraph = new DirectedGraph("5;0 4 ;4 2;2 1;1 3;3 0");
        dfsTest = new DFS(testGraph);
        dfsTest.getDFStree(0);
        List<Integer> exp = List.of(3, 1, 2, 4, 0); //l'ordine di fine visita deve essere 3,1,2,4,0 data la struttura del grafo
        assertEquals(5, dfsTest.getOrdine(0).size(),"L'ordine di visita deve contenere tutti i nodi");
        assertEquals(exp, dfsTest.getOrdine(0)); //l'ordine di visita deve essere 3,1,2,4,0 data la struttura del grafo
    }

    @Order(90)
    @Test
    public void test70DFSCycle() {
        testGraph = new DirectedGraph("5;0 4 ;4 2;2 1;1 3;3 0");
        dfsTest = new DFS(testGraph);
        assertTrue(dfsTest.hasCycle(0),"Il grafo contiene un ciclo");
        testGraph = new DirectedGraph("5;0 4 ;4 2;2 1;1 3");
        dfsTest = new DFS(testGraph);
        assertFalse(dfsTest.hasCycle(0),"Il grafo non contiene cicli");
        testGraph = new UndirectedGraph("5;0 4 ;4 2;2 1;1 3;3 0");
        dfsTest = new DFS(testGraph);
        assertFalse(dfsTest.hasCycle(0),"Il grafo non contiene cicli perche' non e' orientato");
    }

    @Order(100)
    @Test
    public void test80DFSForest(){
        testGraph = new UndirectedGraph("7;0 1;0 2;3 4;5 6");
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.DFSForest();
        assertTrue((tree.hasEdge(0,1))  &&
                (tree.hasEdge(0,2)),"L'albero di visita in profondita' con sorgente 0 deve avere gli archi (0,1) e (0,2)" );
        assertFalse((tree.hasEdge(3, 5)), "L'albero di visita in profondita' con sorgente 0 non deve avere l'arco (3,4)");
        assertTrue((tree.hasEdge(5, 6)), "L'albero di visita in profondita' con sorgente 0 deve avere l'arco (5,6)");
        assertEquals(testGraph.getOrder(), tree.getOrder(), "L'albero di visita in profondita' deve avere lo stesso numero di nodi del grafo originale");
    }

    @Order(110)
    @Test
    public void test90DFSAllCycle(){
        testGraph = new UndirectedGraph("4; 1 0; 0 2; 2 3; 3 0");
        dfsTest = new DFS(testGraph);
        assertTrue (dfsTest.hasCycle(1), "Il grafo contiene cicli");
    }

    @Order(120)
    @Test
    public void testIsConnected(){
        testGraph = new DirectedGraph("4; 0 1; 1 2; 2 3; 3 1; 2 0");
        dfsTest = new DFS(testGraph);
        assertTrue(dfsTest.isConnected(0));
    }

    @Order(130)
    @Test
    public void testIsNotConnected(){
        testGraph = new DirectedGraph("5; 0 1; 1 2; 2 3; 3 1; 2 0");    //c'è un nodo in più (4) che non è connesso
        dfsTest = new DFS(testGraph);
        assertFalse(dfsTest.isConnected(0));
    }

    @Order(140)
    @Test
    public void testConnectedComponents(){
        testGraph = new UndirectedGraph("6; 1 0; 0 4; 2 5");
        dfsTest = new DFS(testGraph);
        int [] res = dfsTest.ConnectedComponents();
        assertEquals(0, res[0]);
        assertEquals(1, res[2]);
        assertEquals(1, res[5]);
        assertEquals(2, res[3]);
    }

    @Order(150)
    @Test
    public void testOT() {
        testGraph = new DirectedGraph("4; 3 1; 1 2; 0 2; 1 0; 3 0");
        dfsTest = new DFS(testGraph);
        assertEquals(List.of(3, 1, 0, 2), dfsTest.TopologicalOrder());
    }

    @Order(160)
    @Test
    public void testDAG() {
        testGraph = new UndirectedGraph("4; 3 1; 1 2; 0 2; 1 0; 3 0");
        dfsTest = new DFS(testGraph);
        assertThrows(IllegalArgumentException.class, () -> dfsTest.TopologicalOrder());
    }
}