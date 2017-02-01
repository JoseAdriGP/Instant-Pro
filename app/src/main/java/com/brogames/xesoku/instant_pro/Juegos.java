package com.brogames.xesoku.instant_pro;

/**
 * Created by Xesoku on 19/01/2017.
 */

public class Juegos {
    private int id;
    private String nombre;
    private double precio;
    private int descuento;
    private int plataforma;
    private String keyWord;

    public Juegos(int id, String nombre, double precio, int descuento, int plataforma, String keyWord) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descuento = descuento;
        this.plataforma = plataforma;
        this.keyWord = keyWord;
    }

    public Juegos() {
        this.id = -1;
        this.nombre = "";
        this.precio = -1;
        this.descuento = -1;
        this.plataforma = -1;
        this.keyWord = "";
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public int getPlataforma() {
        return plataforma;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public void setPlataforma(int plataforma) {
        this.plataforma = plataforma;
    }
}
