package com.afr.afrmageknight.model;

public class CartaAccionHechizo extends CartaAccion {

    //Atributos
    private String nombreSecundario;

    //Constructores
    public CartaAccionHechizo() {
    }

    public CartaAccionHechizo(int numero, String nombre, boolean descartada, Cristal color, String descripcionBasica, String descripcionAvanzada, String nombreSecundario) {
        super(numero, nombre, descartada, color, descripcionBasica, descripcionAvanzada);
        this.nombreSecundario = nombreSecundario;
    }

    //Getters y setters
    public String getNombreSecundario() {
        return nombreSecundario;
    }

    public void setNombreSecundario(String nombreSecundario) {
        this.nombreSecundario = nombreSecundario;
    }

    //Métodos

}
