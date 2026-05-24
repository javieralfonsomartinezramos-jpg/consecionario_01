package com.example.prueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class registerController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasena;

    @FXML
    void registrarUsuario(ActionEvent event) {

        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String clave = txtContrasena.getText();

        if (nombre.isEmpty()
                || correo.isEmpty()
                || clave.isEmpty()) {

            mostrarMensaje("Error",
                    "Completa todos los campos");
            return;
        }

        if (!correo.matches(
                "^[A-Za-z0-9+_.-]+@(.+)$")) {

            mostrarMensaje("Error",
                    "Correo inválido");
            return;
        }

        if (!clave.matches("\\d+")) {

            mostrarMensaje("Error",
                    "La contraseña debe contener solo números");
            return;
        }

        Users user =
                new Users(nombre, correo, clave);

        ArchivoUsuarios.guardarUsuario(user);

        mostrarMensaje("Éxito",
                "Usuario registrado");

        txtNombre.clear();
        txtCorreo.clear();
        txtContrasena.clear();
    }

    @FXML
    void volverLogin(ActionEvent event)
            throws IOException {

        Parent root =
                FXMLLoader.load(
                        Objects.requireNonNull(getClass().getResource(
                                "hello-view.fxml")));

        Stage stage =
                (Stage) txtNombre.getScene().getWindow();

        stage.setScene(
                new Scene(root, 1920, 1080)
        );

        stage.setMaximized(true);

        stage.show();
    }

    @FXML
    void cerrarVentana(ActionEvent event) {

        Stage stage =
                (Stage) txtNombre.getScene().getWindow();

        stage.close();
    }

    private void mostrarMensaje(String titulo,
                                String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}