package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ProductoNoEncontrado;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioProductoImpl implements ServicioProducto {


    RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioProductoImpl(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }


    @Override
    public List<Producto> listarProductos() {

        if (this.repositorioProducto.listarProductos().size() > 0) {
            return this.repositorioProducto.listarProductos();
        } else {
            throw new ListaNoEncontrada();
        }
    }

    @Override
    public List<Producto> listarProductosActivos() throws ListaNoEncontrada {

        if (this.repositorioProducto.listarProductosActivos().size() < 1) {
            throw new ListaNoEncontrada();
        }
        return this.repositorioProducto.listarProductosActivos();
    }

    @Override
    public Producto buscarProductoPorNombre(String nombreProducto) {

        if (this.repositorioProducto.buscarProductoPorNombre(nombreProducto) == null) {
            throw new ProductoNoEncontrado();
        }
        return this.repositorioProducto.buscarProductoPorNombre(nombreProducto);
    }

    @Override
    public Producto buscarProductoPorId(Long idProducto) {

        if (this.repositorioProducto.buscarProductoPorId(idProducto) == null) {
            throw new ProductoNoEncontrado();
        }

        return this.repositorioProducto.buscarProductoPorId(idProducto);
    }

    @Override
    public Long darMeGusta(Long idProducto) {
        Producto productoEncontrado = buscarProductoPorId(idProducto);
        Integer cantidad = productoEncontrado.getCantidadMeGusta();
        productoEncontrado.setCantidadMeGusta(++cantidad);
        return this.repositorioProducto.actualizarProducto(productoEncontrado);
    }

    @Override
    public List<Producto> listarDestacados() {
        List<Producto> destacados = new ArrayList<>();
        List<Producto> listaProductos = this.repositorioProducto.listarProductos();
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCantidadMeGusta() > 3) {
                destacados.add(listaProductos.get(i));
                if (destacados.size() == 3) {
                    break;
                }
            }
        }
        if (destacados.size() < 3) {
            for (int i = 0; i < listaProductos.size(); i++) {
                destacados.add(listaProductos.get((int) Math.random() * listaProductos.size()));
                if (destacados.size() == 3) {
                    break;
                }
            }
        }
        return destacados;
    }
}