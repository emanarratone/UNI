package com.dijkstra;

import it.uniupo.algoTools.MaxHeap;
import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Hogwarts {

    DirectedGraph castello;
    int[][] costo;

    public Hogwarts(DirectedGraph castello, int[][] costo) {
        this.castello = castello;
        this.costo = costo;
    }

    public int vitaFinale(int inizio, int obbiettivo, int abil, int vita)  {
        if(inizio < 0 || obbiettivo < 0 || inizio >= castello.getOrder() || obbiettivo >= castello.getOrder())
            throw new IllegalArgumentException("Stanza iniziale o obbiettivo non valida");

        int[] vitaResidua = new int[castello.getOrder()];
        Arrays.fill(vitaResidua, -1);
        vitaResidua[inizio] = vita;
        ArrayList<Integer> scoperti = new ArrayList<>();
        scoperti.add(inizio);
        MaxHeap<Edge, Integer> heap = new MaxHeap<>();

        for(Edge e: castello.getOutEdges(inizio)){
            if(e.getWeight() <= abil){
                heap.add(e, vitaResidua[e.getTail()] - costo[e.getTail()][e.getHead()]);
            }
        }

        while(!heap.isEmpty()){
            Edge e = heap.extractMax();
            if(!scoperti.contains(e.getHead())){
                vitaResidua[e.getHead()] = vitaResidua[e.getTail()] - costo[e.getTail()][e.getHead()];
                scoperti.add(e.getHead());
            }
            for(Edge ee: castello.getOutEdges(e.getHead())){
                if(ee.getWeight() <= abil){
                    if(!scoperti.contains(ee.getHead()))heap.add(ee, vitaResidua[ee.getTail()] - costo[ee.getTail()][ee.getHead()]);
                }
            }

        }
        if(vitaResidua[obbiettivo] == -1) return -1;
        if(vitaResidua[obbiettivo] <= 0) return 0;
        return vitaResidua[obbiettivo];
    }
}
