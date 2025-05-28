package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import model.Personale.Infermiere;
import model.Personale.Medico;

import java.io.IOException;

/**
 * Controller per la gestione dell'interfaccia utente della home page per il personale sanitario.
 * Fornisce funzionalità per navigare tra le varie funzionalità dell'applicazione.
 */
public class PSHomeController {

    /**
     * Pane principale dove verranno caricati i contenuti delle varie schermate.
     */
    @FXML
    private AnchorPane ContentPane;

    /**
     * Bottone per effettuare il login.
     */
    @FXML
    private Button Login;

    /**
     * Bottone per inserire un infermiere.
     */
    @FXML
    private Button infIns;

    /**
     * Bottone per eliminare un paziente dalla lista personale.
     */
    @FXML
    private Button lisDel;

    /**
     * Bottone per registrare un paziente dalla lista personale.
     */
    @FXML
    private Button lisIns;

    /**
     * Bottone per visualizzare i pazienti dalla lista personale.
     */
    @FXML
    private Button lisP;

    /**
     * Bottone per cercare un paziente dalla lista personale.
     */
    @FXML
    private Button lisSearch;

    /**
     * Bottone per inserire un medico.
     */
    @FXML
    private Button medIns;

    /**
     * Bottone per gestire gli orari.
     */
    @FXML
    private Button orari;

    /**
     * Bottone per eseguire una visita.
     */
    @FXML
    private Button eseguiVisita;

    /**
     * Gestisce l'azione per eliminare un paziente dalla lista personale.
     *
     */
    @FXML
    void handleLisDel(ActionEvent event) {
        if (homeController.User instanceof Medico || homeController.User instanceof Infermiere) showPanel("LisDel.fxml");
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il Login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    /**
     * Gestisce l'azione per registrare un paziente dalla lista personale.
     *
     */
    @FXML
    void handleLisIns(ActionEvent event) {
        if (homeController.User instanceof Medico || homeController.User instanceof Infermiere) showPanel("LisIns.fxml");
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il Login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    /**
     * Gestisce l'azione per cercare un paziente dalla lista personale.
     *
     */
    @FXML
    void handleLisSearch(ActionEvent event) {
        if (homeController.User instanceof Medico || homeController.User instanceof Infermiere) showPanel("LisSearch.fxml");
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il Login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    /**
     * Gestisce l'azione per visualizzare i pazienti dalla lista personale.
     *
     */
    @FXML
    void handleLisView(ActionEvent event) {
        if (homeController.User instanceof Medico || homeController.User instanceof Infermiere) showPanel("LisView.fxml");
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il Login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    /**
     * Gestisce l'azione per inserire un'infermiere.
     *
     */
    @FXML
    void handleInfIns(ActionEvent event) { showPanel("INFins.fxml"); }

    /**
     * Gestisce l'azione per effettuare il login.
     *
     */
    @FXML
    void handleLog(ActionEvent event) { showPanel("PSLogin.fxml"); }

    /**
     * Gestisce l'azione per inserire un medico.
     *
     */
    @FXML
    void handleMedIns(ActionEvent event) { showPanel("PSInsert.fxml"); }

    /**
     * Gestisce l'azione per eseguire una visita.
     *
     */
    @FXML
    void handleVis(ActionEvent event) {
        if (homeController.User instanceof Medico || homeController.User instanceof Infermiere) showPanel("Visit_insert.fxml");
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il Login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    /**
     * Gestisce l'azione per visualizzare gli orari in base al tipo di personale.
     * Mostra la schermata degli orari per il medico o i turni per l'infermiere.
     * Se non è effettuato il login, mostra un avviso.
     */
    @FXML
    void handleOrari(ActionEvent event) {
        if (homeController.User instanceof Medico) showPanel("Orari.fxml");
        else if (homeController.User instanceof Infermiere) showPanel("Turni.fxml");
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il Login.");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    /**
     * Carica la schermata specificata all'interno del ContentPane.
     *
     * @param root il nome del file FXML da caricare
     */
    @FXML
    private void showPanel(String root) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(root));
            Node inserisciPazientePanel = loader.load();
            ContentPane.getChildren().setAll(inserisciPazientePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
