package com.dijkstra.InterNetwork;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;

public class InterNetwork {
    GraphInterface grafo;
    int partenza;
    int[] distanza;

    public InterNetwork(GraphInterface grafo, int partenza){
        this.grafo = grafo;
        this.partenza = partenza;
        this.distanza = new int[grafo.getOrder()];
    }

    private void initDistance(){
        for(int i: distanza){
            i = -1;
        }
    }

    public int[] getDistanza() {
        return distanza;
    }

    private void dijkstra(int partenza){
        initDistance();
        // Implementazione dell'algoritmo di Dijkstra
        ArrayList<Integer> scoperti = new ArrayList<>();
        MinHeap<Edge, Integer> heap = new MinHeap<>();
        scoperti.add(partenza);
        distanza[partenza] = 0;
        // Inizializzazione
        for(Edge e: grafo.getOutEdges(partenza)){
            heap.add(e, e.getWeight());
        }
        while(!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!scoperti.contains(e.getHead())) {
                distanza[e.getHead()] = distanza[e.getTail()] + e.getWeight();
                scoperti.add(e.getHead());
            }
            for(Edge ee: grafo.getOutEdges(e.getHead())){
                if(!scoperti.contains(ee.getHead())) heap.add(ee, ee.getWeight());
            }
        }
    }

    public int numberOfHopps(int partenza, int destinazione){
        if(partenza < 0 || partenza > grafo.getOrder() || destinazione < 0 || destinazione > grafo.getOrder()) {
            throw new IllegalArgumentException("ID dei nodi non validi");
        }

        dijkstra(partenza);
        return distanza[destinazione];
    }
}
