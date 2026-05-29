package com.example.prueba;

import archivos.txt.ArchivoUsuarios;
import archivos.txt.ArchivoSesiones;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContrasena;

    // =================================================
    // LOGIN
    // =================================================

    @FXML
    public void iniciarSesion() {

        String correo =
                txtCorreo.getText().trim();

        String contrasena =
                txtContrasena.getText().trim();

        // CAMPOS VACIOS

        if (correo.isEmpty()
                || contrasena.isEmpty()) {

            mostrarMensaje(
                    "Error",
                    "Completa todos los campos"
            );

            return;
        }

        // =================================================
        // ADMIN
        // =================================================

        if (Session.esCredencialAdmin(correo, contrasena)) {

            Session.iniciarAdmin();
            ArchivoSesiones.registrarInicio(
                    Session.usuarioActual,
                    Session.esAdmin()
            );

            abrirCatalogo();

            return;
        }

        // =================================================
        // USUARIO NORMAL
        // =================================================

        Users usuario =
                ArchivoUsuarios.validarUsuario(
                        correo,
                        contrasena
                );

        if (usuario != null) {

            Session.iniciarUsuario(usuario);
            ArchivoSesiones.registrarInicio(
                    Session.usuarioActual,
                    Session.esAdmin()
            );

            abrirCatalogo();

        } else {

            mostrarMensaje(
                    "Error",
                    "Correo o contraseña incorrectos"
            );
        }
    }

    // =================================================
    // ABRIR REGISTRO
    // =================================================

    @FXML
    public void abrirRegistro() {

        abrirVentana("register.fxml");
    }

    // =================================================
    // ABRIR CATALOGO
    // =================================================

    private void abrirCatalogo() {

        abrirVentana("catalogo.fxml");
    }

    // =================================================
    // ABRIR VENTANA
    // =================================================

    private void abrirVentana(
            String archivo
    ) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    archivo
                            )
                    );

            Scene scene =
                    UI.crearEscena(loader.load());

            Stage stage =
                    (Stage) txtCorreo
                            .getScene()
                            .getWindow();

            UI.mostrarMaximizado(stage, scene);

        } catch (Exception e) {

            e.printStackTrace();

            mostrarMensaje(
                    "Error",
                    "No se pudo abrir la ventana"
            );
        }
    }

    // =================================================
    // SALIR
    // =================================================

    @FXML
    public void salirPrograma() {

        ArchivoSesiones.registrarCierre(
                Session.usuarioActual,
                Session.esAdmin()
        );
        DataStore.guardarDatosUsuario();
        DataStore.limpiarDatosSesion();
        Session.cerrarSesion();

        System.exit(0);
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
