package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Pazienti.Pazienti.Record;
import model.Pazienti.Visita;
import model.Pazienti.Visita.Parametro;
import model.Personale.Infermiere;
import model.Personale.Medico;
import model.Personale.PersonaleSanitario;

import static model.Pazienti.Pazienti.RecordManager.*;

/**
 * Controller per l'inserimento delle visite dei pazienti.
 * Gestisce l'interfaccia per l'aggiunta di visite e la validazione dei dati.
 */
public class Visit_insert_controller {

    @FXML
    private Spinner<Double> Temp; // Spinner per la temperatura

    @FXML
    private ChoiceBox<Parametro.Livelli> Press; // ChoiceBox per la pressione

    @FXML
    private ChoiceBox<Parametro.Livelli> cap_pol; // ChoiceBox per la capacità polmonare

    @FXML
    private TextField id_paz; // Campo per l'ID del paziente

    @FXML
    private Spinner<Double> peso; // Spinner per il peso

    @FXML
    private Spinner<Double> Altezza; // Spinner per l'altezza

    @FXML
    private Button insertVisit; // Pulsante per inserire la visita

    /**
     * Inizializza i componenti dell'interfaccia e le impostazioni iniziali per gli spinner e choice box.
     */
    @FXML
    private void initialize() {
        // Imposta i limiti per lo spinner del peso
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 300.0);
        peso.setValueFactory(valueFactory);

        // Imposta i limiti per lo spinner della temperatura
        SpinnerValueFactory<Double> valueFactory1 = new SpinnerValueFactory.DoubleSpinnerValueFactory(36.0, 42.0);
        Temp.setValueFactory(valueFactory1);

        // Imposta i limiti per lo spinner dell'altezza
        SpinnerValueFactory<Double> valueFactory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 2.0, 0.0, 0.1);
        Altezza.setValueFactory(valueFactory2);

        // Aggiunge i livelli di pressione e capacità polmonare al rispettivo ChoiceBox
        Press.getItems().addAll(Parametro.Livelli.ALTA, Parametro.Livelli.MEDIA, Parametro.Livelli.BASSA);
        cap_pol.getItems().addAll(Parametro.Livelli.ALTA, Parametro.Livelli.MEDIA, Parametro.Livelli.BASSA);
    }

    /**
     * Gestisce l'evento di inserimento della visita.
     *
     */
    @FXML
    private void handleInsertVisit(ActionEvent event) {
        Record persona = getRecord(id_paz.getText()); // Ottiene il record del paziente

        if (persona == null) {
            // Mostra un messaggio di errore se il paziente non è trovato
            Alert alert = new Alert(Alert.AlertType.ERROR, "Utente non trovato, inserire l'id corretto.");
            alert.showAndWait();
        } else {
            // Verifica che tutti i campi siano stati compilati
            if (Temp.getValue() != null && Press.getValue() != null && cap_pol.getValue() != null && peso.getValue() != null) {
                Visita nuovaVisita = new Visita(); // Crea una nuova visita
                // Imposta i valori della visita
                nuovaVisita.setValori(Parametro.TEMPERATURA, Temp.getValue());
                nuovaVisita.setValori(Parametro.PESO, peso.getValue());
                nuovaVisita.setValori(Parametro.PRESSIONE, Press.getSelectionModel().getSelectedItem());
                nuovaVisita.setValori(Parametro.CAPACITA_POLMONARE, cap_pol.getSelectionModel().getSelectedItem());
                nuovaVisita.setValori(Parametro.ALTEZZA, Altezza.getValue());

                // Verifica se l'utente è un personale sanitario
                if (homeController.User instanceof Medico || homeController.User instanceof Infermiere) {
                    ((PersonaleSanitario) homeController.User).eseguiVisita(persona, nuovaVisita); // Esegue la visita

                    // Mostra un messaggio di conferma
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Visita registrata correttamente!" + nuovaVisita);
                    alert.setResizable(true);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                } else {
                    // Mostra un messaggio di avviso se l'utente non è loggato
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Effettua prima il login.");
                    alert.setResizable(true);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                    alert.showAndWait();
                }
            } else {
                // Mostra un messaggio di errore se i dati non sono stati inseriti correttamente
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inserire correttamente i dati");
                alert.showAndWait();
            }
        }
    }
}
