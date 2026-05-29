package com.example.prueba;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public final class UI {

    private static final double ANCHO_BASE = 1366;
    private static final double ALTO_BASE = 768;
    private static final double ANCHO_MINIMO = 1100;
    private static final double ALTO_MINIMO = 700;

    private UI() {
    }

    public static void aplicarEstilos(Scene scene) {
        URL css = UI.class.getResource("styles/app.css");

        if (css != null
                && !scene.getStylesheets().contains(css.toExternalForm())) {
            scene.getStylesheets().add(css.toExternalForm());
        }
    }

    public static Scene crearEscena(Parent root) {
        Scene scene = new Scene(root, ANCHO_BASE, ALTO_BASE);
        aplicarEstilos(scene);
        return scene;
    }

    public static Scene crearEscena(
            Parent root,
            double ancho,
            double alto
    ) {
        Scene scene = new Scene(root, ancho, alto);
        aplicarEstilos(scene);
        return scene;
    }

    public static void mostrarMaximizado(
            Stage stage,
            Scene scene
    ) {
        aplicarEstilos(scene);

        stage.setMaximized(false);
        stage.setScene(scene);
        stage.setMinWidth(ANCHO_MINIMO);
        stage.setMinHeight(ALTO_MINIMO);
        stage.setWidth(ANCHO_BASE);
        stage.setHeight(ALTO_BASE);
        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.show();
    }

    public static void aplicarHoverElevado(Node nodo) {
        nodo.setOnMouseEntered(event -> animarEscala(nodo, 1.03));
        nodo.setOnMouseExited(event -> animarEscala(nodo, 1.0));
    }

    private static void animarEscala(
            Node nodo,
            double escala
    ) {
        ScaleTransition transition =
                new ScaleTransition(
                        Duration.millis(150),
                        nodo
                );

        transition.setToX(escala);
        transition.setToY(escala);
        transition.play();
    }
}
