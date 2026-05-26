package com.example.prueba;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CatalogoController {

    // ADMIN
    public static boolean esAdmin;

    // PILA HISTORIAL
    private Stack<Moto> historial = new Stack<>();

    // BOTONES
    @FXML private Button a_cbr;
    @FXML private Button a_ninja;
    @FXML private Button a_mt15;
    @FXML private Button a_R1;
    @FXML private Button a_gsx;
    @FXML private Button a_mt07;
    @FXML private Button a_ns200;
    @FXML private Button a_bmw;
    @FXML private Button a_cbf125;
    @FXML private Button a_cbr_blanca;
    @FXML private Button a_cbr1000rr;
    @FXML private Button a_z650;
    @FXML private Button a_rc390;
    @FXML private Button a_gixxer250;
    @FXML private Button a_r3;

    @FXML private Button btnAdmin;

    // LABEL
    @FXML private Label lblUsuario;

    // COMBOBOX
    @FXML private ComboBox<String> comboPrecio;
    @FXML private ComboBox<String> comboCc;

    // LISTA DE MOTOS
    private List<Moto> listaMotos = new ArrayList<>();

    // MOTOS
    private Moto hondaCbr600 =
            new Moto("Honda CBR 600RR", "Honda", 600, 12000, 5);

    private Moto ninja400 =
            new Moto("Kawasaki Ninja 400", "Kawasaki", 400, 6500, 5);

    private Moto mt15 =
            new Moto("Yamaha MT 15", "Yamaha", 155, 5000, 5);

    private Moto r1 =
            new Moto("Yamaha R1", "Yamaha", 1000, 18000, 5);

    private Moto gsx1000 =
            new Moto("Suzuki GSX-R 1000", "Suzuki", 1000, 15500, 5);

    private Moto mt07 =
            new Moto("Yamaha MT 07", "Yamaha", 700, 8500, 5);

    private Moto pulsarNs200 =
            new Moto("Bajaj Pulsar NS 200", "Bajaj", 200, 3000, 5);

    private Moto bmwM1000 =
            new Moto("BMW M 1000 RR", "BMW", 1000, 35000, 5);

    private Moto cbf125 =
            new Moto("Honda CBF 125", "Honda", 125, 2500, 5);

    private Moto cbr600Blanca =
            new Moto("Honda CBR 600RR Blanca", "Honda", 600, 12500, 5);

    private Moto cbr1000 =
            new Moto("Honda CBR 1000RR", "Honda", 1000, 20000, 5);

    private Moto z650 =
            new Moto("Kawasaki Z650", "Kawasaki", 650, 8000, 5);

    private Moto rc390 =
            new Moto("KTM RC 390", "KTM", 390, 6000, 5);

    private Moto gixxer250 =
            new Moto("Suzuki Gixxer SF 250", "Suzuki", 250, 4500, 5);

    private Moto yzfR3 =
            new Moto("Yamaha YZF R3", "Yamaha", 320, 6000, 5);

    @FXML
    public void initialize() {

        lblUsuario.setText(
                "Bienvenido " + Users.usuarioActual
        );

        if (!esAdmin) {
            btnAdmin.setVisible(false);
            btnAdmin.setManaged(false);
        }

        // llenar lista
        listaMotos.add(hondaCbr600);
        listaMotos.add(ninja400);
        listaMotos.add(mt15);
        listaMotos.add(r1);
        listaMotos.add(gsx1000);
        listaMotos.add(mt07);
        listaMotos.add(pulsarNs200);
        listaMotos.add(bmwM1000);
        listaMotos.add(cbf125);
        listaMotos.add(cbr600Blanca);
        listaMotos.add(cbr1000);
        listaMotos.add(z650);
        listaMotos.add(rc390);
        listaMotos.add(gixxer250);
        listaMotos.add(yzfR3);

        // filtros
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

        // botones
        a_cbr.setOnAction(e -> comprarMoto(hondaCbr600));
        a_ninja.setOnAction(e -> comprarMoto(ninja400));
        a_mt15.setOnAction(e -> comprarMoto(mt15));
        a_R1.setOnAction(e -> comprarMoto(r1));
        a_gsx.setOnAction(e -> comprarMoto(gsx1000));
        a_mt07.setOnAction(e -> comprarMoto(mt07));
        a_ns200.setOnAction(e -> comprarMoto(pulsarNs200));
        a_bmw.setOnAction(e -> comprarMoto(bmwM1000));
        a_cbf125.setOnAction(e -> comprarMoto(cbf125));
        a_cbr_blanca.setOnAction(e -> comprarMoto(cbr600Blanca));
        a_cbr1000rr.setOnAction(e -> comprarMoto(cbr1000));
        a_z650.setOnAction(e -> comprarMoto(z650));
        a_rc390.setOnAction(e -> comprarMoto(rc390));
        a_gixxer250.setOnAction(e -> comprarMoto(gixxer250));
        a_r3.setOnAction(e -> comprarMoto(yzfR3));
    }

    private void comprarMoto(Moto moto) {

        historial.push(moto);

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Compra");
        alert.setHeaderText(null);
        alert.setContentText(
                "Agregaste: " + moto.getNombre()
        );

        alert.show();
    }

    @FXML
    public void seleccionarMoto(MouseEvent event) {

        VBox panel =
                (VBox) event.getSource();

        String idMoto =
                panel.getId();

        mostrar(
                "Catálogo",
                "Seleccionaste: " + idMoto
        );
    }

    @FXML
    public void filtrarPrecio() {

        String opcion =
                comboPrecio.getValue();

        if (opcion == null) return;

        List<Moto> filtradas =
                new ArrayList<>();

        for (Moto m : listaMotos) {

            if (opcion.equals("2500 - 6000")
                    && m.getPrecio() >= 2500
                    && m.getPrecio() <= 6000)
                filtradas.add(m);

            if (opcion.equals("6001 - 13000")
                    && m.getPrecio() >= 6001
                    && m.getPrecio() <= 13000)
                filtradas.add(m);

            if (opcion.equals("13001 - 40000")
                    && m.getPrecio() >= 13001
                    && m.getPrecio() <= 40000)
                filtradas.add(m);
        }

        mostrarResultado(filtradas);
    }

    @FXML
    public void filtrarCc() {

        String opcion =
                comboCc.getValue();

        if (opcion == null) return;

        List<Moto> filtradas =
                new ArrayList<>();

        for (Moto m : listaMotos) {

            if (opcion.equals("Baja (125-300)")
                    && m.getCilindraje() >= 125
                    && m.getCilindraje() <= 300)
                filtradas.add(m);

            if (opcion.equals("Media (301-500)")
                    && m.getCilindraje() >= 301
                    && m.getCilindraje() <= 500)
                filtradas.add(m);

            if (opcion.equals("Alta (501-1000)")
                    && m.getCilindraje() >= 501
                    && m.getCilindraje() <= 1000)
                filtradas.add(m);
        }

        mostrarResultado(filtradas);
    }

    private void mostrarResultado(List<Moto> motos) {

        String texto = "";

        for (Moto m : motos) {
            texto += m.getNombre() + "\n";
        }

        mostrar(
                "Resultado",
                texto.isEmpty()
                        ? "No hay motos en este rango."
                        : texto
        );
    }

    @FXML
    public void resetearCatalogo() {

        comboPrecio.setValue(null);
        comboCc.setValue(null);

        mostrar(
                "Reset",
                "Filtros reiniciados"
        );
    }

    @FXML
    public void abrirTienda() {
        mostrar(
                "Tienda",
                "Ya estás en la tienda"
        );
    }

    @FXML
    public void abrirCarrito() {
        mostrar(
                "Carrito",
                "Abriendo carrito..."
        );
    }

    @FXML
    public void abrirHistorial() {

        String texto = "";

        for (Moto m : historial) {
            texto += m.getNombre() + "\n";
        }

        mostrar(
                "Historial",
                texto.isEmpty()
                        ? "El historial está vacío."
                        : texto
        );
    }

    @FXML
    public void abrirAdminPanel() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "adminpanel.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            Stage stage =
                    (Stage) btnAdmin
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrar(String titulo,
                         String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}