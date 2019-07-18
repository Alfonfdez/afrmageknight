package com.afr.afrmageknight.model;

public class CartaTactica extends Carta {

    //Atributos
    private String descripcion;

    //Constructores
    public CartaTactica() {
        // TODO Auto-generated constructor stub
    }

    public CartaTactica(String descripcion) {
        super();
        this.descripcion = descripcion;
    }

    //Getters & Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Métodos
    @Override
    public String toString() {
        return "CartaTactica [descripcion=" + descripcion + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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
        CartaTactica other = (CartaTactica) obj;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        return true;
    }

}
