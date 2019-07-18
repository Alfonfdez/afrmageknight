package com.afr.afrmageknight.model;

public class FichaHabilidad {

    //Atributos
    private long id;
    private String descripcion;
    private Heroe heroe;

    //Constructores
    public FichaHabilidad() {
        // TODO Auto-generated constructor stub
    }

    public FichaHabilidad(long id, String descripcion, Heroe heroe) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.heroe = heroe;
    }

    //Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
