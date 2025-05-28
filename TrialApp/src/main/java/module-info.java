module upo20050533.trials.trialappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens upo20050533.trials.trialappfx to javafx.fxml;
    exports upo20050533.trials.trialappfx;
}