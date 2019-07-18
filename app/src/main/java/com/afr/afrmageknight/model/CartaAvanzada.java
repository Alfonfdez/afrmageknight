package com.afr.afrmageknight.model;

public class CartaAvanzada extends Carta {

    //Atributos
    private String color;
    private String descripcionBasica;
    private String descripcionAvanzada;
    private Heroe heroe;

    //Constructores
    public CartaAvanzada() {
        // TODO Auto-generated constructor stub
    }

    public CartaAvanzada(String color, String descripcionBasica, String descripcionAvanzada, Heroe heroe) {
        super();
        this.color = color;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
        this.heroe = heroe;
    }

    //Getters & Setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
