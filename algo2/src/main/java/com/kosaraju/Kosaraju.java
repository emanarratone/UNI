package com.kosaraju;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphUtils;

import java.util.ArrayList;
import java.util.Collections;

public class Kosaraju {

    DirectedGraph grafo;
    ArrayList<Integer> scoperto;
    ArrayList<Integer> ordine;  //ordine di fine visita
    int[] componenti;

    public Kosaraju(DirectedGraph grafo) {
        this.grafo = grafo;
        this.scoperto = new ArrayList<>(grafo.getOrder());
        this.ordine = new ArrayList<>(grafo.getOrder());
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
                visitaDFS(nodo, numComp);
            }
        }
        ordine.add(sorgente);
    }
    public ArrayList<Integer> getOfvInverso() {
        visitaDFSC();
        ArrayList<Integer> ofv = new ArrayList<>(this.ordine);
        Collections.reverse(ofv);
        return ofv;
    }

    public int[] ConnectedComponents() {
        visitaDFSC();
        return componenti;
    }

    public int[] algoKosaraju() {
        ArrayList<Integer> ofv = getOfvInverso();
        DirectedGraph g = new DirectedGraph(grafo.getOrder());
        grafo = GraphUtils.reverseGraph(grafo);
        visitaKosaraju(ofv);
        return componenti;
    }

    private void visitaKosaraju(ArrayList<Integer> ofv){
        scoperto.clear();
        int numComp = -1;
        for (int i: ofv){
            if((! scoperto.contains(i))) numComp++;
            if(!(scoperto.contains(i))){
                visitaDFS(i, numComp);
            }
        }
    }
}
