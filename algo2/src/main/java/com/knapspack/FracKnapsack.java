package com.knapspack;

import it.uniupo.algoTools.*;

import java.util.Arrays;

public class FracKnapsack {
    int capacity;
    double[] volume;
    double[] valore;
    double[] dose;
    int oggetti;
    double[] quantity;
    double tot;


    public FracKnapsack(int capacity, double[] volume, double[] valore) {
        this.capacity = capacity;
        this.volume = volume;
        this.valore = valore;
        this.oggetti = volume.length;
        this.quantity = new double[oggetti];
        this.dose =  new double[oggetti];
        this.tot = 0;
        frackKnap();

    }

    private void frackKnap(){
        MaxHeap<Integer, Double> heap = new MaxHeap<>();
        Arrays.fill(dose, 0);   //figo :)
        for(int i=0; i<oggetti; i++){
            heap.add(i, valore[i]/volume[i]);
        }
        double spazioRes = capacity;
        //-----------------------inizializzazione---------------------------//
        while(spazioRes > 0 && !heap.isEmpty()){
            int i = heap.extractMax();
            if(spazioRes >= volume[i]){
                dose[i] = 1;
                quantity[i] = volume[i];
                tot+=valore[i];
            }
            else{
                dose[i] = spazioRes/volume[i];
                quantity[i] = spazioRes;
                tot+=valore[i]*dose[i];
            }
            spazioRes -= volume[i];
        }

    }

    public double maxVal() {
        return tot;
    }

    public double getDose(int i) {
        return dose[i];
    }

    public boolean more(int i, int j){
        return quantity[i] > quantity[j];
    }
}
