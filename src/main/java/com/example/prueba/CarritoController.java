package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CarritoController {

    // =====================================
    // LISTA DEL CARRITO
    // =====================================

    public static List<Moto> carrito =
            new ArrayList<>();

    // =====================================
    // FXML
    // =====================================

    @FXML
    private VBox contenedorCarrito;

    @FXML
    private Label lblTotal;

    // =====================================
    // INITIALIZE
    // =====================================

    @FXML
    public void initialize() {

        mostrarCarrito();
    }

    // =====================================
    // MOSTRAR CARRITO
    // =====================================

    private void mostrarCarrito() {

        contenedorCarrito
                .getChildren()
                .clear();

        double total = 0;

        if (carrito.isEmpty()) {

            Label vacio =
                    new Label(
                            "El carrito está vacío"
                    );

            vacio.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 24;

                    """);

            contenedorCarrito
                    .getChildren()
                    .add(vacio);

            lblTotal.setText(
                    "TOTAL: $0"
            );

            return;
        }

        for (Moto moto : carrito) {

            VBox card =
                    new VBox(10);

            card.setStyle("""

                    -fx-background-color: #1e1e1e;
                    -fx-padding: 20;
                    -fx-background-radius: 20;

                    -fx-effect: dropshadow(
                        three-pass-box,
                        rgba(0,0,0,0.5),
                        15,
                        0,
                        0,
                        8
                    );

                    """);

            Label nombre =
                    new Label(
                            moto.getNombre()
                    );

            nombre.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 22;
                    -fx-font-weight: bold;

                    """);

            Label marca =
                    new Label(
                            "Marca: "
                                    + moto.getMarca()
                    );

            marca.setStyle("""

                    -fx-text-fill: #bbbbbb;
                    -fx-font-size: 15;

                    """);

            Label cc =
                    new Label(
                            moto.getCilindraje()
                                    + " CC"
                    );

            cc.setStyle("""

                    -fx-text-fill: #bbbbbb;
                    -fx-font-size: 15;

                    """);

            Label precio =
                    new Label(
                            "$ "
                                    + moto.getPrecio()
                    );

            precio.setStyle("""

                    -fx-text-fill: #d9ad26;
                    -fx-font-size: 24;
                    -fx-font-weight: bold;

                    """);

            card.getChildren().addAll(

                    nombre,
                    marca,
                    cc,
                    precio
            );

            contenedorCarrito
                    .getChildren()
                    .add(card);

            total += moto.getPrecio();
        }

        lblTotal.setText(
                "TOTAL: $" + total
        );
    }

    // =====================================
    // COMPRAR TODO
    // =====================================

    @FXML
    public void comprarTodo() {

        if (carrito.isEmpty()) {

            mostrarMensaje(
                    "Carrito",
                    "No hay motos en el carrito"
            );

            return;
        }

        carrito.clear();

        mostrarCarrito();

        mostrarMensaje(
                "Compra realizada",
                "Gracias por tu compra"
        );
    }

    // =====================================
    // VACIAR CARRITO
    // =====================================

    @FXML
    public void vaciarCarrito() {

        carrito.clear();

        mostrarCarrito();

        mostrarMensaje(
                "Carrito",
                "Carrito vaciado"
        );
    }

    // =====================================
    // VOLVER AL CATALOGO
    // =====================================

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
                    (Stage) contenedorCarrito
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            stage.setFullScreen(true);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================
    // ALERTAS
    // =====================================

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
