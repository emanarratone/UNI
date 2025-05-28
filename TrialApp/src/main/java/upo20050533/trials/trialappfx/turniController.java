package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import model.Personale.Infermiere;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Controller per la gestione dei turni degli infermieri.
 * Consente agli utenti di selezionare i turni per ogni giorno della settimana.
 */
public class turniController {

    @FXML
    private ChoiceBox<String> D; // Turno per Domenica

    @FXML
    private RadioButton DF; // RadioButton per ferie Domenica

    @FXML
    private ChoiceBox<String> G; // Turno per Giovedì

    @FXML
    private RadioButton GF; // RadioButton per ferie Giovedì

    @FXML
    private ChoiceBox<String> L; // Turno per Lunedì

    @FXML
    private RadioButton LF; // RadioButton per ferie Lunedì

    @FXML
    private ChoiceBox<String> MA; // Turno per Martedì

    @FXML
    private RadioButton MAF; // RadioButton per ferie Martedì

    @FXML
    private ChoiceBox<String> ME; // Turno per Mercoledì

    @FXML
    private RadioButton MEF; // RadioButton per ferie Mercoledì

    @FXML
    private ChoiceBox<String> S; // Turno per Sabato

    @FXML
    private RadioButton SF; // RadioButton per ferie Sabato

    @FXML
    private ChoiceBox<String> V; // Turno per Venerdì

    @FXML
    private RadioButton VF; // RadioButton per ferie Venerdì

    @FXML
    private Button submit; // Pulsante per inviare i turni

    /**
     * Inizializza i ChoiceBox per i turni con le opzioni disponibili.
     */
    @FXML
    private void initialize() {
        List<String> turni = Arrays.asList("Mattutino", "Pomeridiano", "Serale", "Notturno");

        // Aggiunge le opzioni di turno a ciascun ChoiceBox
        L.getItems().addAll(turni);
        MA.getItems().addAll(turni);
        ME.getItems().addAll(turni);
        G.getItems().addAll(turni);
        V.getItems().addAll(turni);
        S.getItems().addAll(turni);
        D.getItems().addAll(turni);
    }

    /**
     * Gestisce l'evento di invio dei turni selezionati.
     *
     * @param event L'evento di azione
     */
    @FXML
    void HandleTurni(ActionEvent event) {
        Infermiere inf = (Infermiere) homeController.User; // Ottiene l'oggetto Infermiere attualmente loggato
        inf.setTurni(makeTurni()); // Imposta i turni per l'infermiere
        inf.salvaP(); // Salva le informazioni aggiornate dell'infermiere
        homeController.User = inf; // Aggiorna l'oggetto User
    }

    /**
     * Crea un mappa dei turni selezionati, considerando se l'infermiere è in ferie.
     *
     * @return Una mappa con i giorni della settimana come chiavi e i turni come valori
     */
    private HashMap<String, String> makeTurni() {
        HashMap<String, String> turni = new HashMap<>();

        // Assegna i turni per ciascun giorno, tenendo conto delle ferie
        if (!LF.isSelected()) turni.put("Lunedì", L.getSelectionModel().getSelectedItem());
        else turni.put("Lunedì", "Ferie");

        if (!MAF.isSelected()) turni.put("Martedì", MA.getSelectionModel().getSelectedItem());
        else turni.put("Martedì", "Ferie");

        if (!MEF.isSelected()) turni.put("Mercoledì", ME.getSelectionModel().getSelectedItem());
        else turni.put("Mercoledì", "Ferie");

        if (!GF.isSelected()) turni.put("Giovedì", G.getSelectionModel().getSelectedItem());
        else turni.put("Giovedì", "Ferie");

        if (!VF.isSelected()) turni.put("Venerdì", V.getSelectionModel().getSelectedItem());
        else turni.put("Venerdì", "Ferie");

        if (!SF.isSelected()) turni.put("Sabato", S.getSelectionModel().getSelectedItem());
        else turni.put("Sabato", "Ferie");

        if (!DF.isSelected()) turni.put("Domenica", D.getSelectionModel().getSelectedItem());
        else turni.put("Domenica", "Ferie");

        return turni; // Ritorna la mappa dei turni
    }
}
