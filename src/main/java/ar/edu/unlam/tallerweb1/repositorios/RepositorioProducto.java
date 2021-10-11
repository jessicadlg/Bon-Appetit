package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Producto;

import java.util.List;

public interface RepositorioProducto {

    List<Producto> listarProductos();

    List<Producto> listarProductosActivos();

    Producto buscarProductoPorNombre(String nombreProducto);

    List<Producto> buscarProductoPorCategoria(String nombreCategoria);

    Producto buscarProductoPorId(Long idProducto);

    Long actualizarProducto(Producto producto);

}
