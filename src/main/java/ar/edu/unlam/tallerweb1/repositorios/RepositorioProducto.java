package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Producto;

import java.util.List;

public interface RepositorioProducto {

    List<Producto> listarProductos();

    List<Producto> listarProductosActivos();

}
