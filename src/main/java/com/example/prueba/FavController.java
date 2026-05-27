package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FavController {

    @FXML
    private FlowPane flowFavoritos;

    @FXML
    public void initialize() {

        cargarFavoritos();
    }

    // =================================================
    // CARGAR FAVORITOS
    // =================================================

    private void cargarFavoritos() {

        flowFavoritos.getChildren().clear();

        for (Moto moto : DataStore.favoritos) {

            VBox card = crearCard(moto);

            flowFavoritos.getChildren().add(card);
        }
    }

    // =================================================
    // CREAR CARD
    // =================================================

    private VBox crearCard(Moto moto) {

        ImageView imagen = new ImageView();

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

        imagen.setFitWidth(250);

        imagen.setFitHeight(170);

        imagen.setPreserveRatio(true);

        // NOMBRE

        Label nombre =
                new Label(moto.getNombre());

        nombre.setStyle("""

                -fx-text-fill: white;
                -fx-font-size: 22;
                -fx-font-weight: bold;

                """);

        // PRECIO

        Label precio =
                new Label("$ " + moto.getPrecio());

        precio.setStyle("""

                -fx-text-fill: #d9ad26;
                -fx-font-size: 24;
                -fx-font-weight: bold;

                """);

        // STOCK

        Label stock =
                new Label(
                        "Stock: "
                                + moto.getStock()
                );

        stock.setStyle("""

                -fx-text-fill: #00c853;
                -fx-font-size: 15;

                """);

        // =================================================
        // BOTON CARRITO
        // =================================================

        Button btnCarrito =
                new Button("Agregar al carrito");

        btnCarrito.setStyle("""

                -fx-background-color: #d9ad26;
                -fx-text-fill: black;
                -fx-font-weight: bold;
                -fx-background-radius: 15;

                """);

        btnCarrito.setOnAction(e -> {

            if (moto.getStock() <= 0) {

                mostrarMensaje(
                        "Sin stock",
                        "No quedan unidades"
                );

                return;
            }

            moto.setStock(
                    moto.getStock() - 1
            );

            DataStore.carrito.add(moto);

            stock.setText(
                    "Stock: "
                            + moto.getStock()
            );

            mostrarMensaje(
                    "Carrito",
                    "Moto agregada al carrito"
            );
        });

        // =================================================
        // ELIMINAR FAVORITO
        // =================================================

        Button eliminar =
                new Button("Eliminar");

        eliminar.setStyle("""

                -fx-background-color: #ff2d55;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-background-radius: 15;

                """);

        eliminar.setOnAction(e -> {

            DataStore.favoritos.remove(moto);

            cargarFavoritos();
        });

        HBox botones =
                new HBox(10);

        botones.setAlignment(Pos.CENTER);

        botones.getChildren().addAll(
                btnCarrito,
                eliminar
        );

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
                precio,
                stock,
                botones
        );

        return card;
    }

    // =================================================
    // VOLVER
    // =================================================

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
                    (Stage) flowFavoritos
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            stage.setMaximized(true);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =================================================
    // ALERTAS
    // =================================================

    private void mostrarMensaje(
            String titulo,
            String mensaje
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(titulo);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}