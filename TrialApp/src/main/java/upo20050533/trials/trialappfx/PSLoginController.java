package upo20050533.trials.trialappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Personale.Infermiere;
import model.Personale.Medico;

import java.io.File;
/**
 * Controller per la gestione del login del personale sanitario.
 * Permette ai medici e agli infermieri di effettuare il login nel sistema.
 */
public class PSLoginController {

    /**
     * Campo di testo per l'inserimento dell'ID utente.
     */
    @FXML
    private TextField IDInput;

    /**
     * Bottone per confermare il login.
     */
    @FXML
    private Button loginPS;

    /**
     * Campo di testo per l'inserimento della password.
     */
    @FXML
    private PasswordField pswInput;

    /**
     * Gestisce l'azione di login quando il bottone viene premuto.
     * Verifica se l'ID e la password forniti corrispondono a un utente esistente.
     * Mostra un messaggio di conferma o di errore in base all'esito dell'operazione.
     */
    @FXML
    void handleLogin(ActionEvent event) {
        File pathMedico = new File("./archive/Med/" + IDInput.getText() + ".dat");
        File pathInfermiere = new File("./archive/Inf/" + IDInput.getText() + ".dat");
        if (pathMedico.exists()) {
            Medico med = new Medico();
            med.caricaP(IDInput.getText());
            if(med.getPassword().equals(pswInput.getText())) {
                homeController.User = med;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login Effettuato");
                alert.setResizable(true);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                alert.show();

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password errata.");
                alert.showAndWait();
            }
        }
        else if (pathInfermiere.exists()) {
            Infermiere inf = new Infermiere();
            inf = new Infermiere();
            inf.caricaP(IDInput.getText());
            if(inf.getPassword().equals(pswInput.getText())) {
                homeController.User = inf;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login Effettuato");
                alert.setResizable(true);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
                alert.show();

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password errata.");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID errato o utente inesistente.");
            alert.showAndWait();
        }

    }




}

