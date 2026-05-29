package com.example.prueba;

import archivos.txt.ArchivoMotos;
import estructuras.Cola;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class CarritoController {

    // =====================================
    // FXML
    // =====================================

    @FXML
    private VBox contenedorCarrito;

    @FXML
    private Label lblTotal;

    private final DecimalFormat formatoPrecio =
            new DecimalFormat("#,##0");

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

        if (DataStore.carrito.isEmpty()) {

            Label vacio =
                    new Label(
                            "El carrito esta vacio"
                    );

            vacio.setStyle("""

                    -fx-text-fill: white;
                    -fx-font-size: 24;
                    -fx-font-weight: bold;

                    """);

            contenedorCarrito
                    .getChildren()
                    .add(vacio);

            lblTotal.setText(
                    "TOTAL: " + formatearPrecio(0)
            );

            return;
        }

        for (Moto moto : DataStore.carrito) {

            VBox card =
                    new VBox(10);

            card.setMaxWidth(760);

            card.setPrefWidth(760);

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
                            formatearPrecio(
                                    moto.getPrecio()
                            )
                    );

            precio.setStyle("""

                    -fx-text-fill: #f5c542;
                    -fx-font-size: 24;
                    -fx-font-weight: bold;

                    """);

            Button eliminar =
                    new Button("Eliminar");

            eliminar.setStyle("""

                    -fx-background-color: #e63946;
                    -fx-text-fill: white;
                    -fx-font-size: 14;
                    -fx-font-weight: bold;
                    -fx-background-radius: 12;
                    -fx-cursor: hand;

                    """);

            eliminar.setOnAction(e -> {

                DataStore.carrito.remove(moto);
                DataStore.guardarCarritoUsuario();

                mostrarCarrito();
            });

            HBox filaPrecio =
                    new HBox(15);

            filaPrecio.setAlignment(Pos.CENTER_LEFT);

            filaPrecio.getChildren().addAll(
                    precio,
                    eliminar
            );

            card.getChildren().addAll(

                    nombre,
                    marca,
                    cc,
                    filaPrecio
            );

            UI.aplicarHoverElevado(card);

            contenedorCarrito
                    .getChildren()
                    .add(card);

            total += moto.getPrecio();
        }

        lblTotal.setText(
                "TOTAL: " + formatearPrecio(total)
        );
    }

    // =====================================
    // COMPRAR TODO
    // =====================================

    private boolean stockSuficienteParaCompra() {
        List<String> revisadas = new ArrayList<>();

        for (Moto moto : DataStore.carrito) {
            String clave = moto.getNombre().toLowerCase();

            if (revisadas.contains(clave)) {
                continue;
            }

            revisadas.add(clave);

            int cantidad = DataStore.contarEnCarrito(moto);

            if (cantidad > moto.getStock()) {
                mostrarMensaje(
                        "Sin stock",
                        "Solo hay " + moto.getStock()
                                + " unidades disponibles para "
                                + moto.getNombre()
                );

                return false;
            }
        }

        return true;
    }

    @FXML
    public void comprarTodo() {

        if (DataStore.carrito.isEmpty()) {

            mostrarMensaje(
                    "Carrito",
                    "No hay motos en el carrito"
            );

            return;
        }

        if (!stockSuficienteParaCompra()) {
            return;
        }

        Cola<Moto> colaCompra =
                DataStore.crearColaCompra();

        List<Moto> comprasProcesadas =
                new ArrayList<>();

        double totalCompra = 0;

        while (!colaCompra.isEmpty()) {

            Moto moto =
                    colaCompra.poll();

            moto.setStock(
                    moto.getStock() - 1
            );

            DataStore.registrarHistorial(moto);
            comprasProcesadas.add(moto);
            totalCompra += moto.getPrecio();
        }

        ArchivoMotos.guardarTodasLasMotos(
                DataStore.motos
        );

        DataStore.registrarCompra(
                comprasProcesadas,
                totalCompra
        );

        DataStore.carrito.clear();
        DataStore.guardarCarritoUsuario();

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

        DataStore.carrito.clear();
        DataStore.guardarCarritoUsuario();

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
                    UI.crearEscena(loader.load());

            Stage stage =
                    (Stage) contenedorCarrito
                            .getScene()
                            .getWindow();

            UI.mostrarMaximizado(stage, scene);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================
    // PRECIO
    // =====================================

    private String formatearPrecio(double precio) {

        return "$" + formatoPrecio.format(precio);
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
