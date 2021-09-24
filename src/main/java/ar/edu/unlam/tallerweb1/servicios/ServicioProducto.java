package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Producto;

import java.util.List;

public interface ServicioProducto {

    List<Producto> listarProductos() throws ListaNoEncontrada;

    List<Producto> listarProductosActivos() throws ListaNoEncontrada;

    Producto buscarProductoPorNombre(String nombreProducto);
}
