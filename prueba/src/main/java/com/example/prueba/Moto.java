package com.example.prueba;

public class Moto {

    private String nombre;
    private String marca;
    private int cilindraje;
    private double precio;
    private int stock;

    public Moto(String nombre, String marca,
                int cilindraje,
                double precio,
                int stock) {

        this.nombre = nombre;
        this.marca = marca;
        this.cilindraje = cilindraje;
        this.precio = precio;
        this.stock = stock;
    }

    // GETTERS

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

    // SETTER DEL STOCK

    public void setStock(int stock) {
        this.stock = stock;
    }
}