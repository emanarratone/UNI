import com.knapspack.FracKnapsack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FrackKnapsackTest {


    @Test
    public void testCreate(){
        double[] volume = new double[]{1, 2, 3, 4, 5};
        double[] valore = new double[]{10, 25, 40, 45, 70};
        FracKnapsack fk = new FracKnapsack(5, volume, valore);
        assertNotNull(fk);
    }

    @Test
    public void oneElement(){
        double[] volume = new double[]{3};
        double[] valore = new double[]{10};
        FracKnapsack fk = new FracKnapsack(7, volume, valore);
        assertEquals(10, fk.maxVal());
        assertEquals(1, fk.getDose(0));
        double[] volume1 = new double[]{4};
        double[] valore1 = new double[]{10};
        FracKnapsack fk1 = new FracKnapsack(2, volume1, valore1);
        assertEquals(5, fk1.maxVal());
        assertEquals(0.5, fk1.getDose(0));
    }

    @Test
    public void threeElements(){
        double[] volume = new double[]{3, 2, 4};
        double[] valore = new double[]{2, 5, 1};
        FracKnapsack fk = new FracKnapsack(10, volume, valore);
        assertEquals(8, fk.maxVal());
        assertEquals(1, fk.getDose(0));
        double[] volume1 = new double[]{70, 12, 3};
        double[] valore1 = new double[]{10, 2, 0.5};
        FracKnapsack fk1 = new FracKnapsack(3, volume1, valore1);
        assertEquals(0.5, fk1.maxVal());
        assertTrue(1 == fk1.getDose(2) || 0.25 == fk1.getDose(1));
        double[] volume2 = new double[]{4, 3, 4};
        double[] valore2 = new double[]{5, 3, 1};
        FracKnapsack fk2 = new FracKnapsack(10, volume2, valore2);
        assertEquals(8.75, fk2.maxVal());
        assertEquals(0.75, fk2.getDose(2));
    }
}
