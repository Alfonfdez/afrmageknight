package com.afr.afrmageknight.model;

public class CartaAccionAvanzadaEspecial extends Carta {

    //Atributos
    private Cristal colorPrimario;
    private Cristal colorSecundario;
    private String descripcionBasica;
    private String descripcionAvanzada;

    //Constructores
    public CartaAccionAvanzadaEspecial() {
    }

    public CartaAccionAvanzadaEspecial(int numero, String nombre, Cristal colorPrimario, Cristal colorSecundario, String descripcionBasica, String descripcionAvanzada) {
        super(numero, nombre);
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
    }

    //Getters y setters
    public Cristal getColorPrimario() {
        return colorPrimario;
    }

    public void setColorPrimario(Cristal colorPrimario) {
        this.colorPrimario = colorPrimario;
    }

    public Cristal getColorSecundario() {
        return colorSecundario;
    }

    public void setColorSecundario(Cristal colorSecundario) {
        this.colorSecundario = colorSecundario;
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
