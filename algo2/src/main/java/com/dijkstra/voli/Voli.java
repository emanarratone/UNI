package com.dijkstra.voli;

import com.dijkstra.Dijkstra;
import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.ArrayList;

public class Voli {
    DirectedGraph graph;
    int[] scali;
    int[] tempi;
    int[] cammini;

    public Voli(DirectedGraph graph) {
        this.graph = graph;
        this.scali = new int[graph.getOrder()];
        this.tempi = new int[graph.getOrder()];
        this.cammini = new int[graph.getOrder()];
    }

    public int tempoMinimo(int partenza, int destinazione){
        if(partenza < 0 || destinazione < 0 || partenza > destinazione ||
                partenza >= graph.getOrder() || destinazione >= graph.getOrder()){
            throw new IllegalArgumentException("Partenza o destinazione non valide");
        }
        Dijkstra dijkstra = new Dijkstra(graph);
        int [] distanze = dijkstra.getDistanza(partenza);
        return distanze[destinazione];
    }

    public int scali(int partenza, int destinazione){
        if(partenza < 0 || destinazione < 0 || partenza > destinazione ||
                partenza >= graph.getOrder() || destinazione >= graph.getOrder()){
            throw new IllegalArgumentException("Partenza o destinazione non valide");
        }
        dijkstraVoli(partenza);
        return scali[destinazione];
    }

    public int tempo(int partenza, int destinazione){
        if(partenza < 0 || destinazione < 0 || partenza > destinazione ||
                partenza >= graph.getOrder() || destinazione >= graph.getOrder()){
            throw new IllegalArgumentException("Partenza o destinazione non valide");
        }
        init(tempi);
        dijkstraVoli(partenza);
        return tempi[destinazione];
    }

    private void dijkstraVoli (int sorgente){
        ArrayList<Integer> scoperto = new ArrayList<>(graph.getOrder());
        scoperto.add(sorgente);
        MinHeap<Edge,Integer> heap = new MinHeap<>();
        init(scali);
        scali[sorgente] = 0;
        tempi[sorgente] = 0;
        for(Edge arco: graph.getOutEdges(sorgente)){
            scali[arco.getHead()] = 1;
            tempi[arco.getHead()] = arco.getWeight();
            heap.add(arco, scali[arco.getTail()]); //costruisce heap senza i pesi (scali)
        }
        while(!heap.isEmpty()){
            Edge edge = heap.extractMin();
            if(!scoperto.contains(edge.getHead())){
                scali[edge.getHead()] =  scali[edge.getTail()] +  1;
                tempi[edge.getHead()] = tempi[edge.getHead()] + tempi[edge.getTail()];
            }
            scoperto.add(edge.getHead());
            for(Edge arco: graph.getOutEdges(edge.getHead())){
                if(!scoperto.contains(arco.getHead())) {
                    heap.add(arco, scali[arco.getTail()] + 1); //ricostruisce pesi per i vicini, stabilizza heap
                }                                                 // e completa la visita sul grafo (S,V)->(V,W)
            }
        }
        normalize(scali);
    }


    private void dijkstraVeloce (int sorgente){
        ArrayList<Integer> scoperto = new ArrayList<>(graph.getOrder());
        ArrayList<Edge> cammino = new ArrayList<>();
        scoperto.add(sorgente);
        MinHeap<Edge,Integer> heap = new MinHeap<>();
        int[] distanza = new int[graph.getOrder()];
        init(distanza);
        init(cammini);
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

    private void normalize(int[] distanza){
        for(int i=0; i<distanza.length; i++){
            if(distanza[i] >0) distanza[i]  = distanza[i] - 1;
        }
    }

    private void init(int[] distanza){
        int i = 0;
        while(i < distanza.length){
            distanza[i] = -1;
            i++;
        }
    }
}
