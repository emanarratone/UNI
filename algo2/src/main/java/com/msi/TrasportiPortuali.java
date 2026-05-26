package com.msi;

import java.util.ArrayList;

public class TrasportiPortuali {

    int[] guadagno;
    int[] A;
    ArrayList<Integer> sol;

    public TrasportiPortuali(int[] guadagno) {
        this.guadagno = guadagno;
        this.A = new int[guadagno.length];
        this.sol = new ArrayList<>();
        MSI();
    }

    private void MSI(){
        A[0] = guadagno[0];
        A[1] = Math.max(guadagno[0], guadagno[1]);
        for(int i = 2; i<guadagno.length; i++){
            A[i] = Math.max(A[i-1], A[i-2]+guadagno[i]);
        }
        solution();
    }

    private void solution(){
        int i = guadagno.length-1;
        while(i >= 1){
            if(A[i] > A[i-1]){
                sol.add(i);
                i-=2;
            }
            else i--;
        }
    }

    public boolean accettaCarico(int s){
        return sol.contains(s);
    }
}
