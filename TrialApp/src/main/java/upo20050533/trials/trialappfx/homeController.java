package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Controller per la home page dell'applicazione.
 * Gestisce le interazioni dell'utente e il passaggio tra diverse schermate.
 */
public class homeController {

    /**
     * Pulsante per eliminare un record.
     */
    @FXML
    private Button Del;

    /**
     * Pulsante per effettuare una ricerca.
     */
    @FXML
    private Button Ricerca;

    /**
     * Pulsante per salvare i dati.
     */
    @FXML
    private Button Save;

    /**
     * Pulsante per visualizzare le statistiche.
     */
    @FXML
    private Button Statistiche;

    /**
     * Pulsante per inserire un record.
     */
    @FXML
    private Button recordInsert;

    /**
     * Pulsante per cercare per età.
     */
    @FXML
    private Button ricercaEta;

    /**
     * Pannello principale dell'interfaccia utente.
     */
    @FXML
    private Pane Pane;

    /**
     * Oggetto statico utile per determinare il tipo di utente (medico o infermiere) a runtime.
     */
    static Object User;

    /**
     * Gestisce l'azione per inserire un record.
     * Viene chiamato quando l'utente clicca sul pulsante per inserire un record.
     */
    @FXML
    private void handleRecord(ActionEvent event){
        showPanel("Record_insert.fxml");
    }

    /**
     * Gestisce l'azione per effettuare una ricerca.
     * Viene chiamato quando l'utente clicca sul pulsante per cercare.
     *
     */
    @FXML
    private void handleRicerca(ActionEvent event){
        showPanel("ricerca.fxml");
    }

    /**
     * Gestisce l'azione per effettuare una ricerca basata sull'età.
     * Viene chiamato quando l'utente clicca sul pulsante per cercare per età.
     *
     */
    @FXML
    private void handleRicercaEta(ActionEvent event){
        showPanel("ricerca_eta.fxml");
    }

    /**
     * Gestisce l'azione per visualizzare le statistiche.
     * Viene chiamato quando l'utente clicca sul pulsante per visualizzare le statistiche.
     *
     */
    @FXML
    private void handleStatistiche(ActionEvent event){
        showPanel("Statistiche.fxml");
    }

    /**
     * Gestisce l'azione per visualizzare il pannello del personale.
     * Viene chiamato quando l'utente clicca sul pulsante per accedere alla schermata del personale.
     *
     */
    @FXML
    private void handlePers(ActionEvent event){
        showPanel("PSHome.fxml");
    }

    /**
     * Mostra il pannello specificato.
     * Carica il file FXML e sostituisce il contenuto del pannello corrente.
     *
     * @param root Il nome del file FXML da caricare
     */
    @FXML
    private void showPanel(String root) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(root));
            Node inserisciPazientePanel = loader.load();
            Pane.getChildren().setAll(inserisciPazientePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
