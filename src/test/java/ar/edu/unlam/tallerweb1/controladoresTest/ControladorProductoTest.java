package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaCategoriaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ProductoNoEncontrado;
import ar.edu.unlam.tallerweb1.controladores.ControladorProducto;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorProductoTest {

    private ModelAndView mav;
    private ServicioProducto servicioProducto;
    private ServicioCategoria servicioCategoria;
    private ControladorProducto controladorProducto;
    private String nombreProducto = "Pizza";
    private List<Producto> productos;
    private Long idProducto = 1L;

    @Before
    public void init() {
        servicioProducto = mock(ServicioProducto.class);
        servicioCategoria = mock(ServicioCategoria.class);
        controladorProducto = new ControladorProducto(servicioProducto,servicioCategoria);
    }
    
    @Test
    public void queSePuedanListarLosProductos() {
        
        givenQueExisteUnaListaDeProductos();
        
        whenListoLosProductos();
        
        thenMeTraeLaListaDeProductos();
        
    }

    @Test
    public void queCuandoLaListaEsteVaciaNosDeUnMensajeQueNoHayProductos(){

        givenQueLaListaDeProductosEstaVacia();

        whenListoLosProductos();

        thenMeDiceQueNoHayProductosPorListar();


    }

    @Test
    public void queSePuedanListarLosProductosActivos(){

        givenQueExisteUnaListaDeProductosActivos();

        whenListoLosProductosActivos();

        thenMeTraeLaListaDeProductosActivos();

    }

    @Test
    public void queCuandoListeLosProductosActivosYLaListaEsteVaciaNosDeElMensaje(){

        givenQueExisteUnaListaDeProductosActivosVacia();

        whenListoLosProductosActivos();

        thenMeDiceQueNoHayProductosActivosPorListar();

    }

    @Test
    public void queSePuedaBuscarUnProductoPorNombre(){

        givenQueExisteUnaListaDeProductos();

        whenBuscoUnProductoPorSuNombre();

        thenMeDevuelveElProducto();

    }

    @Test
    public void queCuandoUnProductoNoSeEncuentreMeDeElMensajeYMeListeTodosLosProductos(){

        givenQueExisteUnProductoBuscado();

        whenBuscoUnProductoPorSuNombre();

        thenMeDiceQueNoSeEncontroElProductoYmeListaTodosLosProductos();

    }

    @Test
    public void queCuandoNoEncuentreUnProductoYtampocoEncuentreLaListaDeProductosNosDeElMensajeDeAmbos(){

        givenQueNoExisteNingunProductoNiListas();

        whenBuscoUnProductoPorSuNombre();

        thenNoMeListaNingunProductoYMeDaAmbosMensajes();

    }


    @Test
    public void quePuedaMostrarmeLosDetallesDeUnProducto(){

        givenQueExisteUnProducto();

        whenMuestroLosDetallesDeEseProducto();

        thenMeDevuelveLosDetallesDeEseProducto();

    }

    @Test
    public void queCuandoBuscoUnProductoPorIdYNoExisteMeMandeElmsj(){

        givenQueNoExisteUnDeterminadoProducto();

        whenMuestroLosDetallesDeEseProducto();

        thenMeMandaElMsjDeQueNoExiste();

    }

    @Test
    public void queSePuedaDarMeGustaAUnProducto(){

        givenQueExisteUnProducto();

        whenDoyMeGustaAUnProducto();

        thenElProductoTieneUnMeGustaMas();

    }

   @Test
    public void queMeTraigaLosTresPrimerosProductosConMasMeGusta(){

        givenQueExisteUnaListaDeProductos();

        whenListoLosProductos();

        thenTraeLosTresPrimerosQueTienenMasDeTresMeGusta();

    }

    private void thenTraeLosTresPrimerosQueTienenMasDeTresMeGusta() {
        assertThat(mav.getViewName()).isEqualTo("productos");
        assertThat(mav.getModel().get("destacados")).isEqualTo(servicioProducto.listarDestacados());
    }

    private void whenDoyMeGustaAUnProducto() {
        mav = controladorProducto.darMeGusta(idProducto);
    }

    private void thenElProductoTieneUnMeGustaMas() {
        assertThat(mav.getViewName()).isEqualTo("redirect:/detalleProducto?id=" + idProducto);
    }


    private void givenQueNoExisteUnDeterminadoProducto() {
        when(servicioProducto.buscarProductoPorId(1L)).thenThrow(ProductoNoEncontrado.class);

    }

    private void thenMeMandaElMsjDeQueNoExiste() {
        assertThat(mav.getViewName()).isEqualTo("detalleProducto");
        assertThat(mav.getModel().get("productoNoEncontrado")).isEqualTo("No se encontro el producto");
    }

    private void givenQueExisteUnProducto(){
        Producto p1 = new Producto();
        when(servicioProducto.buscarProductoPorId(1L)).thenReturn(p1);

        when(servicioProducto.darMeGusta(1L)).thenReturn(1L);

    }

    private void whenMuestroLosDetallesDeEseProducto() {
        mav = controladorProducto.mostrarDetallesProducto(1L);
    }

    private void thenMeDevuelveLosDetallesDeEseProducto() {
        assertThat(mav.getViewName()).isEqualTo("detalleProducto");
        assertThat(mav.getModel().get("productoDetalles")).isEqualTo(servicioProducto.buscarProductoPorId(1L));
    }


    private void givenQueExisteUnaListaDeCategoriasVacia() {
        when(servicioCategoria.listarCategorias()).thenThrow(ListaCategoriaNoEncontrada.class);

    }

    private void thenMeDevuelveElMensajeDeListaVacia() {
        assertThat(mav.getModel().get("categoriasNoEncontradas")).isEqualTo("No se encontro ninguna categoria por mostrar");
        assertThat(mav.getViewName()).isEqualTo("productos");
    }

    private void givenQueExisteUnaListaDeCategorias() {
        List<Categoria>listaCategorias = new ArrayList<>();
        Categoria c1 = new Categoria();
        Categoria c2 = new Categoria();
        Categoria c3 = new Categoria();
        Categoria c4 = new Categoria();
        listaCategorias.add(c1);
        listaCategorias.add(c2);
        listaCategorias.add(c3);
        listaCategorias.add(c4);
        when(servicioCategoria.listarCategorias()).thenReturn(listaCategorias);

    }

    private void whenListoLasCategorias() {
        mav = controladorProducto.listarProductos();
    }

    private void thenMeDevuelveLaListaDeCategorias() {
        assertThat(mav.getModel().get("listaCategorias")).isEqualTo(servicioCategoria.listarCategorias());
        assertThat(mav.getViewName()).isEqualTo("productos");

    }

    private void givenQueNoExisteNingunProductoNiListas() {
        when(servicioProducto.buscarProductoPorNombre(nombreProducto)).thenThrow(ProductoNoEncontrado.class);
        when(servicioProducto.listarProductos()).thenThrow(ListaNoEncontrada.class);


    }

    private void thenNoMeListaNingunProductoYMeDaAmbosMensajes() {
        assertThat(mav.getModel().get("msgErrorProducto")).isEqualTo("No se encontro el producto buscado");
        assertThat(mav.getModel().get("msgError")).isEqualTo("No hay productos");
        assertThat(mav.getViewName()).isEqualTo("productos");
    }

    private void givenQueExisteUnProductoBuscado() {
        productos = new ArrayList<>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        productos.add(p1);
        productos.add(p2);
        when(servicioProducto.buscarProductoPorNombre(nombreProducto)).thenThrow(ProductoNoEncontrado.class);
        when(servicioProducto.listarProductos()).thenReturn(productos);
    }

    private void thenMeDiceQueNoSeEncontroElProductoYmeListaTodosLosProductos() {
        assertThat(mav.getModel().get("msgErrorProducto")).isEqualTo("No se encontro el producto buscado");
        assertThat(mav.getModel().get("listaProductos")).isEqualTo(this.servicioProducto.listarProductos());
        assertThat(mav.getViewName()).isEqualTo("productos");
    }

    private void whenBuscoUnProductoPorSuNombre() {
        mav = controladorProducto.buscarProductoPorNombre(nombreProducto);
    }

    private void thenMeDevuelveElProducto() {
        assertThat(mav.getModel().get("productoBuscado")).isEqualTo(servicioProducto.buscarProductoPorNombre(nombreProducto));
        assertThat(mav.getViewName()).isEqualTo("productos");

    }

    private void givenQueExisteUnaListaDeProductosActivosVacia() {
        when(servicioProducto.listarProductosActivos()).thenThrow(ListaNoEncontrada.class);

    }

    private void thenMeDiceQueNoHayProductosActivosPorListar() {
        assertThat(mav.getModel().get("msgError")).isEqualTo("No hay productos activos para mostrar");
        assertThat(mav.getViewName()).isEqualTo("productos");
    }

    private void givenQueExisteUnaListaDeProductosActivos() {
        List<Producto>productos = new ArrayList<>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        p1.setActivo(true);
        p2.setActivo(true);
        p3.setActivo(true);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        when(servicioProducto.listarProductosActivos()).thenReturn(productos);

    }

    private void whenListoLosProductosActivos() {
        mav = controladorProducto.listarProductosActivos();
    }

    private void thenMeTraeLaListaDeProductosActivos() {
        assertThat(mav.getModel().get("listaProductos")).isEqualTo(this.servicioProducto.listarProductosActivos());
        assertThat(mav.getViewName()).isEqualTo("productos");
    }



    private void givenQueLaListaDeProductosEstaVacia() {
        when(servicioProducto.listarProductos()).thenThrow(ListaNoEncontrada.class);
    }

    private void thenMeDiceQueNoHayProductosPorListar() {
        assertThat(mav.getModel().get("msgError")).isEqualTo("No hay productos");
        assertThat(mav.getViewName()).isEqualTo("productos");
    }

    private void givenQueExisteUnaListaDeProductos()  {
        productos = new ArrayList<>();
        List<Producto> destacados = new ArrayList<>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        Producto p4 = new Producto();
        Producto destacado1 = new Producto();
        Producto destacado2 = new Producto();
        Producto destacado3 = new Producto();
        p1.setNombre(nombreProducto);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        destacados.add(destacado1);
        destacados.add(destacado2);
        destacados.add(destacado3);
        when(servicioProducto.listarProductos()).thenReturn(productos);
        when(servicioProducto.buscarProductoPorNombre(nombreProducto)).thenReturn(p1);
        when(servicioProducto.listarDestacados()).thenReturn(destacados);
    }

    private void whenListoLosProductos() {
        mav = controladorProducto.listarProductos();

    }

    private void thenMeTraeLaListaDeProductos() {
        assertThat(mav.getModel().get("listaProductos")).isEqualTo(this.servicioProducto.listarProductos());
        assertThat(mav.getViewName()).isEqualTo("productos");
    }


}
