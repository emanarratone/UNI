package com.kruskal;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.QuickFind;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.*;

import java.util.ArrayList;


public class Kruskal {

    UndirectedGraph graph;
    int cost;
    UndirectedGraph mst;

    public Kruskal(UndirectedGraph graph) {
        this.graph = graph;
        this.cost = 0;
        this.mst = new UndirectedGraph(graph.getOrder());
        algoKruskal();  //test: quando costruisco ottengo subito mst e costo
    }

    private void algoKruskal() {
        MinHeap<Edge,Integer> heap = new MinHeap<>();
        UnionFind uf = new QuickFind(graph.getOrder());
        uf.create(graph.getOrder());
        buildHeap(heap);
        while(!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(uf.find(e.getTail()) != uf.find(e.getHead())){
                mst.addEdge(e);
                uf.union(uf.find(e.getTail()), uf.find(e.getHead()));   //la find serve per avere il rappresentante dell'insieme, scarsa implementazione di UnionFind...
                cost += e.getWeight();  //essendo greedy non tornerò più indietro
            }                           //basta quindi incrementare
        }
    }

    private void buildHeap(MinHeap<Edge, Integer> heap) {
        for (int i = 0; i < graph.getOrder(); i++) {
            for (Edge e : graph.getOutEdges(i)) {
                // Evita di aggiungere due volte lo stesso arco
                if (e.getTail() < e.getHead()) {
                    heap.add(e, e.getWeight());
                }
            }
        }
    }

    public int getCost() {
        return cost;
    }

    public UndirectedGraph getMst() {
        return mst;
    }
}