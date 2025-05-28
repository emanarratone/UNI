package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Pazienti.Visita.*;
import model.Pazienti.Pazienti.Record;

import static model.Pazienti.Pazienti.RecordManager.*;

/**
 * Controller per la visualizzazione delle statistiche relative ai pazienti.
 * Permette la ricerca di statistiche basate su parametri specifici e
 * la visualizzazione dei risultati ordinati.
 */
public class statisticheController {

    /**
     * Bottone per eseguire la ricerca delle statistiche.
     */
    @FXML
    private Button Cerca;

    /**
     * Scelta dei parametri di ricerca.
     */
    @FXML
    private ChoiceBox<Parametro> Parametri;

    /**
     * Area di testo per visualizzare i risultati ordinati per data.
     */
    @FXML
    private TextArea date_sort;

    /**
     * Campo di testo per l'ID del paziente.
     */
    @FXML
    private TextField id_paz;

    /**
     * Scelta per l'ordinamento dei risultati.
     */
    @FXML
    private ChoiceBox<String> ordine;

    /**
     * Area di testo per visualizzare le statistiche ordinate per valore.
     */
    @FXML
    private TextArea stat_sort;

    /**
     * Inizializza i controlli del pannello, impostando le opzioni per i parametri
     * di ricerca e per l'ordinamento.
     */
    @FXML
    private void initialize() {
        // Aggiunge parametri disponibili alla scelta
        Parametri.getItems().add(Parametro.PESO);
        Parametri.getItems().add(Parametro.CAPACITA_POLMONARE);
        Parametri.getItems().add(Parametro.TEMPERATURA);
        Parametri.getItems().add(Parametro.PRESSIONE);
        Parametri.getItems().add(Parametro.ALTEZZA);

        // Aggiunge opzioni per l'ordinamento

        ordine.getItems().add("Crescente");
        ordine.getItems().add("Decrecsente");
    }

    private boolean getOrdine(){
        return ordine.getSelectionModel().getSelectedItem().equals("Crescente");
    }

    /**
     * Gestisce l'azione di ricerca delle statistiche.
     * Cerca il record del paziente in base all'ID fornito e
     * visualizza le statistiche ordinate per valore e data.
     */
    @FXML
    private void handleRicerca(ActionEvent event) {
        Record R = getRecord(id_paz.getText());

        // Controlla se il paziente esiste
        if (R == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Utente non trovato, riprova");
            alert.showAndWait();
        } else {
            // Verifica se i parametri e l'ordinamento sono stati selezionati
            if (Parametri.getValue() != null && ordine.getValue() != null) {
                // Visualizza i risultati ordinati per valore e data
                if(byValues(R, Parametri.getValue(), getOrdine()) != null || byData(R, getOrdine()) != null){
                    stat_sort.setText(byValues(R, Parametri.getValue(), getOrdine()).toString());
                    date_sort.setText(byData(R, getOrdine()).toString());
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Ancora nessuna visita registrata per questo utente.");
                    alert.setResizable(true);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inserire correttamente i dati.");
                alert.showAndWait();
            }
        }
    }
}
