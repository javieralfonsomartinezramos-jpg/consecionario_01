package com.example.prueba;

public class Moto {

    // =========================
    // ATRIBUTOS
    // =========================

    private String nombre;

    private String marca;

    private int cilindraje;

    private double precio;

    private int stock;

    private String imagen;

    // =========================
    // CONSTRUCTOR
    // =========================

    public Moto(
            String nombre,
            String marca,
            int cilindraje,
            double precio,
            int stock,
            String imagen
    ) {

        this.nombre = nombre;

        this.marca = marca;

        this.cilindraje = cilindraje;

        this.precio = precio;

        this.stock = stock;

        this.imagen = imagen;
    }

    // =========================
    // GETTERS
    // =========================

    public String getNombre() {

        return nombre;
    }

    public String getMarca() {

        return marca;
    }

    public int getCilindraje() {

        return cilindraje;
    }

    public double getPrecio() {

        return precio;
    }

    public int getStock() {

        return stock;
    }

    public String getImagen() {

        return imagen;
    }

    // =========================
    // SETTERS
    // =========================

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setMarca(String marca) {

        this.marca = marca;
    }

    public void setCilindraje(int cilindraje) {

        this.cilindraje = cilindraje;
    }

    public void setPrecio(double precio) {

        this.precio = precio;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public void setImagen(String imagen) {

        this.imagen = imagen;
    }

    // =========================
    // TO STRING
    // =========================

    @Override
    public String toString() {

        return "Moto{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", cilindraje=" + cilindraje +
                ", precio=" + precio +
                ", stock=" + stock +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}