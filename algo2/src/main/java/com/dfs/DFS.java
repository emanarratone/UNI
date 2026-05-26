package com.dfs;

import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;
import java.util.Collections;

public class DFS {
    GraphInterface grafo;
    ArrayList<Integer> scoperto;
    GraphInterface albero;
    ArrayList<Integer> ordine;  //ordine di fine visita
    int[] padre;
    int[] componenti;

    public DFS(GraphInterface grafo) {
        this.grafo = grafo;
        this.scoperto = new ArrayList<>(grafo.getOrder());
        this.albero = grafo.create();   //crea un grafo vuoto dello stesso tipo di grafo
        this.ordine = new ArrayList<>(grafo.getOrder());
        this.padre = new int[grafo.getOrder()];
        this.componenti = new int[grafo.getOrder()];
    }

    private void visitaDFSC(){
        int numComp = -1;
        for (int i=0; i<grafo.getOrder(); i++){
            if((! scoperto.contains(i))) numComp++;
            if(!(scoperto.contains(i))){
                visitaDFS(i, numComp);
            }
        }
    }

    private void visitaDFS(int sorgente) {
        scoperto.add(sorgente);
        for (int nodo : grafo.getNeighbors(sorgente)){
            if(!(scoperto.contains(nodo))){
                if(!albero.hasEdge(nodo, sorgente) //per evitare di aggiungere due volte lo stesso arco
                        || !albero.hasEdge(sorgente, nodo))albero.addEdge(sorgente, nodo);
                visitaDFS(nodo);
            }
        }
        ordine.add(sorgente);
    }

    private void visitaDFS(int sorgente, int numComp) {
        scoperto.add(sorgente);
        componenti[sorgente] = numComp;
        for (int nodo : grafo.getNeighbors(sorgente)){
            if(!(scoperto.contains(nodo))){
                if(!albero.hasEdge(nodo, sorgente) //per evitare di aggiungere due volte lo stesso arco
                        || !albero.hasEdge(sorgente, nodo))albero.addEdge(sorgente, nodo);
                visitaDFS(nodo, numComp);
            }
        }
        ordine.add(sorgente);
    }

    public GraphInterface DFSForest() {
        this.albero = grafo.create(); //altrimenti sovrascrive ogni volta lo stesso albero
        visitaDFSC();
        return this.albero;
    }

    public GraphInterface getDFStree (int sorgente) {
        try {
            if (sorgente < 0 || sorgente >= grafo.getOrder())
                throw new IllegalArgumentException("Sorgente non valida");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Grafo non valido");
        }
        this.albero = grafo.create(); //altrimenti sovrascrive ogni volta lo stesso albero
        visitaDFS(sorgente);
        return this.albero;
    }

    public ArrayList<Integer> getOrdine(int sorgente) {
        try {
            if (sorgente < 0 || sorgente >= grafo.getOrder())
                throw new IllegalArgumentException("Sorgente non valida");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Grafo non valido");
        }
        this.albero = grafo.create();
        return this.ordine;
    }

    public boolean hasCycle(int sorgente) {
        scoperto.add(sorgente);
        boolean cycle = false;
        for (int nodo : grafo.getNeighbors(sorgente)){
            padre[nodo] = sorgente;
            if(!(scoperto.contains(nodo))){
                if(!albero.hasEdge(nodo, sorgente)
                        || !albero.hasEdge(sorgente, nodo))albero.addEdge(sorgente, nodo);
                return hasCycle(nodo);
            }
            if(padre[sorgente] != nodo){
                cycle = true;
            }
        }
        return cycle;
    }

    public boolean isConnected(int sorgente) {
        visitaDFS(sorgente);
        return scoperto.size() == grafo.getOrder();
    }

    public int[] ConnectedComponents() {
        visitaDFSC();
        return componenti;
    }

    public ArrayList<Integer> TopologicalOrder (){
        if(grafo.getClass() == UndirectedGraph.class || hasCycle(0)) throw new IllegalArgumentException("Il grafo non e' un DAG");
        visitaDFSC();
        ArrayList<Integer> ofv = this.ordine; //Ordine di fine visita
        Collections.reverse(ofv);
        return ofv;
    }
}
