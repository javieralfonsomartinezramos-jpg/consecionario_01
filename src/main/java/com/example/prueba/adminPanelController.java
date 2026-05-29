package com.example.prueba;

import archivos.txt.ArchivoMotos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class adminPanelController {

    // =========================
    // FXML
    // =========================

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> comboMarca;

    @FXML
    private TextField txtCc;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtImagen;

    @FXML
    private TableView<Moto> tablaMotos;

    @FXML
    private TableColumn<Moto, String> colNombre;

    @FXML
    private TableColumn<Moto, String> colMarca;

    @FXML
    private TableColumn<Moto, Integer> colCc;

    @FXML
    private TableColumn<Moto, Double> colPrecio;

    @FXML
    private TableColumn<Moto, Integer> colStock;

    @FXML
    private TableColumn<Moto, String> colImagen;

    @FXML
    private Button btnGuardar;

    private final ObservableList<Moto> motosTabla =
            FXCollections.observableArrayList();

    private Moto motoEditando;

    // =========================
    // INITIALIZE
    // =========================

    @FXML
    public void initialize() {

        comboMarca.setItems(

                FXCollections.observableArrayList(

                        "Honda",
                        "Yamaha",
                        "Suzuki",
                        "Kawasaki",
                        "BMW",
                        "Ducati",
                        "KTM",
                        "Bajaj",
                        "Otro"

                )
        );

        configurarTabla();

        cargarMotos();
    }

    private void configurarTabla() {

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colMarca.setCellValueFactory(
                new PropertyValueFactory<>("marca")
        );

        colCc.setCellValueFactory(
                new PropertyValueFactory<>("cilindraje")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        colImagen.setCellValueFactory(
                new PropertyValueFactory<>("imagen")
        );

        tablaMotos.setItems(motosTabla);

        tablaMotos.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, anterior, seleccionada) -> {

                            if (seleccionada != null) {
                                cargarMotoEnFormulario(seleccionada);
                            }
                        }
                );
    }

    private void cargarMotos() {

        if (DataStore.motos.isEmpty()) {
            DataStore.motos.addAll(
                    ArchivoMotos.cargarMotos()
            );
        }

        motosTabla.setAll(DataStore.motos.toList());
    }

    // =========================
    // GUARDAR / EDITAR MOTO
    // =========================

    @FXML
    public void guardarMoto() {

        String nombre =
                txtNombre.getText().trim();

        String marca =
                comboMarca.getValue();

        String ccTexto =
                txtCc.getText().trim();

        String precioTexto =
                limpiarNumero(
                        txtPrecio.getText()
                );

        String stockTexto =
                txtStock.getText().trim();

        String imagen =
                txtImagen.getText().trim();

        if (!validarCampos(
                nombre,
                marca,
                ccTexto,
                precioTexto,
                stockTexto,
                imagen
        )) {
            return;
        }

        int cc =
                Integer.parseInt(ccTexto);

        double precio =
                Double.parseDouble(precioTexto);

        int stock =
                Integer.parseInt(stockTexto);

        if (motoEditando == null
                && existeMoto(nombre)) {

            mostrarMensaje(
                    "Producto repetido",
                    "Ya existe una moto con ese nombre"
            );

            return;
        }

        if (motoEditando != null
                && existeOtraMoto(nombre, motoEditando)) {

            mostrarMensaje(
                    "Producto repetido",
                    "Ya existe otra moto con ese nombre"
            );

            return;
        }

        if (motoEditando == null) {

            Moto nuevaMoto =
                    new Moto(
                            nombre,
                            marca,
                            cc,
                            precio,
                            stock,
                            imagen
                    );

            DataStore.motos.add(nuevaMoto);

            mostrarMensaje(
                    "Exito",
                    "Moto agregada correctamente"
            );

        } else {

            motoEditando.setNombre(nombre);
            motoEditando.setMarca(marca);
            motoEditando.setCilindraje(cc);
            motoEditando.setPrecio(precio);
            motoEditando.setStock(stock);
            motoEditando.setImagen(imagen);

            mostrarMensaje(
                    "Exito",
                    "Moto actualizada correctamente"
            );
        }

        guardarCambios();

        limpiarCamposFormulario();
    }

    private boolean validarCampos(
            String nombre,
            String marca,
            String ccTexto,
            String precioTexto,
            String stockTexto,
            String imagen
    ) {

        if (nombre.isEmpty()
                || marca == null
                || ccTexto.isEmpty()
                || precioTexto.isEmpty()
                || stockTexto.isEmpty()
                || imagen.isEmpty()) {

            mostrarMensaje(
                    "Error",
                    "Completa todos los campos"
            );

            return false;
        }

        if (nombre.contains(",")
                || marca.contains(",")
                || imagen.contains(",")) {

            mostrarMensaje(
                    "Error",
                    "No uses comas en nombre, marca o imagen"
            );

            return false;
        }

        if (!ccTexto.matches("\\d+")
                || !precioTexto.matches("\\d+(\\.\\d+)?")
                || !stockTexto.matches("\\d+")) {

            mostrarMensaje(
                    "Error",
                    "Cilindraje, precio y stock deben ser numericos"
            );

            return false;
        }

        if (Integer.parseInt(ccTexto) <= 0
                || Double.parseDouble(precioTexto) <= 0
                || Integer.parseInt(stockTexto) < 0) {

            mostrarMensaje(
                    "Error",
                    "Cilindraje y precio deben ser mayores a 0"
            );

            return false;
        }

        return true;
    }

    private String limpiarNumero(String texto) {

        return texto
                .trim()
                .replace("$", "")
                .replace(" ", "")
                .replace(",", "");
    }

    private boolean existeMoto(String nombre) {

        for (Moto moto : DataStore.motos) {

            if (moto.getNombre()
                    .equalsIgnoreCase(nombre)) {

                return true;
            }
        }

        return false;
    }

    private boolean existeOtraMoto(String nombre, Moto actual) {

        for (Moto moto : DataStore.motos) {

            if (moto != actual
                    && moto.getNombre()
                    .equalsIgnoreCase(nombre)) {

                return true;
            }
        }

        return false;
    }

    private void guardarCambios() {

        ArchivoMotos.guardarTodasLasMotos(
                DataStore.motos
        );

        motosTabla.setAll(DataStore.motos.toList());

        tablaMotos.refresh();
    }

    // =========================
    // SELECCIONAR / ELIMINAR
    // =========================

    private void cargarMotoEnFormulario(Moto moto) {

        motoEditando = moto;

        txtNombre.setText(
                moto.getNombre()
        );

        comboMarca.setValue(
                moto.getMarca()
        );

        txtCc.setText(
                String.valueOf(
                        moto.getCilindraje()
                )
        );

        txtPrecio.setText(
                String.valueOf(
                        moto.getPrecio()
                )
        );

        txtStock.setText(
                String.valueOf(
                        moto.getStock()
                )
        );

        txtImagen.setText(
                moto.getImagen()
        );

        btnGuardar.setText(
                "ACTUALIZAR PRODUCTO"
        );
    }

    @FXML
    public void eliminarMoto() {

        Moto seleccionada =
                tablaMotos.getSelectionModel()
                        .getSelectedItem();

        if (seleccionada == null) {

            mostrarMensaje(
                    "Selecciona una moto",
                    "Elige un producto de la tabla para eliminarlo"
            );

            return;
        }

        DataStore.motos.remove(seleccionada);

        DataStore.eliminarReferenciasDeMoto(seleccionada);

        guardarCambios();

        limpiarCamposFormulario();

        mostrarMensaje(
                "Eliminado",
                "Moto eliminada del catalogo"
        );
    }

    // =========================
    // LIMPIAR
    // =========================

    @FXML
    public void limpiarCamposFormulario() {

        motoEditando = null;

        tablaMotos.getSelectionModel()
                .clearSelection();

        txtNombre.clear();

        comboMarca.setValue(null);

        txtCc.clear();

        txtPrecio.clear();

        txtStock.clear();

        txtImagen.clear();

        btnGuardar.setText(
                "GUARDAR PRODUCTO"
        );
    }

    // =========================
    // VOLVER
    // =========================

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
                    UI.crearEscena(loader.load());

            Stage stage =
                    (Stage) txtNombre
                            .getScene()
                            .getWindow();

            UI.mostrarMaximizado(stage, scene);

        } catch (Exception e) {

            e.printStackTrace();

            mostrarMensaje(
                    "Error",
                    "No se pudo volver al catalogo"
            );
        }
    }

    // =========================
    // ALERTAS
    // =========================

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
