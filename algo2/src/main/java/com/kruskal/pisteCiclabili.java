package com.kruskal;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.QuickFind;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class pisteCiclabili {

    private UndirectedGraph mappaCosti;
    private int[][] tempo;

    private UndirectedGraph mst;

    int costo;

    public pisteCiclabili(UndirectedGraph mappaCosti, int[][] tempo) {
        this.mappaCosti = mappaCosti;
        this.tempo = tempo;
        mst = new UndirectedGraph(mappaCosti.getOrder());
        costo = 0;
    }

    private void kruskal(){
        MinHeap<Edge, Integer> heap = new MinHeap<>();
        UnionFind uf = new QuickFind(mappaCosti.getOrder());
        uf.create(mappaCosti.getOrder());
        buildHeap(heap);

        while(!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(uf.find(e.getTail()) != uf.find(e.getHead())){
                uf.union(uf.find(e.getTail()), uf.find(e.getHead()));
                mst.addEdge(e);
                costo += tempo[e.getTail()][e.getHead()];
            }
        }
    }

    private void buildHeap( MinHeap<Edge, Integer> heap){
        for(int i = 0; i < mappaCosti.getOrder(); i++){
            for(Edge e: mappaCosti.getOutEdges(i)){
                if(e.getTail()<e.getHead()){
                    heap.add(e, e.getWeight());
                }
            }
        }
    }

    public boolean possibileEntro(int maxNumGiorni){
        if(maxNumGiorni < 0) throw new IllegalArgumentException("maxNumGiorni negativo. -10 aura\n");
        mst = new UndirectedGraph(mappaCosti.getOrder());
        costo = 0;
        kruskal();
        return costo <= maxNumGiorni;
    }

}
