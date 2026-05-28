package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.text.DecimalFormat;

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

    private final DecimalFormat formatoPrecio =
            new DecimalFormat("#,##0");

    @FXML
    public void initialize() {

        if (DataStore.motoSeleccionada != null) {
            setMoto(DataStore.motoSeleccionada);
        }
    }

    // =================================================
    // CARGAR MOTO SELECCIONADA
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
                formatearPrecio(moto.getPrecio())
        );

        actualizarStock();

        lblDescripcion.setText(
                crearDescripcion(moto)
        );

        cargarImagen();
    }

    // =================================================
    // DESCRIPCION
    // =================================================

    private String crearDescripcion(Moto moto) {

        String uso;

        if (moto.getCilindraje() <= 300) {

            uso = "ideal para ciudad, uso diario y bajo consumo";

        } else if (moto.getCilindraje() <= 600) {

            uso = "perfecta para quienes buscan equilibrio entre potencia, comodidad y control";

        } else {

            uso = "pensada para alto rendimiento, velocidad y una experiencia deportiva";
        }

        return "La " + moto.getNombre()
                + " es una motocicleta de la marca "
                + moto.getMarca()
                + " con motor de "
                + moto.getCilindraje()
                + " CC. Es una moto "
                + uso
                + ". Su precio actual es "
                + formatearPrecio(moto.getPrecio())
                + " y cuenta con "
                + moto.getStock()
                + " unidades disponibles en tienda.";
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
                        "No existe la imagen: " + ruta
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

        if (moto == null) {

            mostrarMensaje(
                    "Error",
                    "No hay moto seleccionada"
            );

            return;
        }

        int cantidadEnCarrito =
                contarMotoEnCarrito(moto);

        if (moto.getStock() <= 0
                || cantidadEnCarrito >= moto.getStock()) {

            mostrarMensaje(
                    "Sin stock",
                    "No hay mas unidades disponibles para agregar"
            );

            return;
        }

        DataStore.carrito.add(moto);

        DataStore.historial.push(moto);

        actualizarStock();

        mostrarMensaje(
                "Carrito",
                moto.getNombre()
                        + " agregado al carrito"
        );
    }

    private int contarMotoEnCarrito(Moto motoBuscada) {

        int contador = 0;

        for (Moto item : DataStore.carrito) {

            if (item.getNombre()
                    .equalsIgnoreCase(
                            motoBuscada.getNombre()
                    )) {

                contador++;
            }
        }

        return contador;
    }

    private void actualizarStock() {

        int disponibles =
                moto.getStock()
                        - contarMotoEnCarrito(moto);

        lblStock.setText(
                String.valueOf(disponibles)
        );
    }

    // =================================================
    // FAVORITOS
    // =================================================

    @FXML
    public void agregarFavorito() {

        if (moto == null) {
            return;
        }

        for (Moto favorita : DataStore.favoritos) {

            if (favorita.getNombre()
                    .equalsIgnoreCase(
                            moto.getNombre()
                    )) {

                mostrarMensaje(
                        "Favoritos",
                        "La moto ya esta en favoritos"
                );

                return;
            }
        }

        DataStore.favoritos.add(moto);

        mostrarMensaje(
                "Favoritos",
                moto.getNombre()
                        + " agregado a favoritos"
        );
    }

    // =================================================
    // ABRIR CARRITO
    // =================================================

    @FXML
    public void abrirCarrito() {

        abrirVentana("carrito.fxml");
    }

    // =================================================
    // VOLVER
    // =================================================

    @FXML
    public void volverCatalogo() {

        abrirVentana("catalogo.fxml");
    }

    // =================================================
    // VENTANAS
    // =================================================

    private void abrirVentana(String archivo) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    archivo
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

            mostrarMensaje(
                    "Error",
                    "No se pudo abrir " + archivo
            );
        }
    }

    // =================================================
    // PRECIO
    // =================================================

    private String formatearPrecio(double precio) {

        return "$" + formatoPrecio.format(precio);
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
