package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Pazienti.Pazienti.Record;

import static model.Pazienti.Pazienti.RecordManager.*;

/**
 * Controller per l'inserimento dei record dei pazienti.
 * Gestisce l'interazione dell'utente per registrare un nuovo paziente.
 */
public class Record_insert_controller {

    /**
     * Campo di testo per l'inserimento dell'ID del paziente.
     */
    @FXML
    private TextField ID;

    /**
     * Campo di testo per l'inserimento dell'anno di nascita del paziente.
     */
    @FXML
    private TextField annoN;

    /**
     * Selettore di data per l'inserimento della data di registrazione.
     */
    @FXML
    private DatePicker dataR;

    /**
     * Bottone per confermare l'inserimento del nuovo paziente.
     */
    @FXML
    private Button insertPaziente;

    /**
     * Gestisce l'azione di inserimento di un nuovo paziente.
     * Verifica i dati forniti e crea un nuovo record di paziente.
     * Mostra un messaggio di conferma o di errore in base all'esito dell'operazione.
     */
    @FXML
    private void handleInsertPaziente(ActionEvent event) {
        Record nuovoPaziente;
        if(!annoN.getText().isBlank()){
            if(!ID.getText().isBlank()){
                if(dataR.getValue() != null){
                    nuovoPaziente = new Record(ID.getText(),Integer.parseInt(annoN.getText()), dataR.getValue());

                }
                else{
                    nuovoPaziente = new Record(ID.getText(),Integer.parseInt(annoN.getText()));

                }
            }
            else{
                nuovoPaziente = new Record(Integer.parseInt(annoN.getText()));
            }
            listaUtenti.add(nuovoPaziente);
            salva(nuovoPaziente);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Registrazione effettuata!\nDati dell'utente:"+nuovoPaziente);
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
