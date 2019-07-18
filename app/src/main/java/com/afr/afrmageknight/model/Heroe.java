package com.afr.afrmageknight.model;

import java.util.ArrayList;

public class Heroe {

    //Atributos
    private String nombre;
    private ArrayList<Cristal> cristales;

    //Constructores
    public Heroe() {
        // TODO Auto-generated constructor stub
    }

    public Heroe(String nombre, ArrayList<Cristal> cristales) {
        super();
        this.nombre = nombre;
        this.cristales = cristales;
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cristal> getCristales() {
        return cristales;
    }

    public void setCristales(ArrayList<Cristal> cristales) {
        this.cristales = cristales;
    }

    //Métodos

}
