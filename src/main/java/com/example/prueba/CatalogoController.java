package com.example.prueba;

import archivos.txt.ArchivoMotos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.DecimalFormat;
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

    private final List<Moto> listaMotos = new ArrayList<>();

    private final DecimalFormat formatoPrecio =
            new DecimalFormat("#,##0");

    @FXML
    public void initialize() {
        configurarUsuario();
        configurarAdmin();
        configurarCatalogoVisual();

        cargarMotos();
        cargarFiltros();
        mostrarMotos(listaMotos);

        txtBuscar.textProperty().addListener(
                (observable, oldValue, newValue) -> actualizarCatalogo()
        );

        comboPrecio.setOnAction(e -> actualizarCatalogo());
        comboCc.setOnAction(e -> actualizarCatalogo());
    }

    private void configurarUsuario() {
        if (Session.usuarioActual != null) {
            lblUsuario.setText(
                    "Bienvenido " + Session.usuarioActual.getNombre()
            );
        } else {
            lblUsuario.setText("Bienvenido Invitado");
        }
    }

    private void configurarAdmin() {
        boolean admin = Session.esAdmin();

        btnAdmin.setVisible(admin);
        btnAdmin.setManaged(admin);
    }

    private void configurarCatalogoVisual() {
        flowPane.setHgap(25);
        flowPane.setVgap(25);
        flowPane.setAlignment(Pos.CENTER);
    }

    private void cargarMotos() {
        ArchivoMotos.guardarMotosInicialesSiHaceFalta(crearMotosIniciales());

        DataStore.motos.clear();
        DataStore.motos.addAll(ArchivoMotos.cargarMotos());

        listaMotos.clear();
        listaMotos.addAll(DataStore.motos);
    }

    private List<Moto> crearMotosIniciales() {
        List<Moto> motos = new ArrayList<>();

        motos.add(new Moto("Honda CBR 600RR", "Honda", 600, 12000, 5, "honda_cbr_600rr.png"));
        motos.add(new Moto("Kawasaki Ninja 400", "Kawasaki", 400, 6500, 5, "kawasaki_ninja_400.png"));
        motos.add(new Moto("Yamaha MT 15", "Yamaha", 155, 5000, 5, "mt15_temp_darkGray.png"));
        motos.add(new Moto("Yamaha R1", "Yamaha", 1000, 18000, 5, "R1-2022-COLOR_AZUL.png"));
        motos.add(new Moto("Suzuki GSX-R 1000", "Suzuki", 1000, 15500, 5, "suzuki_gsx_r1000.png"));
        motos.add(new Moto("Yamaha MT 07", "Yamaha", 700, 8500, 5, "yamaha_mt07.png"));
        motos.add(new Moto("Bajaj Pulsar NS 200", "Bajaj", 200, 3000, 5, "bajaj-pulsar-ns200.png"));
        motos.add(new Moto("BMW M 1000 RR", "BMW", 1000, 35000, 5, "bmw-m1000rr.png"));
        motos.add(new Moto("Honda CBF 125", "Honda", 125, 2500, 5, "honda-cbf125.png"));
        motos.add(new Moto("Honda CBR 600RR Blanca", "Honda", 600, 12500, 5, "Honda-CBR600RR.png"));
        motos.add(new Moto("Honda CBR 1000RR", "Honda", 1000, 20000, 5, "honda-cbr1000rr.png"));
        motos.add(new Moto("Kawasaki Z650", "Kawasaki", 650, 8000, 5, "kawasaki-z650.png"));
        motos.add(new Moto("KTM RC 390", "KTM", 390, 6000, 5, "ktm-rc390.png"));
        motos.add(new Moto("Suzuki Gixxer SF 250", "Suzuki", 250, 4500, 5, "suzuki-gixxer-sf250.png"));
        motos.add(new Moto("Yamaha YZF R3", "Yamaha", 320, 6000, 5, "yamaha-yzf-r3.png"));

        return motos;
    }

    private void cargarFiltros() {
        comboPrecio.getItems().setAll(
                "$2,500 - $6,000",
                "$6,001 - $13,000",
                "$13,001 - $40,000"
        );

        comboCc.getItems().setAll(
                "Baja (125-300)",
                "Media (301-500)",
                "Alta (501-1000)"
        );
    }

    private void mostrarMotos(List<Moto> motos) {
        flowPane.getChildren().clear();

        if (motos.isEmpty()) {
            Label vacio = new Label("No hay motos para mostrar");

            vacio.setStyle("""
                    -fx-text-fill: white;
                    -fx-font-size: 24;
                    -fx-font-weight: bold;
                    """);

            flowPane.getChildren().add(vacio);
            return;
        }

        for (Moto moto : motos) {
            flowPane.getChildren().add(crearCard(moto));
        }
    }

    private VBox crearCard(Moto moto) {
        ImageView imagen = crearImagenMoto(moto);

        StackPane contenedorImagen = new StackPane(imagen);
        contenedorImagen.setPrefSize(280, 180);
        contenedorImagen.setMaxSize(280, 180);
        contenedorImagen.setStyle("""
                -fx-background-color: #2a2a2a;
                -fx-background-radius: 18;
                -fx-padding: 10;
                """);

        Label nombre = new Label(moto.getNombre());
        nombre.setWrapText(true);
        nombre.setMaxWidth(270);
        nombre.setAlignment(Pos.CENTER);
        nombre.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 21;
                -fx-font-weight: bold;
                """);

        Label marca = crearEtiquetaInfo(moto.getMarca());
        Label cc = crearEtiquetaInfo(moto.getCilindraje() + " CC");

        HBox datos = new HBox(10, marca, cc);
        datos.setAlignment(Pos.CENTER);

        Label precio = new Label(formatearPrecio(moto.getPrecio()));
        precio.setStyle("""
                -fx-text-fill: #f5c542;
                -fx-font-size: 30;
                -fx-font-weight: bold;
                """);

        Label stock = new Label("Stock disponible: " + moto.getStock());
        stock.setStyle("""
                -fx-text-fill: #20d67b;
                -fx-font-size: 14;
                -fx-font-weight: bold;
                """);

        Button detalles = new Button("Ver detalles");
        detalles.setPrefWidth(190);
        detalles.setStyle("""
                -fx-background-color: #f5c542;
                -fx-text-fill: #111111;
                -fx-font-size: 14;
                -fx-font-weight: bold;
                -fx-background-radius: 12;
                -fx-cursor: hand;
                """);

        detalles.setOnAction(e -> abrirDetalleMoto(moto));

        Button favorito = new Button("Fav");
        favorito.setPrefWidth(65);
        favorito.setStyle("""
                -fx-background-color: #e63946;
                -fx-text-fill: white;
                -fx-font-size: 14;
                -fx-font-weight: bold;
                -fx-background-radius: 12;
                -fx-cursor: hand;
                """);

        favorito.setOnAction(e -> agregarFavorito(moto));

        HBox botones = new HBox(10, detalles, favorito);
        botones.setAlignment(Pos.CENTER);

        VBox card = new VBox(14);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(330);
        card.setMinHeight(430);

        card.setStyle("""
                -fx-background-color: linear-gradient(to bottom, #242424, #151515);
                -fx-background-radius: 22;
                -fx-padding: 20;
                -fx-border-color: #333333;
                -fx-border-width: 1;
                -fx-border-radius: 22;
                -fx-effect: dropshadow(
                    three-pass-box,
                    rgba(0,0,0,0.45),
                    18,
                    0,
                    0,
                    8
                );
                """);

        card.getChildren().addAll(
                contenedorImagen,
                nombre,
                datos,
                precio,
                stock,
                botones
        );

        return card;
    }

    private ImageView crearImagenMoto(Moto moto) {
        ImageView imagen = new ImageView();

        try {
            String ruta = "/com/example/prueba/images/" + moto.getImagen();

            var recurso = getClass().getResourceAsStream(ruta);

            if (recurso != null) {
                imagen.setImage(new Image(recurso));
            } else {
                System.out.println("No existe la imagen: " + ruta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        imagen.setFitWidth(250);
        imagen.setFitHeight(160);
        imagen.setPreserveRatio(true);

        return imagen;
    }

    private Label crearEtiquetaInfo(String texto) {
        Label label = new Label(texto);

        label.setStyle("""
                -fx-text-fill: #d6d6d6;
                -fx-font-size: 13;
                -fx-font-weight: bold;
                -fx-background-color: #333333;
                -fx-background-radius: 20;
                -fx-padding: 6 12 6 12;
                """);

        return label;
    }

    private String formatearPrecio(double precio) {
        return "$" + formatoPrecio.format(precio);
    }

    private void abrirDetalleMoto(Moto moto) {
        try {
            DataStore.motoSeleccionada = moto;
            DataStore.historial.push(moto);

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("detallemoto.fxml"));

            Scene scene =
                    new Scene(loader.load());

            DetalleMotoController controller =
                    loader.getController();

            controller.setMoto(moto);

            Stage stage =
                    (Stage) flowPane.getScene().getWindow();

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
    }

    private void agregarFavorito(Moto moto) {
        if (!existeEnFavoritos(moto)) {
            DataStore.favoritos.add(moto);

            mostrarMensaje(
                    "Favoritos",
                    moto.getNombre() + " agregado a favoritos"
            );
        } else {
            mostrarMensaje(
                    "Favoritos",
                    "La moto ya esta en favoritos"
            );
        }
    }

    private boolean existeEnFavoritos(Moto moto) {
        for (Moto favorita : DataStore.favoritos) {
            if (favorita.getNombre().equalsIgnoreCase(moto.getNombre())) {
                return true;
            }
        }

        return false;
    }

    private void actualizarCatalogo() {
        String texto = txtBuscar.getText();

        if (texto == null) {
            texto = "";
        }

        texto = texto.trim().toLowerCase();

        String opcionPrecio = comboPrecio.getValue();
        String opcionCc = comboCc.getValue();

        List<Moto> resultado = new ArrayList<>();

        for (Moto moto : listaMotos) {
            if (cumpleBusqueda(moto, texto)
                    && cumplePrecio(moto, opcionPrecio)
                    && cumpleCc(moto, opcionCc)) {

                resultado.add(moto);
            }
        }

        mostrarMotos(resultado);
    }

    private boolean cumpleBusqueda(Moto moto, String texto) {
        if (texto.isEmpty()) {
            return true;
        }

        return moto.getNombre().toLowerCase().contains(texto)
                || moto.getMarca().toLowerCase().contains(texto);
    }

    private boolean cumplePrecio(Moto moto, String opcion) {
        if (opcion == null) {
            return true;
        }

        double precio = moto.getPrecio();

        if (opcion.equals("$2,500 - $6,000")) {
            return precio >= 2500 && precio <= 6000;
        }

        if (opcion.equals("$6,001 - $13,000")) {
            return precio >= 6001 && precio <= 13000;
        }

        if (opcion.equals("$13,001 - $40,000")) {
            return precio >= 13001 && precio <= 40000;
        }

        return true;
    }

    private boolean cumpleCc(Moto moto, String opcion) {
        if (opcion == null) {
            return true;
        }

        int cc = moto.getCilindraje();

        if (opcion.equals("Baja (125-300)")) {
            return cc >= 125 && cc <= 300;
        }

        if (opcion.equals("Media (301-500)")) {
            return cc >= 301 && cc <= 500;
        }

        if (opcion.equals("Alta (501-1000)")) {
            return cc >= 501 && cc <= 1000;
        }

        return true;
    }

    @FXML
    public void filtrarPrecio() {
        actualizarCatalogo();
    }

    @FXML
    public void filtrarCc() {
        actualizarCatalogo();
    }

    @FXML
    public void resetearCatalogo() {
        comboPrecio.setValue(null);
        comboCc.setValue(null);
        txtBuscar.clear();

        mostrarMotos(listaMotos);
    }

    @FXML
    public void abrirTienda() {
        mostrarMensaje(
                "Tienda",
                "Ya estas en la tienda"
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
        if (!Session.esAdmin()) {
            mostrarMensaje(
                    "Acceso denegado",
                    "Solo el administrador puede abrir este panel"
            );

            return;
        }

        abrirVentana("adminPanel.fxml");
    }

    private void abrirVentana(String archivo) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource(archivo));

            if (loader.getLocation() == null) {
                mostrarMensaje(
                        "Error",
                        "No existe: " + archivo
                );

                return;
            }

            Scene scene = new Scene(loader.load());

            Stage stage =
                    (Stage) flowPane.getScene().getWindow();

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

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
