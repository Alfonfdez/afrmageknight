package com.afr.afrmageknight.model;

public abstract class CartaAccion extends Carta{

    //Atributos
    private Cristal color;
    private String descripcionBasica;
    private String descripcionAvanzada;
    private boolean descartada;

    //Constructores
    public CartaAccion() {
    }

    public CartaAccion(int numero, String nombre, Cristal color, String descripcionBasica, String descripcionAvanzada, boolean descartada) {
        super(numero, nombre);
        this.color = color;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
        this.descartada = descartada;
    }

    //Getters y setters
    public Cristal getColor() {
        return color;
    }

    public void setColor(Cristal color) {
        this.color = color;
    }

    public String getDescripcionBasica() {
        return descripcionBasica;
    }

    public void setDescripcionBasica(String descripcionBasica) {this.descripcionBasica = descripcionBasica;
    }

    public String getDescripcionAvanzada() {
        return descripcionAvanzada;
    }

    public void setDescripcionAvanzada(String descripcionAvanzada) {
        this.descripcionAvanzada = descripcionAvanzada;
    }

    public boolean isDescartada() {
        return descartada;
    }

    public void setDescartada(boolean descartada) {
        this.descartada = descartada;
    }

    //Métodos

}
