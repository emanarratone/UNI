package com.dfs;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.ArrayList;
import java.util.Stack;

public class SS {

    private DirectedGraph system;
    ArrayList<Integer> scoperti;
    public SS(DirectedGraph system) {
        this.system = system;
        this.scoperti = new ArrayList<>();
    }

    private void dfs(int sorgente){
        scoperti.add(sorgente);
        Stack<Integer> pila = new Stack<>();
        pila.add(sorgente);
        while(!pila.isEmpty()){
            int u = pila.pop();
            for(Edge e: system.getOutEdges(u)){
                if(!scoperti.contains(e.getHead())){
                    scoperti.add(e.getHead());
                    pila.push(e.getHead());
                }
            }
        }
    }

    public int depends (int s1, int s2){
        if(s1 < 0 || s2 < 0 || s1 >= system.getOrder() || s2 >= system.getOrder()) return -1;
        if(s1 == s2) return -2;
        int res = 0;

        scoperti.clear();
        dfs(s2);

        if(scoperti.contains(s1)) res = 1;

        scoperti.clear();
        dfs(s1);

        if(scoperti.contains(s2)){
            if(res == 1)res = -3;
            else res = 2;
        }
        return res;
    }

}
