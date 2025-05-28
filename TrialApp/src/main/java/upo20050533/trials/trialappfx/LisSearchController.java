package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Pazienti.Pazienti.Record;
import model.Personale.Infermiere;
import model.Personale.Medico;
import model.Personale.PersonaleSanitario;

import static model.Pazienti.Pazienti.RecordManager.getRecord;

/**
 * Controller per la ricerca di un record di un paziente.
 * Gestisce l'interazione con l'interfaccia utente per cercare un record basato sull'ID fornito.
 */
public class LisSearchController {

    /**
     * Pulsante per confermare l'azione di ricerca del record.
     */
    @FXML
    private Button Ins;

    /**
     * Campo di testo per inserire l'ID del record da cercare.
     */
    @FXML
    private TextField idInput;

    /**
     * Gestisce l'azione di ricerca di un record dalla lista personale di medici o pazienti.
     * Controlla se l'utente è autenticato e se il record esiste.
     * Se il record viene trovato, viene visualizzato in un messaggio di conferma.
     */
    @FXML
    void handleLisSearch(ActionEvent event) {
        int x = -1;
        if(homeController.User != null){
            if(getRecord(idInput.getText()) != null){

                x = ((PersonaleSanitario) homeController.User).cercaRecord(getRecord(idInput.getText()));
                if(x != -1 ) {
                    Record record;
                    if (homeController.User instanceof Medico) {
                        record = ((Medico) homeController.User).getPazientiSeguiti().get(x);
                    }
                    else{
                        record = ((Infermiere) homeController.User).getPazientiAssegnati().get(x);
                    }
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, record.toString());
                        alert.setResizable(true);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                        alert.show();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Utente inesistente");
                alert.setResizable(true);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                alert.show();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }

    }

}
