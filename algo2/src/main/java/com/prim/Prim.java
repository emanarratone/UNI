package com.prim;


import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.*;

import java.util.ArrayList;

public class Prim {
    UndirectedGraph graph;
    int cost;
    UndirectedGraph mst;

    public Prim(UndirectedGraph graph) {
        this.cost = 0;
        this.graph = graph;
        mst = new UndirectedGraph(graph.getOrder());
    }

    private void algoPrim(int sorgente){
        ArrayList<Integer> scoperti = new ArrayList<>();
        scoperti.add(sorgente);
        MinHeap<Edge,Integer> heap = new MinHeap<>();
        for(Edge e : graph.getOutEdges(sorgente)){
            heap.add(e, e.getWeight());
        }
        while(!heap.isEmpty()){
            Edge edge = heap.extractMin();
            if(!scoperti.contains(edge.getHead())){
                scoperti.add(edge.getHead());
                mst.addEdge(edge);
                cost += edge.getWeight();
            }
            for(Edge e : graph.getOutEdges(edge.getHead())){
                if(!scoperti.contains(e.getHead()))heap.add(e, e.getWeight());
            }
        }
    }

    public int getCost() {
        algoPrim(0);
        return cost;
    }
}
