package com.afr.afrmageknight.model;

public class Hechizo extends CartaAccionAvanzada {

    //Atributos
    private String nombreSecundario;

    //Constructores
    public Hechizo() {
    }

    public Hechizo(int numero, String nombre, Cristal color, String descripcionBasica, String descripcionAvanzada, boolean descartada, String nombreSecundario) {
        super(numero, nombre, color, descripcionBasica, descripcionAvanzada, descartada);
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
