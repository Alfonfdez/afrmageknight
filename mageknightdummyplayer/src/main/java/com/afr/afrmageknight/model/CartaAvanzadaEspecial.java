package com.afr.afrmageknight.model;

public class CartaAvanzadaEspecial extends Carta {

    //Atributos
    private Cristal colorPrimario;
    private Cristal colorSecundario;
    private String descripcionBasica;
    private String descripcionAvanzada;
    private Heroe heroe;

    //Constructores
    public CartaAvanzadaEspecial() {
    }

    public CartaAvanzadaEspecial(int numero, String nombre, Cristal colorPrimario, Cristal colorSecundario, String descripcionBasica, String descripcionAvanzada, Heroe heroe) {
        super(numero, nombre);
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
        this.heroe = heroe;
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

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    //Métodos
}
