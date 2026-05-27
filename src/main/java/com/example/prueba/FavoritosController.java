package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class FavoritosController {

    @FXML
    private FlowPane flowFavoritos;

    @FXML
    private Button btnVolver;

    @FXML
    public void initialize() {

        mostrarFavoritos();
    }

    private void mostrarFavoritos() {

        flowFavoritos
                .getChildren()
                .clear();

        if (DataStore.favoritos.isEmpty()) {

            Label vacio =
                    new Label(
                            "❤ No tienes motos favoritas"
                    );

            vacio.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 24;
                    -fx-font-weight: bold;

                    """);

            flowFavoritos
                    .getChildren()
                    .add(vacio);

            return;
        }

        for (Moto moto : DataStore.favoritos) {

            VBox card =
                    crearCard(moto);

            flowFavoritos
                    .getChildren()
                    .add(card);
        }
    }

    private VBox crearCard(
            Moto moto
    ) {

        ImageView imagen =
                new ImageView();

        try {

            String ruta =
                    "/com/example/prueba/images/"
                            + moto.getImagen();

            Image img =
                    new Image(
                            Objects.requireNonNull(getClass()
                                    .getResourceAsStream(ruta))
                    );

            imagen.setImage(img);

        } catch (Exception e) {

            e.printStackTrace();
        }

        imagen.setFitWidth(230);

        imagen.setFitHeight(160);

        imagen.setPreserveRatio(true);

        Label nombre =
                new Label(
                        moto.getNombre()
                );

        nombre.setStyle("""

                -fx-text-fill: white;
                -fx-font-size: 22;
                -fx-font-weight: bold;

                """);

        Label precio =
                new Label(
                        "$ " + moto.getPrecio()
                );

        precio.setStyle("""

                -fx-text-fill: #ff4d6d;
                -fx-font-size: 24;
                -fx-font-weight: bold;

                """);

        Label cc =
                new Label(
                        moto.getCilindraje()
                                + " CC"
                );

        cc.setStyle("""

                -fx-text-fill: #bbbbbb;
                -fx-font-size: 14;

                """);

        Button eliminar =
                new Button(
                        "Eliminar"
                );

        eliminar.setStyle("""

                -fx-background-color: #ff4d6d;
                -fx-text-fill: white;
                -fx-font-size: 14;
                -fx-font-weight: bold;
                -fx-background-radius: 15;
                -fx-cursor: hand;

                """);

        eliminar.setOnAction(e -> {

            DataStore.favoritos.remove(moto);

            mostrarFavoritos();
        });

        HBox botones =
                new HBox(10);

        botones.setAlignment(Pos.CENTER);

        botones.getChildren().add(eliminar);

        VBox card =
                new VBox(15);

        card.setAlignment(Pos.CENTER);

        card.setPrefWidth(320);

        card.setStyle("""

                -fx-background-color: #1e1e1e;
                -fx-background-radius: 25;
                -fx-padding: 20;

                -fx-effect: dropshadow(
                    three-pass-box,
                    rgba(0,0,0,0.5),
                    15,
                    0,
                    0,
                    10
                );

                """);

        card.getChildren().addAll(

                imagen,
                nombre,
                cc,
                precio,
                botones
        );

        return card;
    }

    @FXML
    public void volverCatalogo() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "catalogo.fxml"
                                    )
                    );

            Scene scene =
                    new Scene(
                            loader.load()
                    );

            Stage stage =
                    (Stage) btnVolver
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