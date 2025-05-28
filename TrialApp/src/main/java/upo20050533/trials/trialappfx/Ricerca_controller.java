package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Pazienti.Pazienti.Record;

import static model.Pazienti.Pazienti.RecordManager.*;

/**
 * Controller per la ricerca dei record dei pazienti.
 * Gestisce l'interazione dell'utente per cercare un paziente tramite ID.
 */
public class Ricerca_controller {

    /**
     * Campo di testo per l'inserimento dell'ID del paziente da cercare.
     */
    @FXML
    private TextField id_paz;

    /**
     * Area di testo per visualizzare i risultati della ricerca.
     */
    @FXML
    private TextArea res;

    /**
     * Bottone per eseguire la ricerca del paziente.
     */
    @FXML
    private Button ricerca;

    /**
     * Gestisce l'azione di ricerca di un paziente tramite ID.
     * Se il paziente viene trovato, i dettagli vengono visualizzati nell'area di testo.
     * Se il paziente non viene trovato, viene mostrato un messaggio di avviso.
     */
    @FXML
    private void handleRicerca(ActionEvent event) {
        // Controlla che l'ID non sia vuoto
        if (!id_paz.getText().isEmpty()) {
            Record persona = getRecord(id_paz.getText()); // Recupera il record corrispondente

            // Controlla se il record è stato trovato
            if (persona == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Paziente non trovato, riprova.");
                alert.showAndWait(); // Mostra un avviso se il paziente non è stato trovato
            } else {
                res.setText(persona.toString()); // Visualizza i dettagli del paziente
            }
        }
    }
}
