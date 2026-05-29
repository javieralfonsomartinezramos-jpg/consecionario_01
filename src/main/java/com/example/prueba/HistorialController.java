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

public class HistorialController {

    @FXML
    private FlowPane flowHistorial;

    private final DecimalFormat formatoPrecio =
            new DecimalFormat("#,##0");

    @FXML
    public void initialize() {

        mostrarHistorial();
    }

    private void mostrarHistorial() {

        flowHistorial.getChildren().clear();

        if (DataStore.historial.isEmpty()) {

            Label vacio =
                    new Label("No hay motos en el historial");

            vacio.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 24;
                    -fx-font-weight: bold;

                    """);

            flowHistorial.getChildren().add(vacio);

            return;
        }

        for (Moto moto : DataStore.historial.toListDesdeCima()) {
            VBox card =
                    crearCard(moto);

            flowHistorial.getChildren().add(card);
        }
    }

    private VBox crearCard(Moto moto) {

        ImageView imagen =
                crearImagen(moto);

        StackPane contenedorImagen =
                new StackPane(imagen);

        contenedorImagen.setPrefSize(260, 160);

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
                -fx-font-size: 20;
                -fx-font-weight: bold;

                """);

        Label datos =
                new Label(
                        moto.getMarca()
                                + " | "
                                + moto.getCilindraje()
                                + " CC"
                );

        datos.setStyle("""

                -fx-text-fill: #bbbbbb;
                -fx-font-size: 14;

                """);

        Label precio =
                new Label(
                        formatearPrecio(
                                moto.getPrecio()
                        )
                );

        precio.setStyle("""

                -fx-text-fill: #f5c542;
                -fx-font-size: 24;
                -fx-font-weight: bold;

                """);

        Button verDetalle =
                new Button("Ver detalle");

        verDetalle.setStyle("""

                -fx-background-color: #f5c542;
                -fx-text-fill: #111111;
                -fx-font-size: 13;
                -fx-font-weight: bold;
                -fx-background-radius: 12;
                -fx-cursor: hand;

                """);

        verDetalle.setOnAction(e -> abrirDetalle(moto));

        HBox botones =
                new HBox(10);

        botones.setAlignment(Pos.CENTER);

        botones.getChildren().add(verDetalle);

        VBox card =
                new VBox(14);

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
                datos,
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

                imagen.setImage(
                        new Image(recurso)
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        imagen.setFitWidth(230);

        imagen.setFitHeight(140);

        imagen.setPreserveRatio(true);

        return imagen;
    }

    private void abrirDetalle(Moto moto) {

        try {

            DataStore.motoSeleccionada = moto;

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "detallemoto.fxml"
                            )
                    );

            Scene scene =
                    UI.crearEscena(loader.load());

            DetalleMotoController controller =
                    loader.getController();

            controller.setMoto(moto);

            Stage stage =
                    (Stage) flowHistorial
                            .getScene()
                            .getWindow();

            UI.mostrarMaximizado(stage, scene);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void limpiarHistorial() {

        DataStore.historial.clear();
        DataStore.guardarHistorialUsuario();

        mostrarHistorial();
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
                    UI.crearEscena(loader.load());

            Stage stage =
                    (Stage) flowHistorial
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
