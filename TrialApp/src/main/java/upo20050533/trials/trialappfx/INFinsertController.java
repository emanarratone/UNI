package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Personale.Infermiere;
import model.Personale.PersonaleSanitario;


/**
 * Controller per l'inserimento di un nuovo personale sanitario (infermiere).
 * Gestisce la registrazione delle informazioni dell'infermiere e le interazioni con l'interfaccia utente.
 */
public class INFinsertController {

    /**
     * Campo di testo per inserire il cognome del personale sanitario.
     */
    @FXML
    private TextField PSICognome;

    /**
     * Campo di testo per inserire il nome del personale sanitario.
     */
    @FXML
    private TextField PSINome;

    /**
     * Selettore di data per la data di assunzione del personale sanitario.
     */
    @FXML
    private DatePicker PSIdataAss;

    /**
     * Selettore di data per la data di nascita del personale sanitario.
     */
    @FXML
    private DatePicker PSIdataN;

    /**
     * Pulsante per confermare l'inserimento del personale sanitario.
     */
    @FXML
    private Button PSInsert;

    /**
     * Scelta per selezionare il reparto del personale sanitario.
     */
    @FXML
    private ChoiceBox<PersonaleSanitario.Reparto> PSIrep;

    /**
     * Campo per inserire la password del personale sanitario.
     */
    @FXML
    private PasswordField psw;

    /**
     * Inizializza i componenti dell'interfaccia utente.
     * Aggiunge i reparti disponibili al campo di selezione.
     */
    @FXML
    private void initialize(){
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Pediatria);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Cardiologia);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Chirurgia);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.Dermatologia);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.ProntoSoccorso);
        PSIrep.getItems().add(PersonaleSanitario.Reparto.TerapiaIntensiva);
    }

    /**
     * Gestisce l'azione di inserimento di un nuovo personale sanitario.
     * Controlla che tutti i campi siano compilati correttamente, crea un nuovo oggetto Infermiere
     * e visualizza un messaggio di conferma o errore.
     *
     */
    @FXML
    void handleINFInsert(ActionEvent event) {
        if(!PSINome.getText().isBlank() && !PSICognome.getText().isBlank() && PSIdataN.getValue() != null
                && PSIdataAss.getValue() != null && PSIrep.getSelectionModel().getSelectedItem() != null && !psw.getText().isBlank()) {

            Infermiere NewPS = new Infermiere(PSINome.getText(), PSICognome.getText(), PSIdataN.getValue(),
                    PSIdataAss.getValue(), PSIrep.getSelectionModel().getSelectedItem(), psw.getText());
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

