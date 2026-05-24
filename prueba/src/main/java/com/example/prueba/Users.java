package com.example.prueba;

public class Users {

    private String nombre;
    private String correo;
    private String clave;

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

    public String toFileString() {
        return nombre + "," + correo + "," + clave;
    }
}
