package upo20050533.trials.trialappfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * La classe principale dell'applicazione TrialApp.
 * Estende la classe Application di JavaFX e gestisce l'avvio dell'applicazione.
 */
public class TrialAppApplication extends Application {

    /**
     * Metodo principale per avviare l'applicazione.
     * Carica l'interfaccia utente dal file FXML e imposta le proprietà della finestra principale.
     *
     * @param stage La finestra principale dell'applicazione
     * @throws IOException Se si verifica un errore durante il caricamento del file FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carica l'icona desiderata
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon.png")));
        // Imposta l'icona per lo stage
        stage.getIcons().add(icon);

        // Carica il layout FXML
        FXMLLoader fxmlLoader = new FXMLLoader(TrialAppApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Imposta il titolo e la scena della finestra
        stage.setTitle("TrialApp");
        stage.setScene(scene);
        stage.setResizable(false); // Imposta la finestra come non ridimensionabile
        stage.show(); // Mostra la finestra
    }

    /**
     * Punto di ingresso per l'applicazione.
     *
     * @param args Argomenti della riga di comando
     */
    public static void main(String[] args) {
        launch(); // Avvia l'applicazione JavaFX
    }
}
