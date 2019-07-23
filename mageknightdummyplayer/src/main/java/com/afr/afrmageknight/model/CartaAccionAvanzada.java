package com.afr.afrmageknight.model;

public class CartaAccionAvanzada extends Carta{

    //Atributos
    private Cristal color;
    private String descripcionBasica;
    private String descripcionAvanzada;

    //Constructores
    public CartaAccionAvanzada() {
    }

    public CartaAccionAvanzada(int numero, String nombre, Cristal color, String descripcionBasica, String descripcionAvanzada) {
        super(numero, nombre);
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
}
