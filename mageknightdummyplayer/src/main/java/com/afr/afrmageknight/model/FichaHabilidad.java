package com.afr.afrmageknight.model;

public class FichaHabilidad {

    //Atributos
    private int id;
    private String nombre;
    private String descripcion;
    private Heroe heroe;

    //Constructores
    public FichaHabilidad() {
    }

    public FichaHabilidad(int id, String nombre, String descripcion, Heroe heroe) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.heroe = heroe;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    //Métodos

}
