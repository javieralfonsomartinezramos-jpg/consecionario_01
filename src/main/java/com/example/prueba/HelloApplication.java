package com.example.prueba;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(@NotNull Stage stage)
            throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(
                        HelloApplication.class.getResource(
                                "hello-view.fxml"));

        Scene scene =
                UI.crearEscena(
                        fxmlLoader.load()
                );

        stage.setTitle("MOTOSHOP");

        UI.mostrarMaximizado(stage, scene);
    }

    public static void main(String[] args) {
        launch();
    }
}
