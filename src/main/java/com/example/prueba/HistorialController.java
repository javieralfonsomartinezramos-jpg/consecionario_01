package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistorialController {

    @FXML
    private FlowPane flowHistorial;

    @FXML
    public void initialize() {

        mostrarHistorial();
    }

    private void mostrarHistorial() {

        flowHistorial.getChildren().clear();

        if (DataStore.historial.isEmpty()) {

            Label vacio =
                    new Label("No hay compras aún");

            vacio.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 24;

                    """);

            flowHistorial.getChildren().add(vacio);

            return;
        }

        for (Moto moto : DataStore.historial) {

            VBox card =
                    new VBox(10);

            card.setAlignment(Pos.CENTER);

            card.setPrefWidth(300);

            card.setStyle("""

                    -fx-background-color: #1e1e1e;
                    -fx-background-radius: 20;
                    -fx-padding: 20;

                    """);

            Label nombre =
                    new Label(moto.getNombre());

            nombre.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 20;
                    -fx-font-weight: bold;

                    """);

            Label precio =
                    new Label("$ " + moto.getPrecio());

            precio.setStyle("""

                    -fx-text-fill: #d9ad26;
                    -fx-font-size: 24;

                    """);

            card.getChildren().addAll(
                    nombre,
                    precio
            );

            flowHistorial.getChildren().add(card);
        }
    }

    @FXML
    public void volverCatalogo() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "catalogo.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) flowHistorial
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            stage.setFullScreen(true);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}