package com.afr.afrmageknight.model;

public class CartaAccionAvanzadaEspecial extends CartaAccion {

    //Atributos
    private Cristal colorSecundario;

    //Constructores
    public CartaAccionAvanzadaEspecial() {
    }

    public CartaAccionAvanzadaEspecial(int numero, String nombre, boolean descartada, Cristal color, String descripcionBasica, String descripcionAvanzada, Cristal colorSecundario) {
        super(numero, nombre, descartada, color, descripcionBasica, descripcionAvanzada);
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
