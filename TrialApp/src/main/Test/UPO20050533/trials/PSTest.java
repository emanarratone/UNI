package UPO20050533.trials;

import model.Personale.Medico;
import model.Personale.PersonaleSanitario;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import model.Personale.PersonaleSanitario.Reparto;

import static org.junit.jupiter.api.Assertions.*;

public class PSTest {

    PersonaleSanitario personaleSanitario;

    @BeforeEach
    void setUp() {
        // Usa la classe Medico per testare i metodi di PersonaleSanitario
        personaleSanitario = new Medico("Mario", "Rossi", LocalDate.of(1980, 5, 20),
                LocalDate.of(2020, 1, 15), Reparto.Cardiologia, "password123", "Cardiologo");
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("Mario", personaleSanitario.getNome());
        assertEquals("Rossi", personaleSanitario.getCognome());
        assertEquals(LocalDate.of(1980, 5, 20), personaleSanitario.getDataN());
        assertEquals(LocalDate.of(2020, 1, 15), personaleSanitario.getDataAss());
        assertEquals(Reparto.Cardiologia, personaleSanitario.getReparto());
        assertEquals("password123", personaleSanitario.getPassword());

        // Test dei setter
        personaleSanitario.setNome("Luigi");
        assertEquals("Luigi", personaleSanitario.getNome());

        personaleSanitario.setCognome("Bianchi");
        assertEquals("Bianchi", personaleSanitario.getCognome());

        personaleSanitario.setDataN(LocalDate.of(1985, 6, 15));
        assertEquals(LocalDate.of(1985, 6, 15), personaleSanitario.getDataN());

        personaleSanitario.setDataAss(LocalDate.of(2021, 2, 5));
        assertEquals(LocalDate.of(2021, 2, 5), personaleSanitario.getDataAss());

        personaleSanitario.setReparto(Reparto.Pediatria);
        assertEquals(Reparto.Pediatria, personaleSanitario.getReparto());

        personaleSanitario.setPassword("newpass456");
        assertEquals("newpass456", personaleSanitario.getPassword());
    }


}
