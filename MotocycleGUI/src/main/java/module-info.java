module pl.polsl.motocyclegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pl.polsl.motocyclegui to javafx.fxml;
    exports pl.polsl.motocyclegui;
    exports controller;
    exports model;
}
