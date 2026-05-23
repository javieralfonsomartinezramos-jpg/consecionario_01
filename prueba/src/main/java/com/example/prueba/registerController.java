package com.example.prueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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

        // validar campos vacíos
        if (nombre.isEmpty()
                || correo.isEmpty()
                || clave.isEmpty()) {

            mostrarMensaje(
                    "Error",
                    "Completa todos los campos"
            );
            return;
        }

        // validar correo
        if (!correo.matches(
                "^[A-Za-z0-9+_.-]+@(.+)$")) {

            mostrarMensaje(
                    "Error",
                    "Correo inválido"
            );
            return;
        }

        // validar contraseña numérica
        if (!clave.matches("\\d+")) {
            mostrarMensaje(
                    "Error",
                    "La contraseña debe contener solo números"
            );
            return;
        }

        // crear usuario
        Users user = new Users(
                nombre,
                correo,
                clave
        );

        // guardar en txt
        ArchivoUsuarios.guardarUsuario(user);

        mostrarMensaje(
                "Éxito",
                "Usuario registrado correctamente"
        );

        // limpiar campos
        txtNombre.clear();
        txtCorreo.clear();
        txtContrasena.clear();
    }

    private void mostrarMensaje(
            String titulo,
            String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
