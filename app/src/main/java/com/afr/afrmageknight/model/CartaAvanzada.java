package com.afr.afrmageknight.model;

public class CartaAvanzada extends Carta {

    //Atributos
    private String color;
    private String descripcionBasica;
    private String descripcionAvanzada;

    //Constructores
    public CartaAvanzada() {
        // TODO Auto-generated constructor stub
    }

    public CartaAvanzada(String color, String descripcionBasica, String descripcionAvanzada) {
        super();
        this.color = color;
        this.descripcionBasica = descripcionBasica;
        this.descripcionAvanzada = descripcionAvanzada;
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

    //Métodos
    @Override
    public String toString() {
        return "CartaAvanzada [color=" + color + ", descripcionBasica=" + descripcionBasica + ", descripcionAvanzada="
                + descripcionAvanzada + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((descripcionAvanzada == null) ? 0 : descripcionAvanzada.hashCode());
        result = prime * result + ((descripcionBasica == null) ? 0 : descripcionBasica.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartaAvanzada other = (CartaAvanzada) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (descripcionAvanzada == null) {
            if (other.descripcionAvanzada != null)
                return false;
        } else if (!descripcionAvanzada.equals(other.descripcionAvanzada))
            return false;
        if (descripcionBasica == null) {
            if (other.descripcionBasica != null)
                return false;
        } else if (!descripcionBasica.equals(other.descripcionBasica))
            return false;
        return true;
    }

}
