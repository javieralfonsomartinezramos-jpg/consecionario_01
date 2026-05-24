package com.example.prueba;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {

    @FXML
    private Pane panelPrincipal;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasena;

    @FXML
    public void initialize() {

        Platform.runLater(() -> {

            Stage stage =
                    (Stage) panelPrincipal
                            .getScene()
                            .getWindow();

            panelPrincipal.layoutXProperty().bind(
                    stage.widthProperty()
                            .subtract(panelPrincipal.widthProperty())
                            .divide(2)
            );

            panelPrincipal.layoutYProperty().bind(
                    stage.heightProperty()
                            .subtract(panelPrincipal.heightProperty())
                            .divide(2)
            );
        });
    }

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {

        String correo = txtCorreo.getText();

        String contra = txtContrasena.getText();

        if (correo.isEmpty() || contra.isEmpty()) {

            mostrarMensaje(
                    "Error",
                    "Completa todos los campos"
            );

            return;
        }

        if (!contra.matches("\\d+")) {

            mostrarMensaje(
                    "Error",
                    "La contraseña debe contener solo números"
            );

            return;
        }

        if (correo.equals("admin@gmail.com")
                && contra.equals("1234")) {

            mostrarMensaje(
                    "Admin",
                    "Bienvenido administrador"
            );

            abrirCatalogo();

            return;
        }

        if (ArchivoUsuarios.validarUsuario(
                correo,
                contra
        )) {

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

        alert.showAndWait();
    }
}