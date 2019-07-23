package com.afr.afrmageknight.model;

public class CartaTactica extends Carta {

    //Atributos
    private TipoTactica tipoTactica;
    private int numeroOrden;
    private String descripcion;

    //Constructores
    public CartaTactica() {
        // TODO Auto-generated constructor stub
    }

    public CartaTactica(int numero, String nombre, TipoTactica tipoTactica, int numeroOrden, String descripcion) {
        super(numero, nombre);
        this.tipoTactica = tipoTactica;
        this.numeroOrden = numeroOrden;
        this.descripcion = descripcion;
    }

    //Getters & Setters
    public TipoTactica getTipoTactica() {
        return tipoTactica;
    }

    public void setTipoTactica(TipoTactica tipoTactica) {
        this.tipoTactica = tipoTactica;
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
