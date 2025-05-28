package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Personale.Medico;
import model.Personale.PersonaleSanitario;
/**
 * Controller per la registrazione di un nuovo personale sanitario (medico).
 * Gestisce l'inserimento dei dati richiesti per la registrazione.
 */
public class PSInsertController {

    /**
     * Campo di testo per il cognome del personale sanitario.
     */
    @FXML
    private TextField PSICognome;

    /**
     * Campo di testo per il nome del personale sanitario.
     */
    @FXML
    private TextField PSINome;

    /**
     * DatePicker per la data di assunzione del personale sanitario.
     */
    @FXML
    private DatePicker PSIdataAss;

    /**
     * DatePicker per la data di nascita del personale sanitario.
     */
    @FXML
    private DatePicker PSIdataN;

    /**
     * Bottone per confermare l'inserimento del personale sanitario.
     */
    @FXML
    private Button PSInsert;

    /**
     * ChoiceBox per selezionare il reparto del personale sanitario.
     */
    @FXML
    private ChoiceBox<PersonaleSanitario.Reparto> PSIrep;

    /**
     * Campo di testo per la specializzazione del personale sanitario.
     */
    @FXML
    private TextField spec;

    /**
     * Campo di testo per la password del personale sanitario.
     */
    @FXML
    private PasswordField psw;

    /**
     * Inizializza la ChoiceBox con i reparti disponibili.
     */
    @FXML
    private void initialize() {
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Pediatria);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Cardiologia);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Chirurgia);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Dermatologia);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.ProntoSoccorso);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.TerapiaIntensiva);
    }

    /**
     * Gestisce l'azione di inserimento del nuovo personale sanitario.
     * Controlla che tutti i campi siano compilati correttamente prima di creare un nuovo oggetto Medico.
     * Mostra un alert di conferma o di errore in base all'esito dell'operazione.
     */
    @FXML
    void handlePSInsert(ActionEvent event) {

        if(!PSINome.getText().isBlank() && !PSICognome.getText().isBlank() && PSIdataN.getValue() != null
                && PSIdataAss.getValue() != null && PSIrep.getSelectionModel().getSelectedItem() != null && !spec.getText().isBlank() && !psw.getText().isBlank()) {
            Medico NewPS = new Medico(PSINome.getText(), PSICognome.getText(), PSIdataN.getValue(),
                    PSIdataAss.getValue(), PSIrep.getSelectionModel().getSelectedItem(), psw.getText(), spec.getText());
            NewPS.salvaP();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Registrazione effettuata!\nDati dell'utente:"+ NewPS);
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inserire correttamente i dati.");
                alert.showAndWait();
        }
    }
}


