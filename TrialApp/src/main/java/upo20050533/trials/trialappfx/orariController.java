package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import model.Personale.Medico;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * Controller per la gestione degli orari di lavoro del medico.
 * Permette di impostare e salvare gli orari settimanali.
 */
public class orariController {

    /**
     * Spinner per l'orario di fine della Domenica.
     */
    @FXML
    private Spinner<LocalTime> DA;     //Domenica Alle

    /**
     * Spinner per l'orario di inizio della Domenica.
     */
    @FXML
    private Spinner<LocalTime> DD;     //Domenica Dalle

    /**
     * Spinner per l'orario di fine del Giovedì.
     */
    @FXML
    private Spinner<LocalTime> GA;

    /**
     * Spinner per l'orario di inizio del Giovedì.
     */
    @FXML
    private Spinner<LocalTime> GD;

    /**
     * Spinner per l'orario di fine del Lunedì.
     */
    @FXML
    private Spinner<LocalTime> LA;

    /**
     * Spinner per l'orario di inizio del Lunedì.
     */
    @FXML
    private Spinner<LocalTime> LD;

    /**
     * Spinner per l'orario di fine del Martedì.
     */
    @FXML
    private Spinner<LocalTime> MAA;

    /**
     * Spinner per l'orario di inizio del Martedì.
     */
    @FXML
    private Spinner<LocalTime> MAD;

    /**
     * Spinner per l'orario di fine del Mercoledì.
     */
    @FXML
    private Spinner<LocalTime> MEA;

    /**
     * Spinner per l'orario di inizio del Mercoledì.
     */
    @FXML
    private Spinner<LocalTime> MED;

    /**
     * Spinner per l'orario di fine del Venerdì.
     */
    @FXML
    private Spinner<LocalTime> SA;

    /**
     * Spinner per l'orario di inizio del Venerdì.
     */
    @FXML
    private Spinner<LocalTime> SD;

    /**
     * Spinner per l'orario di fine del Sabato.
     */
    @FXML
    private Spinner<LocalTime> VA;

    /**
     * Spinner per l'orario di inizio del Sabato.
     */
    @FXML
    private Spinner<LocalTime> VD;

    /**
     * Bottone per inviare gli orari impostati.
     */
    @FXML
    private Button submit;

    /**
     * Radio button per indicare ferie il Lunedì.
     */
    @FXML
    private RadioButton LF;

    /**
     * Radio button per indicare ferie il Martedì.
     */
    @FXML
    private RadioButton MAF;

    /**
     * Radio button per indicare ferie il Mercoledì.
     */
    @FXML
    private RadioButton MEF;

    /**
     * Radio button per indicare ferie il Giovedì.
     */
    @FXML
    private RadioButton GF;

    /**
     * Radio button per indicare ferie il Venerdì.
     */
    @FXML
    private RadioButton VF;

    /**
     * Radio button per indicare ferie il Sabato.
     */
    @FXML
    private RadioButton SF;

    /**
     * Radio button per indicare ferie la Domenica.
     */
    @FXML
    private RadioButton DF;

    /**
     * Inizializza il controller, impostando i valori degli spinner per l'orario
     * con valori compresi tra 00:00 e 23:30 con incrementi di 30 minuti.
     */
    @FXML
    private void initialize(){
        LocalTime minTime = LocalTime.of(0, 0); // 00:00
        LocalTime maxTime = LocalTime.of(23, 30); // 23:30
        int stepMinutes = 30;
        LD.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        LA.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        MAD.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        MAA.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        MED.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        MEA.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        GD.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        GA.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        VD.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        VA.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        SD.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        SA.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        DD.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
        DA.setValueFactory(createTimeValueFactory(minTime, maxTime, stepMinutes));
    }

    /**
     * Crea un factory per il valore dello spinner per gestire l'orario.
     *
     * @param min il valore minimo consentito
     * @param max il valore massimo consentito
     * @param stepMinutes il passo di incremento in minuti
     * @return un SpinnerValueFactory configurato per gestire gli orari
     */
    private SpinnerValueFactory<LocalTime> createTimeValueFactory(LocalTime min, LocalTime max, int stepMinutes) {
        return new SpinnerValueFactory<LocalTime>() {
            {
                setValue(min); // Imposta il valore iniziale
            }

            @Override
            public void decrement(int steps) {
                LocalTime current = getValue();
                LocalTime newValue = current.minusMinutes((long) steps * stepMinutes);
                if (newValue.isBefore(min)) {
                    setValue(min);
                } else {
                    setValue(newValue);
                }
            }

            @Override
            public void increment(int steps) {
                LocalTime current = getValue();
                LocalTime newValue = current.plusMinutes((long) steps * stepMinutes);
                if (newValue.isAfter(max)) {
                    setValue(max);
                } else {
                    setValue(newValue);
                }
            }
        };
    }

    /**
     * Gestisce l'azione di invio degli orari impostati dal medico.
     * Salva gli orari nel profilo del medico.
     *
     */
    @FXML
    void HandleOrari(ActionEvent event) {
        Medico med = (Medico) homeController.User;
        med.setOrari(makeOrari());
        med.salvaP();
        homeController.User = med;
    }

    /**
     * Crea una mappa con gli orari impostati per ciascun giorno della settimana.
     *
     * @return una HashMap contenente gli orari per ogni giorno
     */
    private HashMap<String, String> makeOrari(){
        HashMap<String, String> orari = new HashMap<>();
        if(!LF.isSelected()) orari.put("Lunedì", "Dalle " + LD.getValue() + " alle " + LA.getValue());
        else orari.put("Lunedi", "Ferie");
        if(!MAF.isSelected()) orari.put("Martedì", "Dalle " + MAD.getValue() + " alle " + MAA.getValue());
        else orari.put("Martedì", "Ferie");
        if(!MEF.isSelected()) orari.put("Mercoledì", "Dalle " + MED.getValue() + " alle " + MEA.getValue());
        else orari.put("Mercoledì", "Ferie");
        if(!GF.isSelected()) orari.put("Giovedì", "Dalle " + GD.getValue() + " alle " + GA.getValue());
        else orari.put("Giovedì", "Ferie");
        if(!VF.isSelected()) orari.put("Venerdì", "Dalle " + VD.getValue() + " alle " + VA.getValue());
        else orari.put("Venerdì", "Ferie");
        if(!SF.isSelected()) orari.put("Sabato", "Dalle " + SD.getValue() + " alle " + SA.getValue());
        else orari.put("Sabato", "Ferie");
        if(!DF.isSelected()) orari.put("Domenica", "Dalle " + DD.getValue() + " alle " + DA.getValue());
        else orari.put("Domenica", "Ferie");
        return orari;
    }
}
