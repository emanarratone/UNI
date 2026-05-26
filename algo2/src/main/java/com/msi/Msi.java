package com.msi;

import it.uniupo.graphLib.UndirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Msi {
    int[] peso;
    int A[];
    ArrayList<Integer> sol;

    public Msi(int[] peso) {
        this.peso = peso;
        this.A = new int[peso.length];
        this.sol = new ArrayList<>();
        MSI();
    }

    private void MSI(){
        A[0] = peso[0];
        A[1] = Math.max(peso[0], peso[1]);
        for(int i=2; i<peso.length; i++) {
            A[i] = Math.max(A[i - 1], A[i - 2] + peso[i]);
        }
        solution();
    }

    private void solution(){
        int i = peso.length-1;

        while(i >= 1) {
            if (A[i] > A[i - 1]) {
                sol.add(i);
                i -= 2;
            }
            else i--;
        }
    }
    public ArrayList<Integer> getSoluzione(){
        return sol;
    }

    public int getMaxVal(){
        int res = 0;
        for(int i : sol) {
            res += peso[i];
        }
        return res;
    }

}
