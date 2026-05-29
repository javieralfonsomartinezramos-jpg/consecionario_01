package com.example.prueba;

public class PreferenciasUsuario {

    private String filtroPrecio = "";
    private String filtroCc = "";
    private String busqueda = "";
    private String tema = "oscuro";

    public String getFiltroPrecio() {
        return filtroPrecio;
    }

    public void setFiltroPrecio(String filtroPrecio) {
        this.filtroPrecio = normalizar(filtroPrecio);
    }

    public String getFiltroCc() {
        return filtroCc;
    }

    public void setFiltroCc(String filtroCc) {
        this.filtroCc = normalizar(filtroCc);
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = normalizar(busqueda);
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        String valor = normalizar(tema);
        this.tema = valor.isEmpty() ? "oscuro" : valor;
    }

    private String normalizar(String texto) {
        return texto == null ? "" : texto.trim();
    }
}
