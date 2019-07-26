package com.afr.afrmageknight.model;

public class CartaAccionBasica extends CartaAccionAvanzada {

    //Atributos
    private Heroe heroe;

    //Constructores
    public CartaAccionBasica() {
    }

    public CartaAccionBasica(int numero, String nombre, Cristal color, String descripcionBasica, String descripcionAvanzada, boolean descartada, Heroe heroe) {
        super(numero, nombre, color, descripcionBasica, descripcionAvanzada, descartada);
        this.heroe = heroe;
    }

    //Getters & Setters
    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    //Métodos

}
