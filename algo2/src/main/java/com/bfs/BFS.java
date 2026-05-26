package com.bfs;

import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;

public class BFS {
	//variabili di istanza
	GraphInterface grafo; 										//per memorizzare il grafo su cui si lavora
	boolean[] scoperto; 										//per memorizzare i nodi scoperti: scoperti[2]=true se il nodo 2 è stato scoperto (utile che sia globale?)
	ArrayList<Integer> nodiVisitatiInOrdine; 					//elenco dei nodi nell'ordine in cui sono stati visitati


	/************
	 * Costruttore per la classe BFS che inizializza gli oggetti locali e attribuisce un valore al grafo di questa istanza
	 * @param grafoInInput grafo utilizzato per creare e operare su di esso all'interno della classe
	 */
	public BFS(GraphInterface grafoInInput){
		this.grafo = grafoInInput;
		this.scoperto = new boolean[grafoInInput.getOrder()]; //incremento dinamico array in base al numero di nodi di un grafo in input
		this.nodiVisitatiInOrdine = new ArrayList<>();
	}

	
	/**
	 *  Il metodo visitaBFS(int sorgente) fa una visita BFS dalla sorgente, ma non restituisce niente:
	 *  modifica i valori delle opportune variabili di istanza
	 */

	 private void visitaBFS(int sorgente) {
		 //la coda puo' essere implementata come una ArrayList di interi
		 ArrayList<Integer> coda = new ArrayList<Integer>();
		 ArrayList<Integer> nodiScoperti = new ArrayList<>();
		 GraphInterface albero = null;
		 coda.add(sorgente);
		 while(!coda.isEmpty()){
			 int V = coda.remove(0);    //per leggere e cancellare il primo elemento coda.remove(0)
			 for (Integer U : grafo.getNeighbors(V)) {
				 if (!nodiScoperti.contains(U)) {
					 nodiScoperti.add(U);
					 coda.add(U);    //per aggiungere un elemento in fondo alla "coda": coda.add(elemento)
					 albero.addEdge(U, V);
				 }
			 }
		 }
	 }
	 
	 public ArrayList<Integer> getNodesInOrderOfVisit(int sorgente){ //restituisce i nodi del grafo nell'ordine in cui vengono scoperti
		 ArrayList<Integer> res = new ArrayList<>();
		 ArrayList<Integer> coda = new ArrayList<Integer>();
		 ArrayList<Integer> nodiScoperti = new ArrayList<>();
		 GraphInterface albero = new UndirectedGraph(this.grafo.getOrder());
		 coda.add(sorgente);
		 while(!coda.isEmpty()){
			 int V = coda.remove(0);    //per leggere e cancellare il primo elemento coda.remove(0)
			 if(!nodiScoperti.contains(V)){
				 nodiScoperti.add(V);	//senza questo viene inserito due volte lo stesso nodo in res
				 res.add(V);
			 }
			 for (Integer U : grafo.getNeighbors(V)) {
				 if (!nodiScoperti.contains(U)) {
					 nodiScoperti.add(U);
					 coda.add(U);    //per aggiungere un elemento in fondo alla "coda": coda.add(elemento)
					 albero.addEdge(U, V);
					 res.add(U);
				 }
			 }
		 }
		 return res;
	 }


	 public int[] getDistance(int sorgente) { //restituisce le distanza di ciascun nodo da sorgente
		ArrayList<Integer> coda = new ArrayList<>();
		ArrayList<Integer> Scoperti = new ArrayList<>();
		int[] distanza = new int[this.grafo.getOrder()];
		coda.add(sorgente);
		Scoperti.add(sorgente);
		int i = 0;
		 while (i < this.grafo.getOrder()) {	//blocco x inizializzare distanza
			 distanza[i] = 0;
			 i++;
		 }
		 while(!coda.isEmpty()){			//va fatto OBBLIGATORIAMENTE con un ciclo while: gli iteratori crashano perchè modifichi la collezione
			 int u = coda.remove(0);  //<---questo fa crashare gli iteratori (For(Integer u: coda)...)
			 for(Integer v : this.grafo.getNeighbors(u)){
				 if(!Scoperti.contains(v)){
					 coda.add(v);
					 Scoperti.add(v);
					 distanza[v] = distanza[u] + 1;
				 }
			 }
		 }
		 return distanza;
	 }

	 public int getDistance(int sorgente, int nodo){
		 return getDistance(sorgente)[nodo];	//lol
	 }


}
