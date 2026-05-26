import com.msi.Msi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MsiTest {

    @Test
    public void testCreate() {
        int[] pesi = new int[]{1, 2, 3};
        Msi msi = new Msi(pesi);
        assertNotNull(msi);
    }

    @Test
    public void testSoluzioneCorretta() {
        int[] peso = {3, 2, 7, 10};
        Msi msi = new Msi(peso);
        ArrayList<Integer> sol = msi.getSoluzione();

        // In questo caso la somma massima è 13 (3 + 10)
        // Quindi la soluzione dovrebbe contenere i corrispondenti valori scelti
        assertTrue(sol.contains(3) || sol.contains(10));
    }

    @Test
    public void testGetMaxVal() {
        int[] peso = {1, 2, 3, 4};
        Msi msi = new Msi(peso);
        assertEquals(6, msi.getMaxVal());
    }

    @Test
    public void testInputPiccolo() {
        int[] peso = {5, 1};
        assertDoesNotThrow(() -> new Msi(peso));
    }

    @Test
    public void testSoluzioneVuotaConArrayVuoto() {
        int[] peso = {};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new Msi(peso));
    }
}
