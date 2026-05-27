package com.example.prueba;

import archivos.txt.ArchivoMotos;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class adminPanelController {

    // =========================
    // FXML
    // =========================

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> comboMarca;

    @FXML
    private TextField txtCc;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtImagen;

    // =========================
    // INITIALIZE
    // =========================

    @FXML
    public void initialize() {

        comboMarca.setItems(

                FXCollections.observableArrayList(

                        "Honda",
                        "Yamaha",
                        "Suzuki",
                        "Kawasaki",
                        "BMW",
                        "Ducati",
                        "KTM",
                        "Bajaj"

                )
        );
    }

    // =========================
    // GUARDAR MOTO
    // =========================

    @FXML
    public void guardarMoto() {

        String nombre =
                txtNombre.getText();

        String marca =
                comboMarca.getValue();

        String ccTexto =
                txtCc.getText();

        String precioTexto =
                txtPrecio.getText();

        String stockTexto =
                txtStock.getText();

        String imagen =
                txtImagen.getText();

        // =========================
        // VALIDAR CAMPOS
        // =========================

        if (nombre.isEmpty()
                || marca == null
                || ccTexto.isEmpty()
                || precioTexto.isEmpty()
                || stockTexto.isEmpty()
                || imagen.isEmpty()) {

            mostrarMensaje(
                    "Error",
                    "Completa todos los campos"
            );

            return;
        }

        // =========================
        // VALIDAR NUMEROS
        // =========================

        if (!ccTexto.matches("\\d+")
                || !precioTexto.matches("\\d+")
                || !stockTexto.matches("\\d+")) {

            mostrarMensaje(
                    "Error",
                    "CC, precio y stock deben ser numéricos"
            );

            return;
        }

        // =========================
        // CONVERSIONES
        // =========================

        int cc =
                Integer.parseInt(ccTexto);

        double precio =
                Double.parseDouble(precioTexto);

        int stock =
                Integer.parseInt(stockTexto);

        // =========================
        // CREAR MOTO
        // =========================

        Moto nuevaMoto =

                new Moto(

                        nombre,
                        marca,
                        cc,
                        precio,
                        stock,
                        imagen

                );

        // =========================
        // GUARDAR
        // =========================

        ArchivoMotos.guardarMoto(
                nuevaMoto,
                imagen
        );

        // =========================
        // MENSAJE
        // =========================

        mostrarMensaje(
                "Éxito",
                "Moto agregada correctamente"
        );

        // =========================
        // LIMPIAR
        // =========================

        limpiarCampos();
    }

    // =========================
    // LIMPIAR
    // =========================

    private void limpiarCampos() {

        txtNombre.clear();

        comboMarca.setValue(null);

        txtCc.clear();

        txtPrecio.clear();

        txtStock.clear();

        txtImagen.clear();
    }

    // =========================
    // CERRAR
    // =========================

    @FXML
    public void cerrarVentana() {

        Stage stage =

                (Stage) txtNombre
                        .getScene()
                        .getWindow();

        stage.close();
    }

    // =========================
    // ALERTAS
    // =========================

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