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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class FavoritosController {

    @FXML
    private FlowPane flowFavoritos;

    @FXML
    private Button btnVolver;

    private final DecimalFormat formatoPrecio =
            new DecimalFormat("#,##0");

    @FXML
    public void initialize() {

        mostrarFavoritos();
    }

    private void mostrarFavoritos() {

        flowFavoritos.getChildren().clear();

        if (DataStore.favoritos.isEmpty()) {

            Label vacio =
                    new Label("No tienes motos favoritas");

            vacio.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 24;
                    -fx-font-weight: bold;

                    """);

            flowFavoritos.getChildren().add(vacio);
            return;
        }

        for (Moto moto : DataStore.favoritos) {
            flowFavoritos.getChildren().add(crearCard(moto));
        }
    }

    private VBox crearCard(Moto moto) {

        ImageView imagen =
                crearImagen(moto);

        StackPane contenedorImagen =
                new StackPane(imagen);

        contenedorImagen.setPrefSize(260, 170);

        contenedorImagen.setStyle("""

                -fx-background-color: #2a2a2a;
                -fx-background-radius: 16;
                -fx-padding: 10;

                """);

        Label nombre =
                new Label(moto.getNombre());

        nombre.setWrapText(true);
        nombre.setMaxWidth(270);
        nombre.setAlignment(Pos.CENTER);

        nombre.setStyle("""

                -fx-text-fill: white;
                -fx-font-size: 21;
                -fx-font-weight: bold;

                """);

        Label precio =
                new Label(formatearPrecio(moto.getPrecio()));

        precio.setStyle("""

                -fx-text-fill: #ff4d6d;
                -fx-font-size: 24;
                -fx-font-weight: bold;

                """);

        Label cc =
                new Label(moto.getMarca() + " | " + moto.getCilindraje() + " CC");

        cc.setStyle("""

                -fx-text-fill: #bbbbbb;
                -fx-font-size: 14;

                """);

        Button eliminar =
                new Button("Eliminar");

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
            DataStore.guardarFavoritosUsuario();

            mostrarFavoritos();
        });

        HBox botones =
                new HBox(10, eliminar);

        botones.setAlignment(Pos.CENTER);

        VBox card =
                new VBox(15);

        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(320);

        card.setStyle("""

                -fx-background-color: linear-gradient(to bottom, #242424, #171717);
                -fx-background-radius: 20;
                -fx-border-color: #333333;
                -fx-border-radius: 20;
                -fx-padding: 18;
                -fx-effect: dropshadow(
                    three-pass-box,
                    rgba(0,0,0,0.45),
                    16,
                    0,
                    0,
                    8
                );

                """);

        card.getChildren().addAll(
                contenedorImagen,
                nombre,
                cc,
                precio,
                botones
        );

        UI.aplicarHoverElevado(card);

        return card;
    }

    private ImageView crearImagen(Moto moto) {

        ImageView imagen =
                new ImageView();

        try {
            String ruta =
                    "/com/example/prueba/images/"
                            + moto.getImagen();

            var recurso =
                    getClass().getResourceAsStream(ruta);

            if (recurso != null) {
                imagen.setImage(new Image(recurso));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        imagen.setFitWidth(230);
        imagen.setFitHeight(150);
        imagen.setPreserveRatio(true);

        return imagen;
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
                    UI.crearEscena(
                            loader.load()
                    );

            Stage stage =
                    (Stage) btnVolver
                            .getScene()
                            .getWindow();

            UI.mostrarMaximizado(stage, scene);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String formatearPrecio(double precio) {

        return "$" + formatoPrecio.format(precio);
    }
}
