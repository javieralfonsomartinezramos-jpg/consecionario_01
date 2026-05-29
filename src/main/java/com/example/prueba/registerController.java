package com.example.prueba;

import archivos.txt.ArchivoDatosUsuario;
import archivos.txt.ArchivoUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String clave = txtContrasena.getText().trim();

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
                    "Correo invalido");
            return;
        }

        if (ArchivoUsuarios.esDatoReservado(nombre, correo)) {

            mostrarMensaje("Error",
                    "Las credenciales del administrador estan reservadas");
            return;
        }

        if (!clave.matches("\\d+")) {

            mostrarMensaje("Error",
                    "La contrasena debe contener solo numeros");
            return;
        }

        if (nombre.contains(",")
                || correo.contains(",")
                || clave.contains(",")) {

            mostrarMensaje("Error",
                    "No uses comas en los datos de registro");
            return;
        }

        Users user =
                new Users(nombre, correo, clave);

        if (!ArchivoUsuarios.guardarUsuario(user)) {

            mostrarMensaje("Error",
                    "Ya existe un usuario con ese correo");
            return;
        }

        ArchivoDatosUsuario.inicializarDatos(user);

        mostrarMensaje("Exito",
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

        UI.mostrarMaximizado(
                stage,
                UI.crearEscena(root)
        );
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
