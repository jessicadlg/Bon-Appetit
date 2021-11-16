package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Bebida")
public class Bebida extends Producto{

    public Bebida() {
    }

    public Bebida(String nombre, double precio, Categoria categoria, Integer cantidadMeGusta, String nombreImagen, String descripcion) {
        super(nombre, precio, categoria, cantidadMeGusta, nombreImagen, descripcion);
    }
}
