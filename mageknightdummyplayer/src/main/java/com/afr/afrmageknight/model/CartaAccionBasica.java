package com.afr.afrmageknight.model;

public class CartaAccionBasica extends CartaAccion {

    //Atributos
    private Heroe heroe;

    //Constructores
    public CartaAccionBasica() {
    }

    public CartaAccionBasica(int numero, String nombre, boolean descartada, Cristal color, String descripcionBasica, String descripcionAvanzada,  Heroe heroe) {
        super(numero, nombre, descartada, color, descripcionBasica, descripcionAvanzada);
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
