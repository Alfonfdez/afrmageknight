package com.afr.afrmageknight.model;

public class CartaTactica extends Carta {

    //Atributos
    private TipoTactica tipoTactica;
    private int numeroOrden;
    private String descripcion;
    boolean descartada;

    //Constructores
    public CartaTactica() {
    }

    public CartaTactica(int numero, String nombre, TipoTactica tipoTactica, int numeroOrden, String descripcion, boolean descartada) {
        super(numero, nombre);
        this.tipoTactica = tipoTactica;
        this.numeroOrden = numeroOrden;
        this.descripcion = descripcion;
        this.descartada = descartada;
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

    public boolean isDescartada() { return descartada; }

    public void setDescartada(boolean descartada) {
        this.descartada = descartada;
    }

    //Métodos

}
