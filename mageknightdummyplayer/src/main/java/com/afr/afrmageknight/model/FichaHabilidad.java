package com.afr.afrmageknight.model;

public class FichaHabilidad {

    //Atributos
    private int idFicha;
    private String nombre;
    private String descripcion;
    private Heroe heroe;

    //Constructores
    public FichaHabilidad() {
    }

    public FichaHabilidad(int idFicha, String nombre, String descripcion, Heroe heroe) {
        this.idFicha = idFicha;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.heroe = heroe;
    }

    //Getters & Setters
    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
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
