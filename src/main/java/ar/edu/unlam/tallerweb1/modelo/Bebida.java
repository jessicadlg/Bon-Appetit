package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;

@Entity
public class Bebida extends Producto{

    public Bebida() {
    }

    public Bebida(String nombre, double precio, String codigo, Categoria categoria, boolean activo, Integer cantidadMeGusta, String nombreImagen, String descripcion) {
        super(nombre, precio, codigo, categoria, activo, cantidadMeGusta, nombreImagen, descripcion);
    }
}
