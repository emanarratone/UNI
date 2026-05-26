package com.knapspack;

public class Knapsack {

    int capacity;
    int[] volume;
    int[] valore;
    int [][] soluzione;
    int oggetti;
    int tot;


    public Knapsack(int capacity, int[] volume, int[] valore) {
        this.capacity = capacity;
        this.volume = volume;
        this.valore = valore;
        this.oggetti = volume.length;
        this.tot = 0;
        this.soluzione = new int[oggetti+1][capacity+1];
        KnapIt();
    }

    private void KnapIt() {
        for(int cap = 0; cap < capacity; cap++){
            soluzione[0][cap] = 0;
        }
        for(int n = 1; n <= oggetti; n++){
            int id = n-1;
            for(int c = 0; c <= capacity; c++){
                if(volume[id] <= c){
                    soluzione[n][c] = Math.max(soluzione[n-1][c-volume[id]]+valore[id], soluzione[n-1][c]);
                }
                else soluzione[n][c] = soluzione[n-1][c];
            }
        }

    }

    public int getMaxVal(){
        return soluzione[oggetti][capacity];
    }

}
