package com.BF;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.ArrayList;

public class BellmanFord {

    DirectedGraph graph;
    int[][] soluzione;
    int sorgente;
    boolean negCycle;
    int n;

    public BellmanFord(DirectedGraph graph, int sorgente, int n) {
        this.graph = graph;
        this.sorgente = sorgente;
        this.soluzione = new int[n][n];
        this.negCycle = false;
        this.n = n;
        bFord();
    }

    private void bFord() {
        initSol();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                soluzione[i][j] = Math.min(soluzione[i-1][j], getMin(i, j));
            }
        }
        for(int i = 0; i < n; i++){
            if(soluzione[n-1][i] < soluzione[n-2][i]){
                negCycle = true;
                break;
            }
        }
    }

    private void initSol() {
        soluzione[0][sorgente] = 0;
        for(int i = 0; i<graph.getOrder(); i++) {
            if(i != sorgente) soluzione[0][i] = Integer.MAX_VALUE;
        }
    }

    private int getMin(int i, int j) {
        MinHeap<Edge, Integer> min = new MinHeap<>();
        for (int k = 0; k < graph.getOrder(); k++) {
            for (Edge e : graph.getOutEdges(k)) {
                if (e.getHead() == j) min.add(e, e.getWeight() + soluzione[i - 1][k]);
            }
        }
        if (min.isEmpty()) return Integer.MAX_VALUE;
        else {
            Edge e = min.extractMin();
            if(soluzione[i][e.getTail()] == Integer.MAX_VALUE) return e.getWeight()+soluzione[i][j];
            else return e.getWeight() + soluzione[i][e.getTail()];
        }
    }

    public int getDist(int j) {
        if(negCycle) return Integer.MIN_VALUE;
        else return soluzione[n-1][j];
    }

    public boolean isNegCycle() {
        return negCycle;
    }
}
