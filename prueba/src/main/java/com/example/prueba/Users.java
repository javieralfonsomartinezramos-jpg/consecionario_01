package com.example.prueba;

public class Users {

    private String nombre;
    private String correo;
    private String clave;

    // variable global para guardar nombre del usuario logueado
    public static String usuarioActual = "";

    public Users(String nombre, String correo, String clave) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String toFileString() {
        return nombre + "," + correo + "," + clave;
    }
}