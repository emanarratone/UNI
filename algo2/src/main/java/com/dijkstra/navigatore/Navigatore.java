package com.dijkstra.navigatore;

import java.util.ArrayList;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

public class Navigatore {
    private DirectedGraph grafo;
    private boolean autostrada[][];
    private Integer partenza;
    private Integer[] distanza;

    public Navigatore(DirectedGraph grafo, boolean autostrada[][], Integer partenza, Integer[] distanza){
        this.grafo = grafo;
        this.autostrada = autostrada;
        this.partenza = partenza;
        this.distanza = distanza;
    } 


    private void dijkstra(Integer partenza, boolean evitaA){
        ArrayList<Integer> scoperti = new ArrayList<>();
        Integer[] distanza = new Integer[grafo.getOrder()];

        scoperti.add(partenza);
        distanza[partenza] = 0;

        MinHeap<Edge, Integer> heap = new MinHeap<>();

        for(Edge e: grafo.getOutEdges(partenza)){
            if(evitaA && autostrada[e.getTail()][e.getHead()] == false){
                heap.add(e, distanza[e.getTail()] + e.getWeight());
            }
        }

        while(!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!scoperti.contains(e.getHead())){
                distanza[e.getHead()] = distanza[e.getTail()] + e.getWeight();
            }
            scoperti.add(e.getHead());
            for(Edge ee: grafo.getOutEdges(e.getHead())){
                if(evitaA && autostrada[e.getTail()][e.getHead()] == false){
                    heap.add(ee, distanza[e.getTail()] + ee.getWeight());
                }
            }
        }
    }

    public int  distance(int destinazione, boolean evitaAutostrada){

        dijkstra(partenza, evitaAutostrada);

        return distanza[destinazione];
    }
}
