package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Personale.PersonaleSanitario;

import static model.Pazienti.Pazienti.RecordManager.getRecord;

/**
 * Controller per la cancellazione di un record di un paziente.
 * Gestisce l'interazione con l'interfaccia utente per eliminare un record basato sull'ID fornito.
 */
public class LisDelController {

    /**
     * Pulsante per confermare la cancellazione del record.
     */
    @FXML
    private Button Ins;

    /**
     * Campo di testo per inserire l'ID del record da cancellare.
     */
    @FXML
    private TextField idInput;

    /**
     * Gestisce l'azione di cancellazione del record dalla lista personale di medici o pazienti.
     * Controlla se l'utente è autenticato, se il record esiste ed
     * effettua la cancellazione se entrambe le condizioni sono soddisfatte.
     *
     */
    @FXML
    void handleLisDel(ActionEvent event) {
        if(homeController.User != null){
            if(getRecord(idInput.getText()) != null){

                ((PersonaleSanitario) homeController.User).cancellaRecord(getRecord(idInput.getText()));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cancellazione effettuata");
                alert.setResizable(true);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                alert.show();
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

