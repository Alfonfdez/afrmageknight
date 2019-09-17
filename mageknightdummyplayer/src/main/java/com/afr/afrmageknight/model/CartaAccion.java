package com.afr.afrmageknight.model;

public abstract class CartaAccion extends Carta{

    //Atributos
    private Cristal color;
    private String descripcionBasica;
    private String descripcionAvanzada;

    //Constructores
    public CartaAccion() {
    }

    public CartaAccion(int numero, String nombre, boolean descartada, Cristal color, String descripcionBasica, String descripcionAvanzada) {
        super(numero, nombre, descartada);
        this.color = color;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
    }

    //Getters y setters
    public Cristal getColor() {
        return color;
    }

    public void setColor(Cristal color) {
        this.color = color;
    }

    public String getDescripcionBasica() {
        return descripcionBasica;
    }

    public void setDescripcionBasica(String descripcionBasica) {
        this.descripcionBasica = descripcionBasica;
    }

    public String getDescripcionAvanzada() {
        return descripcionAvanzada;
    }

    public void setDescripcionAvanzada(String descripcionAvanzada) {
        this.descripcionAvanzada = descripcionAvanzada;
    }


    //Métodos

}
