package com.example.prueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class HelloController {

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasena;

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {

        String correo = txtCorreo.getText();
        String contra = txtContrasena.getText();

        // CAMPOS VACIOS
        if (correo.isEmpty() || contra.isEmpty()) {

            mostrarMensaje(
                    "Error",
                    "Completa todos los campos"
            );
            return;
        }

        // SOLO NUMEROS
        if (!contra.matches("\\d+")) {

            mostrarMensaje(
                    "Error",
                    "La contraseña debe contener solo números"
            );
            return;
        }

        // ADMIN
        if (correo.equals("admin@gmail.com")
                && contra.equals("1234")) {

            CatalogoController.esAdmin = true;

            Users.usuarioActual = "Administrador";

            mostrarMensaje(
                    "Admin",
                    "Bienvenido administrador"
            );

            abrirCatalogo();
            return;
        }

        // USUARIO NORMAL
        Users usuario =
                ArchivoUsuarios.validarUsuario(
                        correo,
                        contra
                );

        if (usuario != null) {

            CatalogoController.esAdmin = false;

            // guardar nombre del usuario logueado
            Users.usuarioActual =
                    usuario.getNombre();

            mostrarMensaje(
                    "Éxito",
                    "Inicio de sesión correcto"
            );

            abrirCatalogo();

        } else {

            mostrarMensaje(
                    "Error",
                    "Usuario no registrado"
            );
        }
    }

    @FXML
    void abrirRegistro(ActionEvent event)
            throws IOException {

        Parent root =
                FXMLLoader.load(
                        Objects.requireNonNull(
                                getClass().getResource(
                                        "register.fxml"
                                )
                        )
                );

        Stage stage =
                (Stage) txtCorreo
                        .getScene()
                        .getWindow();

        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.show();
    }

    private void abrirCatalogo()
            throws IOException {

        Parent root =
                FXMLLoader.load(
                        Objects.requireNonNull(
                                getClass().getResource(
                                        "catalogo.fxml"
                                )
                        )
                );

        Stage stage =
                (Stage) txtCorreo
                        .getScene()
                        .getWindow();

        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.show();
    }

    private void mostrarMensaje(
            String titulo,
            String mensaje
    ) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> buttonType =
                alert.showAndWait();
    }
}