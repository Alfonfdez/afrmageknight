package com.afr.afrmageknight.model;

public class CartaTactica extends Carta {

    //Atributos
    private int numeroOrden;
    private String descripcion;

    //Constructores
    public CartaTactica() {
        // TODO Auto-generated constructor stub
    }

    public CartaTactica(int numeroOrden, String descripcion) {
        super();
        this.numeroOrden = numeroOrden;
        this.descripcion = descripcion;
    }

    //Getters & Setters
    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Métodos

}
