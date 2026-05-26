package com.dijkstra;

import it.uniupo.algoTools.*;
import it.uniupo.graphLib.*;

import java.util.ArrayList;

public class Dijkstra {

    GraphInterface graph;
    ArrayList<Integer> scoperto;
    int[] ordine;
    int[] distanza;

    public Dijkstra(GraphInterface grafo) {
        this.graph = grafo;
        this.scoperto = new ArrayList<>(grafo.getOrder());
        this.ordine = new int[grafo.getOrder()];
        this.distanza = new int[grafo.getOrder()];
    }

    private void dijkstra (int sorgente){
    scoperto.add(sorgente);
    MinHeap<Edge,Integer> heap = new MinHeap<>();
    initDistance();
    distanza[sorgente] = 0;
        for(Edge arco: graph.getOutEdges(sorgente)){
            heap.add(arco, distanza[arco.getTail()]+arco.getWeight()); //costruisce heap con i pesi
        }
        while(!heap.isEmpty()){
            Edge edge = heap.extractMin();
            if(!scoperto.contains(edge.getHead())) distanza[edge.getHead()] =  distanza[edge.getTail()] +  edge.getWeight();
            scoperto.add(edge.getHead());
            for(Edge arco: graph.getOutEdges(edge.getHead())){
                if(!scoperto.contains(arco.getHead())) {
                    heap.add(arco, distanza[arco.getTail()] + arco.getWeight()); //ricostruisce pesi per i vicini, stabilizza heap
                }                                                             // e completa la visita sul grafo (S,V)->(V,W)
            }
        }
    }


    private void initDistance(){
        int i = 0;
        while(i < distanza.length){
            distanza[i] = -1;
            i++;
        }
    }

    public int[] getdistanza(){
        dijkstra(0);
        return distanza;
    }
    public int[] getDistanza(int sorgente){
        dijkstra(sorgente);
        return distanza;
    }

}
