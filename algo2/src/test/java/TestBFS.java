import com.bfs.BFS;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBFS {
    GraphInterface testGraph;
    BFS visit;
    
    //********************************
    //testCreate(): non ci sono errori nel costruttore
    
    @Test
    @Order(1)
    public void testCreate() {
        testGraph = new UndirectedGraph(1);
        visit = new BFS(testGraph);
        assertNotNull(visit);
    }
    
    //********coda********************
    //avete scritto correttamente la condizione di terminazione del ciclo?
    
    @Test
    @Order(2)
    @Timeout(value = 500)
    public void testCoda() {
    	 testGraph = new UndirectedGraph("2; 0 1");
         visit = new BFS(testGraph);
         visit.getNodesInOrderOfVisit(0);
    }
     
    //********test base***************
    
    //deve funzionare correttamente in un grafo banale con un solo nodo e senza archi
    @Test
    @Order(3)
    public void testOneNodeVisited() { //BFS su grafo con un solo nodo
        testGraph = new UndirectedGraph(1);
        visit = new BFS(testGraph);
        ArrayList<Integer> visited = visit.getNodesInOrderOfVisit(0);
        assertEquals(1, visited.size(),"La visita in ampiezza deve trovare un nodo");
        int node = visited.get(0);
        assertEquals(0, node,"Il nodo trovato deve essere 0");
    }

    //deve funzionare correttamente in un grafo banale con due nodi e un arco
    @Test
    @Order(4)
    public void testTwoNodesVisited() { //BFS su grafo con due nodi
        testGraph = new UndirectedGraph(2);
        testGraph.addEdge(1, 0);
        visit = new BFS(testGraph);
        ArrayList<Integer> visited = visit.getNodesInOrderOfVisit(1);
        assertEquals(2, visited.size(),"La visita in ampiezza deve trovare due nodi");
        int node = visited.get(0);
        assertEquals(1,node,"Il primo nodo incontrato e' 1");
        node = visited.get(1);
        assertEquals(0,node,"Il secondo nodo incontrato e' 0");

    }
    
    //************verifica che la visita sia realmente una BFS***********
   @Test
   @Order(5)
   public void testBFSOrder() {
	   testGraph = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
       visit = new BFS(testGraph);
       ArrayList<Integer> ordineDiVisita = visit.getNodesInOrderOfVisit(2);
       assertTrue(ordineDiVisita.get(2) == 0 || ordineDiVisita.get(2) == 3,"La visita in ampiezza con sorgente 2 deve trovare i nodi nell'ordine 2,3,0,1 (0 e' il terzo visitato) oppure 2,0,3,1 (3 e' il terzo visitato)");
       int node = ordineDiVisita.get(2);
       assertNotEquals(1, node,"La visita in ampiezza non deve trovare 1 come terzo nodo");
       
   }
   
   
   //*************test inizializzazione******************
   
   //se fallisce uno dei due prossimi test, probabilmente non avete inizializzato nel punto giusto
   //l'array dei nodi scoperti e/o l'ArrayList dell'ordine di visita
   
   //numero di nodi scoperti
   @Test
   @Order(6)
   public void testInitNumeroNodi() {
     GraphInterface grafo = 
   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
     BFS visit = new BFS(grafo); //<<- creato una volta sola
     assertEquals(4, visit.getNodesInOrderOfVisit(0).size()); //<<-due chiamate del metodo con argomenti diversi
     assertEquals(4, visit.getNodesInOrderOfVisit(2).size());
   }

   //ordine dei nodi scoperti
   @Test
   @Order(7)
   public void testInitOrdine() {
     GraphInterface grafo = 
   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
     BFS visit = new BFS(grafo); //<<- creato una volta sola
     int dist = visit.getNodesInOrderOfVisit(2).get(3);
     assertNotEquals(3, dist,"Nella visita da sorgente 2: 3 non e' il quarto visitato"); //<<-tre chiamate del metodo
     dist = visit.getNodesInOrderOfVisit(1).get(3);
     assertNotEquals(0,dist,"Nella visita da sorgente 1: 0 non e' il quarto visitato");
     dist = visit.getNodesInOrderOfVisit(0).get(3);
     assertNotEquals(2,dist,"Nella visita da sorgente 0: 2 non e' il quarto visitato"); 
   }
   
   //***********test distanza***********
   
   //valore corretto da una sorgente
  @Test
  @Order(8)
  public void testDistanza() {
	  GraphInterface grafo = new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
	  BFS visit = new BFS(grafo);
	  int[] distanza = visit.getDistance(2); //ottieni tutte le distanze dalla sorgente 2
	  assertEquals(0, distanza[2],"distanza(2,2) = 0");
	  assertEquals(1, distanza[0],"distanza(2,0) = 1");  
	  assertEquals(1, distanza[3],"distanza(2,3) = 1");  
	  assertEquals(2, distanza[1],"distanza(2,1) = 2");  
  }
  
  //inizializzazione: metodo richiamato con due sorgenti diverse
  @Test
  @Order(9)
  public void testInitDistanza() {
    GraphInterface grafo = 
  		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
    BFS visit = new BFS(grafo); //<<- creato una volta sola
	  assertEquals(0, visit.getDistance(2)[2],"distanza(2,2) = 0"); //<--distanze da 2
	  assertEquals(1, visit.getDistance(2)[0],"distanza(2,0) = 1");
	  assertEquals(2, visit.getDistance(3)[0],"distanza(3,0) = 2"); //<--distanze da 3
	  assertEquals(1, visit.getDistance(3)[1],"distanza(3,1) = 1");

  }

  @Test
  @Order(10)
    public void testDistanzaNodo(){
      GraphInterface grafo =
              new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
      BFS visit = new BFS(grafo); //<<- creato una volta sola
      assertEquals(0, visit.getDistance(2, 2),"distanza(2,2) = 0"); //<--distanze da 2
      assertEquals(1, visit.getDistance(2, 0),"distanza(2,0) = 1");
      assertEquals(2, visit.getDistance(3, 0),"distanza(3,0) = 2"); //<--distanze da 3
      assertEquals(1, visit.getDistance(3, 1),"distanza(3,1) = 1");

  }

}
