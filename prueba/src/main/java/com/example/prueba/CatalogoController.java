package com.example.prueba;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CatalogoController {

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
    private TableView<Moto> tablaMotos;

    @FXML
    private TableColumn<Moto, String> colNombre;

    @FXML
    private TableColumn<Moto, String> colMarca;

    @FXML
    private TableColumn<Moto, Integer> colCilindraje;

    @FXML
    private TableColumn<Moto, Double> colPrecio;

    @FXML
    private TableColumn<Moto, Integer> colStock;

    private ObservableList<Moto> listaMotos =
            FXCollections.observableArrayList();

    // STOCK INICIAL = 5

    private Moto honda =
            new Moto("CBR 600RR", "Honda", 600, 12000, 5);

    private Moto ninja =
            new Moto("Ninja 400", "Kawasaki", 400, 6500, 5);

    private Moto mt15 =
            new Moto("MT 15", "Yamaha", 200, 5000, 5);

    private Moto r1 =
            new Moto("R1", "Yamaha", 1000, 18000, 5);

    private Moto gsx =
            new Moto("GSX-R 1000", "Suzuki", 1000, 15500, 5);

    private Moto mt09 =
            new Moto("MT 09", "Yamaha", 900, 10500, 5);

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        colMarca.setCellValueFactory(
                new PropertyValueFactory<>("marca"));

        colCilindraje.setCellValueFactory(
                new PropertyValueFactory<>("cilindraje"));

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio"));

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));

        listaMotos.addAll(
                honda,
                ninja,
                mt15,
                r1,
                gsx,
                mt09
        );

        tablaMotos.setItems(listaMotos);

        // BOTONES

        a_cbr.setOnAction(e -> comprarMoto(honda));

        a_ninja.setOnAction(e -> comprarMoto(ninja));

        a_mt15.setOnAction(e -> comprarMoto(mt15));

        a_R1.setOnAction(e -> comprarMoto(r1));

        a_gsx.setOnAction(e -> comprarMoto(gsx));

        a_mt09.setOnAction(e -> comprarMoto(mt09));
    }
//   COMPRA
    private void comprarMoto(Moto moto) {

        if (moto.getStock() > 0) {

            moto.setStock(moto.getStock() - 1);

            tablaMotos.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Compra realizada");

            alert.setHeaderText(null);

            alert.setContentText(
                    "Agregaste una "
                            + moto.getNombre()
                            + "\nStock restante: "
                            + moto.getStock()
            );

            alert.show();

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Sin stock");

            alert.setHeaderText(null);

            alert.setContentText(
                    "La moto "
                            + moto.getNombre()
                            + " está agotada."
            );

            alert.show();
        }
    }
}