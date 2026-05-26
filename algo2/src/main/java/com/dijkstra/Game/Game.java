package com.dijkstra.Game;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

import java.util.ArrayList;

public class Game {

    DirectedGraph gioco;
    int[] configVincenti;
    int[] distanza;

    public Game(DirectedGraph gioco, int[] configVincenti) {
        this.gioco = gioco;
        this.configVincenti = configVincenti;
        this.distanza = new int[gioco.getOrder()];
    }

    private void dijkstra(int sorgente){
        ArrayList<Integer> scoperti = new ArrayList<>();
        MinHeap<Edge, Integer> heap = new MinHeap<>();
        scoperti.add(sorgente);
        distanza[0] = sorgente;
        for(Edge e: gioco.getOutEdges(sorgente)){
            heap.add(e, distanza[sorgente] + 1);
        }
        while(!heap.isEmpty()){
            Edge e = heap.extractMin();
            if(!scoperti.contains(e.getHead())) {
                distanza[e.getHead()] = distanza[e.getTail()] + 1;
                scoperti.add(e.getHead());
            }
            for(Edge ee: gioco.getOutEdges(e.getHead())){
                if(!scoperti.contains(ee.getHead())){
                    heap.add(ee, distanza[ee.getTail()] + 1);
                }
            }
        }
    }

    public int wins(int conf, int player, int maxMoves){
        if(conf > gioco.getOrder() || conf < 0) return -1;

        if(player < 1 || player > 2) return -1;

        if(maxMoves < 0) return -1;

        dijkstra(conf);
        ArrayList<Integer> wins = getWins(player);
        int res = 0;
        for(Integer I: wins){
            if (distanza[I] <= maxMoves) {
                res = 1;
                break;
            }
        }
        return res;

    }

    public ArrayList<Integer> getWins(int player){
        ArrayList<Integer> wins = new ArrayList<>();
        int i= 0;
        while(i < configVincenti.length){
            if(configVincenti[i] == player) wins.add(i);
            i++;
        }
        return wins;
    }
}
