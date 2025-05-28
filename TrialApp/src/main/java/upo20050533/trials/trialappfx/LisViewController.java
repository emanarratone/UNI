package upo20050533.trials.trialappfx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import model.Pazienti.Pazienti.Record;
import model.Personale.PersonaleSanitario;

import java.util.List;

/**
 * Controller per la visualizzazione dei record di un paziente.
 * Gestisce l'interazione con l'interfaccia utente per mostrare i record
 * associati all'utente autenticato.
 */
public class LisViewController {

    /**
     * Area di testo utilizzata per visualizzare i record dei pazienti.
     */
    @FXML
    private TextArea outArea;

    /**
     * Inizializza il controller e visualizza i record se l'utente è autenticato.
     * Se l'utente non è autenticato, viene mostrato un messaggio di avviso.
     */
    @FXML
    private void initialize(){
        if(homeController.User != null){
            List<Record> lisRes = ((PersonaleSanitario) homeController.User).visualizzaRecord();
            if(lisRes.isEmpty())outArea.setText("Lista vuota.");
            for(Record R: lisRes){
                outArea.setText(R.toString());
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il Login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

}

