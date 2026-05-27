package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CatalogoController {

    @FXML
    private Label lblUsuario;

    @FXML
    private ComboBox<String> comboPrecio;

    @FXML
    private ComboBox<String> comboCc;

    @FXML
    private FlowPane flowPane;

    @FXML
    private Button btnAdmin;

    @FXML
    private TextField txtBuscar;

    private List<Moto> listaMotos =
            new ArrayList<>();

    @FXML
    public void initialize() {

        // USUARIO LOGEADO

        if (Session.usuarioActual != null) {

            lblUsuario.setText(
                    "Bienvenido "
                            + Session.usuarioActual.getNombre()
            );

        } else {

            lblUsuario.setText(
                    "Bienvenido Invitado"
            );
        }

        // ADMIN PANEL

        if (Session.esAdmin()) {

            btnAdmin.setVisible(true);

            btnAdmin.setManaged(true);

        } else {

            btnAdmin.setVisible(false);

            btnAdmin.setManaged(false);
        }

        cargarMotos();

        cargarFiltros();

        mostrarMotos(listaMotos);

        // BUSCADOR EN TIEMPO REAL

        txtBuscar.textProperty().addListener(

                (observable, oldValue, newValue) ->

                        buscarMoto(newValue)
        );
    }

    // =================================================
    // CARGAR MOTOS
    // =================================================

    private void cargarMotos() {

        listaMotos.clear();

        listaMotos.add(new Moto(
                "Honda CBR 600RR",
                "Honda",
                600,
                12000,
                5,
                "honda_cbr_600rr.png"
        ));

        listaMotos.add(new Moto(
                "Kawasaki Ninja 400",
                "Kawasaki",
                400,
                6500,
                5,
                "kawasaki_ninja_400.png"
        ));

        listaMotos.add(new Moto(
                "Yamaha MT 15",
                "Yamaha",
                155,
                5000,
                5,
                "mt15_temp_darkGray.png"
        ));

        listaMotos.add(new Moto(
                "Yamaha R1",
                "Yamaha",
                1000,
                18000,
                5,
                "R1-2022-COLOR_AZUL.png"
        ));

        listaMotos.add(new Moto(
                "Suzuki GSX-R 1000",
                "Suzuki",
                1000,
                15500,
                5,
                "suzuki_gsx_r1000.png"
        ));

        listaMotos.add(new Moto(
                "Yamaha MT 07",
                "Yamaha",
                700,
                8500,
                5,
                "yamaha_mt07.png"
        ));

        listaMotos.add(new Moto(
                "Bajaj Pulsar NS 200",
                "Bajaj",
                200,
                3000,
                5,
                "bajaj-pulsar-ns200.png"
        ));

        listaMotos.add(new Moto(
                "BMW M 1000 RR",
                "BMW",
                1000,
                35000,
                5,
                "bmw-m1000rr.png"
        ));

        listaMotos.add(new Moto(
                "Honda CBF 125",
                "Honda",
                125,
                2500,
                5,
                "honda-cbf125.png"
        ));

        listaMotos.add(new Moto(
                "Honda CBR 600RR Blanca",
                "Honda",
                600,
                12500,
                5,
                "Honda-CBR600RR.png"
        ));

        listaMotos.add(new Moto(
                "Honda CBR 1000RR",
                "Honda",
                1000,
                20000,
                5,
                "honda-cbr1000rr.png"
        ));

        listaMotos.add(new Moto(
                "Kawasaki Z650",
                "Kawasaki",
                650,
                8000,
                5,
                "kawasaki-z650.png"
        ));

        listaMotos.add(new Moto(
                "KTM RC 390",
                "KTM",
                390,
                6000,
                5,
                "ktm-rc390.png"
        ));

        listaMotos.add(new Moto(
                "Suzuki Gixxer SF 250",
                "Suzuki",
                250,
                4500,
                5,
                "suzuki-gixxer-sf250.png"
        ));

        listaMotos.add(new Moto(
                "Yamaha YZF R3",
                "Yamaha",
                320,
                6000,
                5,
                "yamaha-yzf-r3.png"
        ));
    }

    // =================================================
    // FILTROS
    // =================================================

    private void cargarFiltros() {

        comboPrecio.getItems().addAll(

                "2500 - 6000",
                "6001 - 13000",
                "13001 - 40000"
        );

        comboCc.getItems().addAll(

                "Baja (125-300)",
                "Media (301-500)",
                "Alta (501-1000)"
        );
    }

    // =================================================
    // MOSTRAR MOTOS
    // =================================================

    private void mostrarMotos(
            List<Moto> motos
    ) {

        flowPane.getChildren().clear();

        for (Moto moto : motos) {

            VBox card =
                    crearCard(moto);

            flowPane.getChildren().add(card);
        }
    }

    // =================================================
    // CREAR CARD
    // =================================================

    private VBox crearCard(Moto moto) {

        ImageView imagen =
                new ImageView();

        try {

            String ruta =
                    "/com/example/prueba/images/"
                            + moto.getImagen();

            var recurso =
                    getClass().getResourceAsStream(ruta);

            if (recurso != null) {

                Image img =
                        new Image(recurso);

                imagen.setImage(img);

            } else {

                System.out.println(
                        "NO EXISTE: " + ruta
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        imagen.setFitWidth(250);

        imagen.setFitHeight(170);

        imagen.setPreserveRatio(true);

        // NOMBRE

        Label nombre =
                new Label(moto.getNombre());

        nombre.setStyle("""

                -fx-text-fill: white;
                -fx-font-size: 22;
                -fx-font-weight: bold;

                """);

        // CILINDRAJE

        Label cc =
                new Label(
                        moto.getCilindraje()
                                + " CC"
                );

        cc.setStyle("""

                -fx-text-fill: #bbbbbb;
                -fx-font-size: 15;

                """);

        // PRECIO

        Label precio =
                new Label(
                        "$ " + moto.getPrecio()
                );

        precio.setStyle("""

                -fx-text-fill: #d9ad26;
                -fx-font-size: 28;
                -fx-font-weight: bold;

                """);

        // STOCK

        Label stock =
                new Label(
                        "Stock: "
                                + moto.getStock()
                );

        stock.setStyle("""

                -fx-text-fill: #00c853;
                -fx-font-size: 14;

                """);

        // =================================================
        // BOTON DETALLES
        // =================================================

        Button detalles =
                new Button(
                        "Ver detalles"
                );

        detalles.setPrefWidth(200);

        detalles.setStyle("""

                -fx-background-color: #d9ad26;
                -fx-text-fill: black;
                -fx-font-size: 14;
                -fx-font-weight: bold;
                -fx-background-radius: 15;
                -fx-cursor: hand;

                """);

        detalles.setOnAction(e -> {

            try {

                // GUARDAR MOTO SELECCIONADA

                DataStore.motoSeleccionada = moto;

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass().getResource(
                                        "detallemoto.fxml"
                                )
                        );

                Scene scene =
                        new Scene(loader.load());

                Stage stage =
                        (Stage) flowPane
                                .getScene()
                                .getWindow();

                stage.setScene(scene);

                stage.setMaximized(true);

                stage.show();

            } catch (Exception ex) {

                ex.printStackTrace();

                mostrarMensaje(
                        "Error",
                        "No se pudo abrir detallemoto.fxml"
                );
            }
        });

        // =================================================
        // FAVORITOS
        // =================================================

        Button favorito =
                new Button("❤");

        favorito.setStyle("""

                -fx-background-color: #ff2d55;
                -fx-text-fill: white;
                -fx-font-size: 16;
                -fx-background-radius: 50;
                -fx-cursor: hand;

                """);

        favorito.setOnAction(e -> {

            if (!DataStore.favoritos.contains(moto)) {

                DataStore.favoritos.add(moto);

                mostrarMensaje(
                        "Favoritos",
                        moto.getNombre()
                                + " agregado a favoritos"
                );

            } else {

                mostrarMensaje(
                        "Favoritos",
                        "La moto ya está en favoritos"
                );
            }
        });

        // BOTONES

        HBox botones =
                new HBox(10);

        botones.setAlignment(Pos.CENTER);

        botones.getChildren().addAll(
                detalles,
                favorito
        );

        // CARD

        VBox card =
                new VBox(15);

        card.setAlignment(Pos.CENTER);

        card.setPrefWidth(320);

        card.setStyle("""

                -fx-background-color: #1e1e1e;
                -fx-background-radius: 25;
                -fx-padding: 20;

                -fx-effect: dropshadow(
                    three-pass-box,
                    rgba(0,0,0,0.5),
                    15,
                    0,
                    0,
                    10
                );

                """);

        card.getChildren().addAll(

                imagen,
                nombre,
                cc,
                precio,
                stock,
                botones
        );

        return card;
    }

    // =================================================
    // BUSCAR MOTO
    // =================================================

    private void buscarMoto(
            String texto
    ) {

        List<Moto> resultado =
                new ArrayList<>();

        for (Moto moto : listaMotos) {

            if (moto.getNombre()
                    .toLowerCase()
                    .contains(
                            texto.toLowerCase()
                    )) {

                resultado.add(moto);
            }
        }

        mostrarMotos(resultado);
    }

    // =================================================
    // FILTRAR PRECIO
    // =================================================

    @FXML
    public void filtrarPrecio() {

        String opcion =
                comboPrecio.getValue();

        if (opcion == null)
            return;

        List<Moto> filtradas =
                new ArrayList<>();

        for (Moto m : listaMotos) {

            if (opcion.equals("2500 - 6000")
                    && m.getPrecio() >= 2500
                    && m.getPrecio() <= 6000) {

                filtradas.add(m);
            }

            if (opcion.equals("6001 - 13000")
                    && m.getPrecio() >= 6001
                    && m.getPrecio() <= 13000) {

                filtradas.add(m);
            }

            if (opcion.equals("13001 - 40000")
                    && m.getPrecio() >= 13001
                    && m.getPrecio() <= 40000) {

                filtradas.add(m);
            }
        }

        mostrarMotos(filtradas);
    }

    // =================================================
    // FILTRAR CC
    // =================================================

    @FXML
    public void filtrarCc() {

        String opcion =
                comboCc.getValue();

        if (opcion == null)
            return;

        List<Moto> filtradas =
                new ArrayList<>();

        for (Moto m : listaMotos) {

            if (opcion.equals("Baja (125-300)")
                    && m.getCilindraje() >= 125
                    && m.getCilindraje() <= 300) {

                filtradas.add(m);
            }

            if (opcion.equals("Media (301-500)")
                    && m.getCilindraje() >= 301
                    && m.getCilindraje() <= 500) {

                filtradas.add(m);
            }

            if (opcion.equals("Alta (501-1000)")
                    && m.getCilindraje() >= 501
                    && m.getCilindraje() <= 1000) {

                filtradas.add(m);
            }
        }

        mostrarMotos(filtradas);
    }

    // =================================================
    // RESETEAR
    // =================================================

    @FXML
    public void resetearCatalogo() {

        comboPrecio.setValue(null);

        comboCc.setValue(null);

        txtBuscar.clear();

        mostrarMotos(listaMotos);
    }

    // =================================================
    // NAVEGACION
    // =================================================

    @FXML
    public void abrirTienda() {

        mostrarMensaje(
                "Tienda",
                "Ya estás en la tienda"
        );
    }

    @FXML
    public void abrirCarrito() {

        abrirVentana("carrito.fxml");
    }

    @FXML
    public void abrirFavoritos() {

        abrirVentana("favorito.fxml");
    }

    @FXML
    public void abrirHistorial() {

        abrirVentana("historial.fxml");
    }

    @FXML
    public void abrirAdminPanel() {

        abrirVentana("adminPanel.fxml");
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

            if (loader.getLocation() == null) {

                mostrarMensaje(
                        "Error",
                        "No existe: " + archivo
                );

                return;
            }

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) flowPane
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