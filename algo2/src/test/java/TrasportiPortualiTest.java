import com.msi.TrasportiPortuali;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TrasportiPortualiTest {

    @Test
    public void create(){
        TrasportiPortuali tp = new TrasportiPortuali(new int[]{1, 3, 2});
        assertNotNull(tp);
    }

    @Test
    public void testAccettareCarico(){
        int[] guadagno = new int[]{2, 4, 12, 18, 7, 3, 8};
        TrasportiPortuali tp = new TrasportiPortuali(guadagno);
        assertTrue(tp.accettaCarico(1));
        assertFalse(tp.accettaCarico(5));
        assertFalse(tp.accettaCarico(-3));
        assertFalse(tp.accettaCarico(5));

    }

}
