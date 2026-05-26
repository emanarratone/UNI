import com.knapspack.Knapsack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnapTest {

    @Test
    public void testCreat(){
        int[] volume = new int[]{1, 2, 3, 4, 5};
        int[] valore = new int[]{10, 25, 40, 45, 70};
        Knapsack knap = new Knapsack(5, volume, valore);
        assertNotNull(knap);
    }

    @Test
    public void testKnapsackExample() {
        int[] volume = {3, 4, 7, 8, 9};
        int[] valore = {4, 5, 10, 11, 13};
        int capacity = 17;

        Knapsack knapsack = new Knapsack(capacity, volume, valore);

        // Valore massimo atteso: 24 (oggetti 4 e 5)
        int expected = 24;
        int actual = knapsack.getMaxVal();

        assertEquals(expected, actual, "Il valore massimo calcolato non è corretto");
    }

    @Test
    public void testKnapsackSmallCase() {
        // Caso semplice: 3 oggetti
        int[] volume = {2, 3, 4};
        int[] valore = {3, 4, 5};
        int capacity = 5;

        /*
         Possibili combinazioni:
         - Oggetto 1 (v=2, val=3)
         - Oggetto 2 (v=3, val=4)
         - Oggetto 3 (v=4, val=5)
         - Oggetti 1+2: v=5, val=7  ✅ migliore
         */

        Knapsack knapsack = new Knapsack(capacity, volume, valore);

        int expected = 7;
        int actual = knapsack.getMaxVal();

        assertEquals(expected, actual, "Il valore massimo calcolato non è corretto nel caso piccolo");
    }
}
