package com.example.prueba;

public class Session {

    // =========================================
    // USUARIO LOGEADO
    // =========================================

    public static Users usuarioActual;

    // =========================================
    // ADMIN
    // =========================================

    public static boolean admin = false;

    // =========================================
    // VALIDAR SI ES ADMIN
    // =========================================

    public static boolean esAdmin() {

        return admin;
    }

    // =========================================
    // CERRAR SESION
    // =========================================

    public static void cerrarSesion() {

        usuarioActual = null;

        admin = false;
    }
}