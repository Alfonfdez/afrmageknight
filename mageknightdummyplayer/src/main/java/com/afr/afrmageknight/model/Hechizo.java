package com.afr.afrmageknight.model;

public class Hechizo extends Carta {

    //Atributos
    private String nombreSecundario;
    private Cristal color;
    private String descripcionBasica;
    private String descripcionAvanzada;

    //Constructores
    public Hechizo() {
        // TODO Auto-generated constructor stub
    }

    public Hechizo(int numero, String nombre, String nombreSecundario, Cristal color, String descripcionBasica, String descripcionAvanzada) {
        super(numero, nombre);
        this.nombreSecundario = nombreSecundario;
        this.color = color;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
    }

    //Getters y setters
    public String getNombreSecundario() {
        return nombreSecundario;
    }

    public void setNombreSecundario(String nombreSecundario) {
        this.nombreSecundario = nombreSecundario;
    }

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

}
