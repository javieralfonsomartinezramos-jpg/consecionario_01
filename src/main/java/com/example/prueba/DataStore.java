package com.example.prueba;

import archivos.txt.ArchivoDatosUsuario;
import estructuras.Cola;
import estructuras.ListaDobleEnlazada;
import estructuras.Pila;

public class DataStore {

    // Catalogo principal: la lista doble facilita altas, bajas y recorridos.
    public static final ListaDobleEnlazada<Moto> motos =
            new ListaDobleEnlazada<>();

    // Datos vivos de la sesion actual.
    public static final ListaDobleEnlazada<Moto> carrito =
            new ListaDobleEnlazada<>();

    public static final ListaDobleEnlazada<Moto> favoritos =
            new ListaDobleEnlazada<>();

    // Historial LIFO: muestra primero la compra confirmada mas reciente.
    public static final Pila<Moto> historial =
            new Pila<>();

    public static PreferenciasUsuario preferencias =
            new PreferenciasUsuario();

    public static Moto motoSeleccionada;

    private DataStore() {
    }

    public static void cargarDatosUsuario() {
        if (Session.usuarioActual != null) {
            ArchivoDatosUsuario.cargarDatos(Session.usuarioActual);
        } else {
            limpiarDatosSesion();
        }
    }

    public static void guardarDatosUsuario() {
        if (Session.usuarioActual != null) {
            ArchivoDatosUsuario.guardarDatos(Session.usuarioActual);
        }
    }

    public static void guardarCarritoUsuario() {
        if (Session.usuarioActual != null) {
            ArchivoDatosUsuario.guardarCarrito(Session.usuarioActual);
        }
    }

    public static void guardarFavoritosUsuario() {
        if (Session.usuarioActual != null) {
            ArchivoDatosUsuario.guardarFavoritos(Session.usuarioActual);
        }
    }

    public static void guardarHistorialUsuario() {
        if (Session.usuarioActual != null) {
            ArchivoDatosUsuario.guardarHistorial(Session.usuarioActual);
        }
    }

    public static void guardarPreferenciasUsuario() {
        if (Session.usuarioActual != null) {
            ArchivoDatosUsuario.guardarPreferencias(Session.usuarioActual);
        }
    }

    public static void registrarCompra(
            Iterable<Moto> motosCompradas,
            double total
    ) {
        if (Session.usuarioActual != null) {
            ArchivoDatosUsuario.registrarCompra(
                    Session.usuarioActual,
                    motosCompradas,
                    total
            );
        }
    }

    public static void limpiarDatosSesion() {
        carrito.clear();
        favoritos.clear();
        historial.clear();
        preferencias = new PreferenciasUsuario();
        motoSeleccionada = null;
    }

    public static void agregarCarrito(Moto moto) {
        carrito.add(moto);
        guardarCarritoUsuario();
    }

    public static boolean agregarFavorito(Moto moto) {
        if (existeEnFavoritos(moto)) {
            return false;
        }

        favoritos.add(moto);
        guardarFavoritosUsuario();
        return true;
    }

    public static void registrarHistorial(Moto moto) {
        historial.push(moto);
        guardarHistorialUsuario();
    }

    public static int contarEnCarrito(Moto motoBuscada) {
        int contador = 0;

        for (Moto item : carrito) {
            if (mismoProducto(item, motoBuscada)) {
                contador++;
            }
        }

        return contador;
    }

    public static boolean existeEnFavoritos(Moto motoBuscada) {
        for (Moto favorita : favoritos) {
            if (mismoProducto(favorita, motoBuscada)) {
                return true;
            }
        }

        return false;
    }

    public static Moto buscarMotoPorNombre(String nombre) {
        for (Moto moto : motos) {
            if (moto.getNombre().equalsIgnoreCase(nombre)) {
                return moto;
            }
        }

        return null;
    }

    public static Cola<Moto> crearColaCompra() {
        Cola<Moto> cola = new Cola<>();

        for (Moto moto : carrito) {
            cola.offer(moto);
        }

        return cola;
    }

    public static void eliminarReferenciasDeMoto(Moto motoEliminada) {
        carrito.removeIf(moto -> mismoProducto(moto, motoEliminada));
        favoritos.removeIf(moto -> mismoProducto(moto, motoEliminada));
        historial.removeIf(moto -> mismoProducto(moto, motoEliminada));
        ArchivoDatosUsuario.eliminarMotoDeTodosLosUsuarios(motoEliminada);
        guardarDatosUsuario();
    }

    private static boolean mismoProducto(Moto primera, Moto segunda) {
        return primera != null
                && segunda != null
                && primera.getNombre().equalsIgnoreCase(segunda.getNombre());
    }
}
