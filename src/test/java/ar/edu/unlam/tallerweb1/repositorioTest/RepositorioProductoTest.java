package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.jetbrains.annotations.NotNull;
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

   /* @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnProductoPorId(){

        Producto productoEsperado = givenQueUnosProductoExiste();

        Producto productoObtenido = whenBuscoUnProductoPorId();

        thenMeDevuelveElProductoBuscado(productoEsperado,productoObtenido);


    }*/

    private Producto givenQueUnosProductoExiste() {
        Producto p1 = new Producto();
        session().save(p1);
        return p1;
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
    public void queSePuedaListarTodosLosProductosActivos(){

        List<Producto>productosEsperados = givenUnaListaDeProductosActivos();

        List<Producto>productosObtenidos = whenListoLosProductosActivos();

        thenMeTraeLaListaDeProductosActivos(productosEsperados,productosObtenidos);


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
    public void queMePuedaTraerLosProductosDeUnaCategoria(){

        givenExisteUnProductoDeCategoria("Bebidas");

        givenExisteUnProductoDeCategoria("Postres");

        List<Producto>listaProductos =  whenBuscoProductoDe("Bebidas");

        thenMeDevuelve(listaProductos,1);

    }



  /*  @Test
    @Rollback
    @Transactional
    public void queSePuedaDarMeGustaAUnProductoYQueDevuelvaElIdDelProducto(){

        Long idEsperado = givenQueUnProductoConMegusta();
        Producto productoObtenido = whenBuscoUnProductoPorId();
        productoObtenido.setCantidadMeGusta(1);
        whenDoyMeGustaAlProducto(productoObtenido);
        thenMeDevuelveElIdDeEseProducto(idEsperado,productoObtenido);

    }
    */
    private Long givenQueUnProductoConMegusta() {
        Producto p1 = new Producto();
        p1.setCantidadMeGusta(0);
        return (Long) session().save(p1);


    }

    private void whenDoyMeGustaAlProducto(Producto productoObtenido) {
         repositorioProducto.actualizarProducto(productoObtenido);
    }

    private void thenMeDevuelveElIdDeEseProducto(Long idEsperado,  Producto producto) {
        assertThat(idEsperado).isEqualTo(producto.getId());
        assertThat(producto.getId()).isEqualTo(1L);
        assertThat(producto.getCantidadMeGusta()).isEqualTo(1);
    }

    private Producto whenBuscoUnProductoPorId() {
        return repositorioProducto.buscarProductoPorId(1L);
    }

    private void thenMeDevuelveElProductoBuscado(Producto productoEsperado, Producto productoObtenido) {
        assertThat(productoObtenido).isNotNull();
        assertThat(productoEsperado).isEqualTo(productoObtenido);
    }

    private void givenExisteUnProductoDeCategoria(String nombreCategoria) {
        Producto p1 = new Producto();
        Categoria c1 = new Categoria();
        c1.setNombreCategoria(nombreCategoria);
        p1.setCategoria(c1);
        session().save(c1);
        session().save(p1);

    }

    private List<Producto> whenBuscoProductoDe(String nombreCategoria) {
        return repositorioProducto.buscarProductoPorCategoria(nombreCategoria);
    }

    private void thenMeDevuelve(List<Producto> listaProductos, int cantidad) {
        assertThat(listaProductos).hasSize(cantidad);
    }

    private @NotNull Producto givenQueUnProductoExiste() {
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

    private List<Producto> givenUnaListaDeProductosActivos() {
        List<Producto>productosLista = new ArrayList<>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        Producto p4 = new Producto();
        p1.setActivo(true);
        p2.setActivo(true);
        p3.setActivo(false);
        p4.setActivo(false);
        session().save(p1);
        session().save(p2);
        session().save(p3);
        session().save(p4);
        productosLista.add(p1);
        productosLista.add(p2);
        return productosLista;
    }

    private List<Producto> whenListoLosProductosActivos() {
        return repositorioProducto.listarProductosActivos();
    }

    private void thenMeTraeLaListaDeProductosActivos(List<Producto> productosEsperados, List<Producto> productosObtenidos) {
        assertThat(productosEsperados).isEqualTo(productosObtenidos);
        assertThat(productosObtenidos).hasSize(2);
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
