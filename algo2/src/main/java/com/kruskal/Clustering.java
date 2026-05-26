package com.kruskal;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.QuickFind;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Clustering {

    UndirectedGraph graph;
    int spaziamento;
    int k;

    public Clustering(UndirectedGraph graph, int k) {
        this.graph = graph;
        this.k = k;
        this.spaziamento = 0;
        clusterIt();
    }

    private void clusterIt() {
        MinHeap<Edge,Integer> heap = new MinHeap<>();
        UnionFind uf = new QuickFind(graph.getOrder());
        uf.create(graph.getOrder());
        buildHeap(heap);
        while(!heap.isEmpty() && uf.getNumberOfSets() > k) {
            Edge e = heap.extractMin();
            if(uf.find(e.getTail()) != uf.find(e.getHead())){
                uf.union(uf.find(e.getTail()), uf.find(e.getHead()));
            }

        }
        if(!heap.isEmpty()){
            Edge e = heap.extractMin();
            spaziamento = e.getWeight();
        }
    }

    private void clusterIt(UnionFind uf) {
        MinHeap<Edge,Integer> heap = new MinHeap<>();
        uf.create(graph.getOrder());
        buildHeap(heap);
        while(!heap.isEmpty() && uf.getNumberOfSets() > k) {
            Edge e = heap.extractMin();
            if(uf.find(e.getTail()) != uf.find(e.getHead())){
                uf.union(uf.find(e.getTail()), uf.find(e.getHead()));
            }
        }
        if(!heap.isEmpty()){
            Edge e = heap.extractMin();
            spaziamento = e.getWeight();
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

    public int getSpaziamento() {
        return spaziamento;
    }

    public boolean sameCluster(int a, int b) {
        UnionFind uf =  new QuickFind(graph.getOrder());
        uf.create(graph.getOrder());
        clusterIt(uf);
        return uf.find(a) == uf.find(b);
    }
}
