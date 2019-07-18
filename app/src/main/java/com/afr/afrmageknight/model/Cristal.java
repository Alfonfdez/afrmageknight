package com.afr.afrmageknight.model;

public class Cristal {

    //Atributos
    private String color;

    //Constructores
    public Cristal() {
        // TODO Auto-generated constructor stub
    }

    public Cristal(String color) {
        super();
        this.color = color;
    }

    //Getters & Setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //Métodos
    @Override
    public String toString() {
        return "Cristal [color=" + color + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cristal other = (Cristal) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        return true;
    }

}
