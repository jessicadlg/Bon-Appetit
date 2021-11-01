package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Plato extends Producto{

    @Basic
    private Double tiempoDeCoccion;

    public Plato() {
    }

    public Plato(String nombre, double precio, String codigo, Categoria categoria, boolean activo, Integer cantidadMeGusta, String nombreImagen, String descripcion, Double tiempoDeCoccion) {
        super(nombre, precio, codigo, categoria, activo, cantidadMeGusta, nombreImagen, descripcion);
        this.tiempoDeCoccion = tiempoDeCoccion;
    }

   public Double getTiempoDeCoccion() {
        return tiempoDeCoccion;
    }

    public void setTiempoDeCoccion(Double tiempoDeCoccion) {
        this.tiempoDeCoccion = tiempoDeCoccion;
    }

}
