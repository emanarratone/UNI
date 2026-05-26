package com.kruskal;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.QuickFind;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class TipologiaClienti {

    UndirectedGraph dati;

    public TipologiaClienti(UndirectedGraph dati) {
        this.dati = dati;
    }

    public int numeroTipologie(int differenzaRichiesta){
        MinHeap<Edge, Integer> heap = new MinHeap<>();
        UnionFind uf = new QuickFind(dati.getOrder());
        buildHeap(heap);
        uf.create(dati.getOrder());
        int nCluster = uf.getNumberOfSets();
        while (!heap.isEmpty()){
            Edge e = heap.extractMin();
            nCluster = uf.getNumberOfSets();    //<--------------
            if(e.getWeight() >= differenzaRichiesta){
                break;
            }
            else if(uf.find(e.getTail()) != uf.find(e.getHead())){
                uf.union(uf.find(e.getTail()), uf.find(e.getHead()));
            }
        }
        return nCluster;
    }

    private void buildHeap(MinHeap<Edge, Integer> heap){
        for(int u = 0; u < dati.getOrder(); u++){
            for(Edge e: dati.getOutEdges(u)){
                if(e.getTail() < e.getHead()){
                    heap.add(e, e.getWeight());
                }
            }
        }
    }
}
