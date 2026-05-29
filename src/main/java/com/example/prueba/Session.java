package com.example.prueba;

public class Session {

    public static final String ADMIN_NOMBRE = "admin";
    public static final String ADMIN_CORREO = "admin@gmail.com";
    public static final String ADMIN_CLAVE = "1234";

    // =========================================
    // USUARIO LOGEADO
    // =========================================

    public static Users usuarioActual;

    // =========================================
    // ADMIN
    // =========================================

    public static boolean admin = false;

    public static boolean esCredencialAdmin(
            String correo,
            String clave
    ) {

        return ADMIN_CORREO.equalsIgnoreCase(normalizar(correo))
                && ADMIN_CLAVE.equals(clave);
    }

    public static boolean esCorreoAdmin(String correo) {

        return ADMIN_CORREO.equalsIgnoreCase(normalizar(correo));
    }

    public static boolean esNombreAdmin(String nombre) {

        return ADMIN_NOMBRE.equalsIgnoreCase(normalizar(nombre));
    }

    public static boolean esIdentidadAdminReservada(
            String nombre,
            String correo
    ) {

        return esNombreAdmin(nombre) || esCorreoAdmin(correo);
    }

    public static void iniciarAdmin() {

        usuarioActual =
                new Users(
                        ADMIN_NOMBRE,
                        ADMIN_CORREO,
                        ADMIN_CLAVE
                );

        Users.usuarioActual = ADMIN_NOMBRE;
        admin = true;
    }

    public static void iniciarUsuario(Users usuario) {

        usuarioActual = usuario;
        Users.usuarioActual = usuario == null ? "" : usuario.getNombre();
        admin = false;
    }

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

        Users.usuarioActual = "";

        admin = false;
    }

    private static String normalizar(String texto) {

        return texto == null ? "" : texto.trim();
    }
}
