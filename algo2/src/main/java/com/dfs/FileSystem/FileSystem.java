package com.dfs.FileSystem;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;
import java.util.Stack;

public class FileSystem {

    private Boolean hasCycle;
    private int sorgente;
    private DirectedGraph grafo;

    public FileSystem(int sorgente, DirectedGraph grafo) {
        this.hasCycle = false;
        this.sorgente = sorgente;
        this.grafo = grafo;
    }

    private void DFS(int sorgente){
        ArrayList<Integer> scoperti = new ArrayList<>();
        scoperti.add(sorgente);
        Stack<Integer> pila = new Stack<>();
        pila.push(sorgente);
        while(!pila.isEmpty()){
            int u = pila.pop();
            for(Edge e: grafo.getOutEdges(u)){
                if(!scoperti.contains(e.getHead())){
                    scoperti.add(e.getHead());
                    pila.push(e.getHead());
                }
                else if(pila.contains(e.getHead())) hasCycle=true;  //se incontro un nodo già visitato ed è ancora nella pila allora c'è un ciclo
            }
        }
    }

    public Boolean HasCycle() {
        return hasCycle;
    }
}
