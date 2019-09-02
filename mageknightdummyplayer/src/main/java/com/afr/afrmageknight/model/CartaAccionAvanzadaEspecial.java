package com.afr.afrmageknight.model;

public class CartaAccionAvanzadaEspecial extends CartaAccion {

    //Atributos
    private Cristal colorSecundario;

    //Constructores
    public CartaAccionAvanzadaEspecial() {
    }

    public CartaAccionAvanzadaEspecial(int numero, String nombre, Cristal color, String descripcionBasica, String descripcionAvanzada, boolean descartada, Cristal colorSecundario) {
        super(numero, nombre, color, descripcionBasica, descripcionAvanzada, descartada);
        this.colorSecundario = colorSecundario;
    }

    //Getters y setters
    public Cristal getColorSecundario() {
        return colorSecundario;
    }

    public void setColorSecundario(Cristal colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

    //Métodos

}
