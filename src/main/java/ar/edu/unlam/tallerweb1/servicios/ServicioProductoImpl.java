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
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        Producto p1 = new Producto(1L,"Cafe Con Leche", 150.00);
        Producto p2 = new Producto(2L,"Cafe Cortado", 150.00);
        Producto p3 = new Producto(3L,"Tostado de Miga", 150.00);
        Producto p4 = new Producto(4L,"Media Luna", 150.00);
        Producto p5 = new Producto(5L,"Gaseosa Light", 150.00);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        return productos;
    }
}
