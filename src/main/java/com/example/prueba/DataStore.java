package com.example.prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DataStore {

    // =========================
    // CATALOGO
    // =========================

    public static List<Moto> motos =
            new ArrayList<>();

    // =========================
    // CARRITO
    // =========================

    public static List<Moto> carrito =
            new ArrayList<>();

    // =========================
    // FAVORITOS
    // =========================

    public static List<Moto> favoritos =
            new ArrayList<>();

    // =========================
    // HISTORIAL
    // =========================

    public static Stack<Moto> historial =
            new Stack<>();

    public static Moto motoSeleccionada;
}
