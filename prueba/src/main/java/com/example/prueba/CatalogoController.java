package com.example.prueba;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CatalogoController {

    // ADMIN
    public static boolean esAdmin;

    // PILA HISTORIAL
    private Stack<Moto> historial = new Stack<>();

    // BOTONES
    @FXML
    private Button a_cbr;

    @FXML
    private Button a_ninja;

    @FXML
    private Button a_mt15;

    @FXML
    private Button a_R1;

    @FXML
    private Button a_gsx;

    @FXML
    private Button a_mt09;

    @FXML
    private Button btnAdmin;

    // LABEL
    @FXML
    private Label lblUsuario;

    // COMBOBOX
    @FXML
    private ComboBox<String> comboPrecio;

    @FXML
    private ComboBox<String> comboCc;

    // LISTA
    private List<Moto> listaMotos = new ArrayList<>();


    // MOTOS
    private Moto honda =
            new Moto("CBR 600RR",
                    "Honda",
                    600,
                    12000,
                    5);

    private Moto ninja =
            new Moto("Ninja 400",
                    "Kawasaki",
                    400,
                    6500,
                    5);

    private Moto mt15 =
            new Moto("MT 15",
                    "Yamaha",
                    200,
                    5000,
                    5);

    private Moto r1 =
            new Moto("R1",
                    "Yamaha",
                    1000,
                    18000,
                    5);

    private Moto gsx =
            new Moto("GSX-R 1000",
                    "Suzuki",
                    1000,
                    15500,
                    5);

    private Moto mt09 =
            new Moto("MT 09",
                    "Yamaha",
                    900,
                    10500,
                    5);


    @FXML
    public void initialize() {

        // mostrar nombre real
        lblUsuario.setText(
                "Bienvenido " +
                        Users.usuarioActual
        );

        // ocultar admin
        if (!esAdmin) {
            btnAdmin.setVisible(false);
            btnAdmin.setManaged(false);
        }

        // llenar lista
        listaMotos.add(honda);
        listaMotos.add(ninja);
        listaMotos.add(mt15);
        listaMotos.add(r1);
        listaMotos.add(gsx);
        listaMotos.add(mt09);

        // combo precio
        comboPrecio.getItems().addAll(
                "5000 - 7000",
                "7001 - 12000",
                "12001 - 20000"
        );

        // combo cc
        comboCc.getItems().addAll(
                "Baja (125-300)",
                "Media (301-500)",
                "Alta (501-1000)"
        );

        // botones motos
        a_cbr.setOnAction(e -> comprarMoto(honda));
        a_ninja.setOnAction(e -> comprarMoto(ninja));
        a_mt15.setOnAction(e -> comprarMoto(mt15));
        a_R1.setOnAction(e -> comprarMoto(r1));
        a_gsx.setOnAction(e -> comprarMoto(gsx));
        a_mt09.setOnAction(e -> comprarMoto(mt09));
    }


    private void comprarMoto(Moto moto) {

        historial.push(moto);

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Compra");
        alert.setHeaderText(null);
        alert.setContentText(
                "Agregaste: " +
                        moto.getNombre()
        );

        alert.show();
    }


    @FXML
    public void filtrarPrecio() {

        String opcion =
                comboPrecio.getValue();

        if (opcion == null) return;

        List<Moto> filtradas =
                new ArrayList<>();

        for (Moto m : listaMotos) {

            if (opcion.equals("5000 - 7000")
                    && m.getPrecio() >= 5000
                    && m.getPrecio() <= 7000)
                filtradas.add(m);

            if (opcion.equals("7001 - 12000")
                    && m.getPrecio() >= 7001
                    && m.getPrecio() <= 12000)
                filtradas.add(m);

            if (opcion.equals("12001 - 20000")
                    && m.getPrecio() >= 12001
                    && m.getPrecio() <= 20000)
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

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(texto);

        alert.show();
    }


    @FXML
    public void resetearCatalogo() {

        comboPrecio.setValue(null);
        comboCc.setValue(null);

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Reset");
        alert.setHeaderText(null);
        alert.setContentText(
                "Filtros reiniciados"
        );

        alert.show();
    }


    @FXML
    public void abrirTienda() {
        mostrar("Tienda",
                "Ya estás en la tienda");
    }

    @FXML
    public void abrirCarrito() {
        mostrar("Carrito",
                "Abriendo carrito...");
    }

    @FXML
    public void abrirHistorial() {

        String texto = "";

        for (Moto m : historial) {
            texto += m.getNombre() + "\n";
        }

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Historial");
        alert.setHeaderText(null);
        alert.setContentText(texto);

        alert.show();
    }

    @FXML
    public void abrirAdminPanel() {
        mostrar("Admin",
                "Bienvenido administrador");
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