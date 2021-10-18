package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


public class RepositorioProductoTest extends SpringTest {

    @Autowired
    private RepositorioProducto repositorioProducto;

    private String nombreProducto = "Pizza";

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnProductoPorId() {

        Long idProducto = givenQueExisteUnProducto();

        Producto productoObtenido = whenBuscoUnProductoPorId(idProducto);

        thenMeDevuelveElProductoBuscado(idProducto, productoObtenido);

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanListarTodasLasProductos (){

        List<Producto>productosEsperados = givenUnaListaDeProductos();

        List<Producto>productosObtenidos = whenListoLosProductos();

        thenMeTraeLaListaDeProductos(productosEsperados,productosObtenidos);

    }
    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnProductoPorSuNombre(){

        Producto productoEsperado =  givenQueUnProductoExiste();

        Producto productoObtenido = whenBuscoUnProductoPorSuNombre();

        thenMeTraeElProductoBuscado(productoEsperado,productoObtenido);

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaDarMeGustaAUnProductoYQueDevuelvaElIdDelProducto() {
        Long idEsperado = givenQueUnProductoConMegusta();
        Producto productoObtenido = whenBuscoUnProductoPorId(idEsperado);
        productoObtenido.setCantidadMeGusta(1);
        whenDoyMeGustaAlProducto(productoObtenido);
        Long idObtenido = whenDoyMeGustaAlProducto(productoObtenido);
        thenMeDevuelveElIdDeEseProducto(idEsperado, productoObtenido);

    }
    @Test
    @Rollback
    @Transactional
    public void quePuedaObtenerLosProductosDestacadosPorCantidadDeLikes() {
        givenQueUnProductoConMegusta(2);
        givenQueUnProductoConMegusta(4);

        List<Producto> productos = whenBuscoProductosConMasMeGustaQue(3);
        thenObtengo(1, productos);
    }

    private void givenQueUnProductoConMegusta(int cantidadLikes) {
        Producto p1 = new Producto();
        p1.setCantidadMeGusta(cantidadLikes);
        session().save(p1);
    }

    private List<Producto> whenBuscoProductosConMasMeGustaQue(int cantidad) {
        return repositorioProducto.buscarProductosConMasDe(cantidad);
    }

    private void thenObtengo(int cantidad, List<Producto> productos) {
        assertThat(productos).hasSize(cantidad);
    }

    private Long givenQueUnProductoConMegusta() {
        Producto p1 = new Producto();
        p1.setCantidadMeGusta(0);
        return (Long) session().save(p1);


    }
    private void thenMeDevuelveElProductoBuscado(Long idProducto, Producto productoObtenido) {
        assertThat(productoObtenido).isNotNull();
        assertThat(idProducto).isEqualTo(productoObtenido.getId());
    }


    private Long givenQueExisteUnProducto() {
        Producto p1 = new Producto();
        return (Long) session().save(p1);

    }

    private Long whenDoyMeGustaAlProducto(Producto productoObtenido) {
        return repositorioProducto.actualizarProducto(productoObtenido);
    }

    private Producto whenBuscoUnProductoPorId(Long idProducto) {
        return repositorioProducto.buscarProductoPorId(idProducto);
    }
    private void thenMeDevuelveElIdDeEseProducto(Long idEsperado, Producto productoObtenido) {
        assertThat(idEsperado).isEqualTo(productoObtenido.getId());
        assertThat(productoObtenido.getCantidadMeGusta()).isEqualTo(1);
    }
    private Producto givenQueUnProductoExiste() {
        Producto producto = new Producto();
        producto.setNombre(nombreProducto);
        session().save(producto);
        return producto;
    }

    private Producto whenBuscoUnProductoPorSuNombre() {
        return repositorioProducto.buscarProductoPorNombre(nombreProducto);
    }

    private void thenMeTraeElProductoBuscado(Producto productoEsperado, Producto productoObtenido) {
        assertThat(productoObtenido).isNotNull();
        assertThat(productoEsperado).isEqualTo(productoObtenido);


    }


    private List<Producto> givenUnaListaDeProductos() {
        List<Producto>productosLista = new ArrayList<>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        Producto p4 = new Producto();
        session().save(p1);
        session().save(p2);
        session().save(p3);
        session().save(p4);
        productosLista.add(p1);
        productosLista.add(p2);
        productosLista.add(p3);
        productosLista.add(p4);
        return productosLista;
    }

    private List<Producto> whenListoLosProductos() {
        return repositorioProducto.listarProductos();
    }

    private void thenMeTraeLaListaDeProductos(List<Producto> productosEsperados, List<Producto> productosObtenidos) {
        assertThat(productosEsperados).isEqualTo(productosObtenidos);
        assertThat(productosObtenidos).hasSize(4);

    }


}
