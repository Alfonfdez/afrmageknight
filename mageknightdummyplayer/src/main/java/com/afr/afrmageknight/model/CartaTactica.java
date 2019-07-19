package com.afr.afrmageknight.model;

public class CartaTactica extends Carta {

    //Atributos
    private String tipo;
    private int numeroOrden;
    private String descripcion;

    //Constructores
    public CartaTactica() {
        // TODO Auto-generated constructor stub
    }

    public CartaTactica(String tipo, int numeroOrden, String descripcion) {
        super();
        this.tipo = tipo;
        this.numeroOrden = numeroOrden;
        this.descripcion = descripcion;
    }

    //Getters & Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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
