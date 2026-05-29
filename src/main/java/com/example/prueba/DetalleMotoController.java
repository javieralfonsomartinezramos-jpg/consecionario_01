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

        String nombreNormalizado =
                moto.getNombre().toLowerCase();

        if (nombreNormalizado.contains("mt 07")) {
            return "Ligera, fuerte y muy facil de llevar. La MT 07 combina respuesta inmediata con una postura comoda, perfecta para ciudad, salidas de fin de semana y para quien busca una naked con caracter sin complicarse.";
        }

        if (nombreNormalizado.contains("r1")) {
            return "Una deportiva de alto nivel, hecha para sentir precision desde el primer giro del acelerador. La R1 destaca por su entrega agresiva, posicion de manejo deportiva y una presencia que no pasa desapercibida.";
        }

        if (nombreNormalizado.contains("cbr 600")) {
            return "Equilibrada, rapida y muy tecnica. Esta CBR mantiene el espiritu deportivo de Honda con una conduccion precisa, buen control en curva y potencia suficiente para disfrutar cada tramo.";
        }

        if (nombreNormalizado.contains("cbr 1000")) {
            return "Potencia seria con una respuesta muy cuidada. La CBR 1000RR esta pensada para quienes quieren una superbike con caracter, tecnologia y una sensacion de manejo firme en todo momento.";
        }

        if (nombreNormalizado.contains("ninja 400")) {
            return "Una deportiva liviana que se siente agil desde el primer dia. La Ninja 400 es ideal para subir de nivel, con buena potencia, postura comoda y una imagen claramente racing.";
        }

        if (nombreNormalizado.contains("z650")) {
            return "Naked practica, compacta y con buena fuerza en medios. La Z650 funciona muy bien para uso diario, pero tambien responde cuando quieres una salida con ritmo.";
        }

        if (nombreNormalizado.contains("mt 15")) {
            return "Compacta, economica y con estilo urbano. La MT 15 es una buena companera para moverse rapido por la ciudad sin perder ese look agresivo de la familia MT.";
        }

        if (nombreNormalizado.contains("gsx-r 1000")) {
            return "Deportiva pura, con una entrega de potencia contundente y una postura enfocada en rendimiento. La GSX-R 1000 es para quien busca sensaciones fuertes y manejo preciso.";
        }

        if (nombreNormalizado.contains("gixxer")) {
            return "Deportiva de baja cilindrada con buena presencia y consumo contenido. La Gixxer SF 250 se siente practica para el dia a dia y atractiva para quienes quieren estilo sport.";
        }

        if (nombreNormalizado.contains("pulsar")) {
            return "Una moto versatil, confiable y facil de mantener. La Pulsar NS 200 tiene buena respuesta para ciudad, postura comoda y el toque deportivo justo para disfrutarla todos los dias.";
        }

        if (nombreNormalizado.contains("m 1000")) {
            return "Una maquina enfocada en rendimiento, con presencia premium y ADN de pista. La BMW M 1000 RR esta hecha para quien busca exclusividad, potencia y tecnologia de alto nivel.";
        }

        if (nombreNormalizado.contains("cbf 125")) {
            return "Sencilla, rendidora y perfecta para moverse todos los dias. La CBF 125 prioriza bajo consumo, comodidad y mantenimiento facil, sin perder la confianza de Honda.";
        }

        if (nombreNormalizado.contains("rc 390")) {
            return "Ligera, afilada y con actitud deportiva. La KTM RC 390 ofrece una conduccion divertida, buen empuje y una posicion pensada para quienes disfrutan manejar con precision.";
        }

        if (nombreNormalizado.contains("yzf r3")) {
            return "Deportiva accesible, suave y muy entretenida. La YZF R3 combina diseno racing con una respuesta progresiva, ideal para aprender, mejorar y disfrutar rutas con confianza.";
        }

        return crearDescripcionGenerica(moto);
    }

    private String crearDescripcionGenerica(Moto moto) {

        if (moto.getCilindraje() <= 300) {
            return "Una opcion practica para moverse a diario, con consumo contenido, manejo sencillo y una presencia que se defiende muy bien en ciudad.";
        }

        if (moto.getCilindraje() <= 650) {
            return "Buen equilibrio entre potencia, comodidad y control. Es una moto pensada para quienes quieren usarla seguido, pero tambien disfrutarla cuando el camino se abre.";
        }

        return "Una propuesta de alto rendimiento, con fuerza de sobra y una conduccion pensada para quienes buscan sensaciones deportivas y mayor presencia en carretera.";
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
                DataStore.contarEnCarrito(moto);

        if (moto.getStock() <= 0
                || cantidadEnCarrito >= moto.getStock()) {

            mostrarMensaje(
                    "Sin stock",
                    "No hay mas unidades disponibles para agregar"
            );

            return;
        }

        DataStore.agregarCarrito(moto);

        actualizarStock();

        mostrarMensaje(
                "Carrito",
                moto.getNombre()
                        + " agregado al carrito"
        );
    }

    private int contarMotoEnCarrito(Moto motoBuscada) {
        return DataStore.contarEnCarrito(motoBuscada);
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

        if (DataStore.agregarFavorito(moto)) {
            mostrarMensaje(
                    "Favoritos",
                    moto.getNombre()
                            + " agregado a favoritos"
            );
        } else {
            mostrarMensaje(
                    "Favoritos",
                    "La moto ya esta en favoritos"
            );
        }
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
                    UI.crearEscena(loader.load());

            Stage stage =
                    (Stage) lblNombre
                            .getScene()
                            .getWindow();

            UI.mostrarMaximizado(stage, scene);

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
