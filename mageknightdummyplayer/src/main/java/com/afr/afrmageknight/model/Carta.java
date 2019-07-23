package com.afr.afrmageknight.model;

public abstract class Carta {

    //Atributos
    private int numero;
    private String nombre;

    //Constructores
    public Carta() {

    }

    public Carta(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
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

    //Métodos

}
