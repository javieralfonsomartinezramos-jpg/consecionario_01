package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetalleMotoController {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblMarca;

    @FXML
    private Label lblCc;

    @FXML
    private Label lblPrecio;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblDescripcion;

    @FXML
    private ImageView imgMoto;

    private Moto moto;

    // =================================================
    // CARGAR MOTO
    // =================================================

    public void setMoto(Moto moto) {

        this.moto = moto;

        lblNombre.setText(
                moto.getNombre()
        );

        lblMarca.setText(
                moto.getMarca()
        );

        lblCc.setText(
                moto.getCilindraje() + " CC"
        );

        lblPrecio.setText(
                "$ " + moto.getPrecio()
        );

        lblStock.setText(
                String.valueOf(
                        moto.getStock()
                )
        );

        lblDescripcion.setText(

                "La " + moto.getNombre()
                        + " es una motocicleta "
                        + moto.getMarca()
                        + " de "
                        + moto.getCilindraje()
                        + " CC ideal para alto rendimiento."
        );

        cargarImagen();
    }

    // =================================================
    // CARGAR IMAGEN
    // =================================================

    private void cargarImagen() {

        try {

            String ruta =
                    "/com/example/prueba/images/"
                            + moto.getImagen();

            var recurso =
                    getClass().getResourceAsStream(
                            ruta
                    );

            if (recurso != null) {

                Image image =
                        new Image(recurso);

                imgMoto.setImage(image);

            } else {

                System.out.println(
                        "No existe: " + ruta
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =================================================
    // AGREGAR CARRITO
    // =================================================

    @FXML
    public void agregarCarrito() {

        if (moto.getStock() <= 0) {

            mostrarMensaje(
                    "Sin stock",
                    "No hay unidades disponibles"
            );

            return;
        }

        moto.setStock(
                moto.getStock() - 1
        );

        DataStore.carrito.add(moto);

        DataStore.historial.push(moto);

        lblStock.setText(
                String.valueOf(
                        moto.getStock()
                )
        );

        mostrarMensaje(
                "Carrito",
                moto.getNombre()
                        + " agregado al carrito"
        );
    }

    // =================================================
    // FAVORITOS
    // =================================================

    @FXML
    public void agregarFavorito() {

        DataStore.favoritos.add(moto);

        mostrarMensaje(
                "Favoritos",
                moto.getNombre()
                        + " agregado a favoritos"
        );
    }

    // =================================================
    // VOLVER
    // =================================================

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
                    (Stage) lblNombre
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            stage.setMaximized(true);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
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
