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

public class HelloController {

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasena;

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {

        String correo = txtCorreo.getText();
        String contra = txtContrasena.getText();

        // validar campos vacíos
        if (correo.isEmpty() || contra.isEmpty()) {
            mostrarMensaje("Error", "Completa todos los campos");
            return;
        }

        // validar contraseña numérica
        if (!contra.matches("\\d+")) {
            mostrarMensaje("Error", "La contraseña debe contener solo números");
            return;
        }

        // ADMIN
        if (correo.equals("admin@gmail.com") && contra.equals("1234")) {
            mostrarMensaje("Admin", "Bienvenido administrador");
            abrirCatalogo();
            return;
        }

        // usuario normal con correo válido
        if (correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mostrarMensaje("Éxito", "Inicio de sesión correcto");
            abrirCatalogo();
        } else {
            mostrarMensaje("Error", "Correo inválido");
        }
    }

    private void abrirCatalogo() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("catalogo.fxml"));

        Stage stage = (Stage) txtCorreo.getScene().getWindow();
        stage.setScene(new Scene(root, 1366, 768));
        stage.centerOnScreen();
        stage.show();
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}