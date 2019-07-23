package com.afr.afrmageknight.model;

public class CartaAccionBasica extends Carta {

    //Atributos
    private Cristal color;
    private String descripcionBasica;
    private String descripcionAvanzada;
    private Heroe heroe;

    //Constructores
    public CartaAccionBasica() {
    }

    public CartaAccionBasica(int numero, String nombre, Cristal color, String descripcionBasica, String descripcionAvanzada, Heroe heroe) {
        super(numero, nombre);
        this.color = color;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
        this.heroe = heroe;
    }

    //Getters & Setters
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

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    //Métodos

}
