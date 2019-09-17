package com.afr.afrmageknight.model;

public abstract class Carta {

    //Atributos
    private int numero;
    private String nombre;
    private boolean descartada;

    //Constructores
    public Carta() {
    }

    public Carta(int numero, String nombre, boolean descartada) {
        this.numero = numero;
        this.nombre = nombre;
        this.descartada = descartada;
    }

    //Getters & Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDescartada() {
        return descartada;
    }

    public void setDescartada(boolean descartada) {
        this.descartada = descartada;
    }

    //Métodos

}
