package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pazienti.Pazienti.Record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.Pazienti.Pazienti.RecordManager.*;

/**
 * Controller per la ricerca dei pazienti in base all'età.
 * Permette di cercare pazienti che rientrano in un intervallo di anni di nascita.
 */
public class ricercaEta_controller {

    /**
     * Spinner per l'impostazione dell'anno di nascita minimo.
     */
    @FXML
    private Spinner<Integer> min;

    /**
     * Spinner per l'impostazione dell'anno di nascita massimo.
     */
    @FXML
    private Spinner<Integer> max;

    /**
     * Bottone per eseguire la ricerca.
     */
    @FXML
    private Button Ricerca;

    /**
     * Area di testo per visualizzare i risultati della ricerca.
     */
    @FXML
    private TextArea res;

    /**
     * Inizializza i controlli del pannello, impostando i valori degli spinner
     * per gli anni di nascita minimo e massimo.
     */
    @FXML
    private void initialize() {
        // Imposta il valore iniziale dello spinner per l'anno minimo
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, LocalDate.now().getYear());
        min.setValueFactory(valueFactory);

        // Imposta il valore iniziale dello spinner per l'anno massimo
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, LocalDate.now().getYear());
        max.setValueFactory(valueFactory1);
    }

    /**
     * Gestisce l'azione di ricerca dei pazienti in base all'età.
     * Aggiunge i record che rientrano nell'intervallo specificato agli
     * risultati della ricerca e li visualizza nell'area di testo.
     */
    @FXML
    private void handleRicerca(ActionEvent event) {
        List<Record> risultatiRicerca = new ArrayList<>();

        // Cerca i record dei pazienti che rientrano nell'intervallo di anni specificato
        for (Record R : listaUtenti) {
            if (min.getValue() <= R.getAnnoN() && R.getAnnoN() <= max.getValue()) {
                risultatiRicerca.add(R);
            }
        }

        // Mostra un avviso se non ci sono risultati
        if (risultatiRicerca.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nessun risultato per la ricerca");
            alert.showAndWait();
        } else {
            // Visualizza i risultati della ricerca nell'area di testo
            res.setText(risultatiRicerca.toString());
        }
    }
}
