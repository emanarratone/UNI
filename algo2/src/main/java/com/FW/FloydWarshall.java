package com.FW;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.Objects;

public class FloydWarshall {

    DirectedGraph graph;
    int[][] distanze;
    boolean negCycle;
    int n;

    public FloydWarshall(DirectedGraph graph, int n) {
        this.graph = graph;
        this.n = n;
        this.distanze = new int[n][n];
        this.negCycle = false;
        fWarshall();
    }

    private void fWarshall() {
        initSol();
        for(int k = 0;  k < n; k++) {
            for(int u = 0; u < n; u++) {
                for(int v = 0; v < n; v++) {
                    if(distanze[u][k] != Integer.MAX_VALUE && distanze[k][v] != Integer.MAX_VALUE){
                        distanze[u][v] = Math.min(distanze[u][v], distanze[u][k]+distanze[k][v]);
                    }
                }
            }
        }
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                if(distanze[x][y] < 0){
                    negCycle = true;
                    break;
                }
            }
        }
    }

    private void initSol() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(graph.hasEdge(i, j)) distanze[i][j] = Objects.requireNonNull(getEdge(i, j)).getWeight();
                else if(i == j) distanze[i][j] = 0;
                else distanze[i][j] = Integer.MAX_VALUE;

            }
        }
    }

    private Edge getEdge(int i, int j) {
        for (Edge e: graph.getOutEdges(i)) {
            if(e.getHead() == j) return e;
        }
        return null;
    }

    public int getDist(int i, int j){
        if(negCycle) return Integer.MIN_VALUE;
        else return distanze[i][j];
    }

    public boolean isNegCycle() {
        return negCycle;
    }
}
