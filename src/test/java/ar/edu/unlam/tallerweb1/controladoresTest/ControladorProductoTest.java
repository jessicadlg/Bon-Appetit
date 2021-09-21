package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.controladores.ControladorProducto;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorProductoTest {

    private ModelAndView mav;
    private ServicioProducto servicioProducto;
    private ControladorProducto controladorProducto;

    @Before
    public void init() {
        servicioProducto = mock(ServicioProducto.class);
        controladorProducto = new ControladorProducto(servicioProducto);
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

    private void givenQueLaListaDeProductosEstaVacia() {
        List<Producto>listaVacia = new ArrayList<>();
        when(servicioProducto.listarProductos()).thenThrow(ListaNoEncontrada.class);
    }

    private void thenMeDiceQueNoHayProductosPorListar() {
        assertThat(mav.getModel().get("msgError")).isEqualTo("No hay productos");
        assertThat(mav.getViewName()).isEqualTo("productos");
    }

    private void givenQueExisteUnaListaDeProductos()  {
        List<Producto> productos = new ArrayList<Producto>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        Producto p4 = new Producto();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        when(servicioProducto.listarProductos()).thenReturn(productos);
    }

    private void whenListoLosProductos() {
        mav = controladorProducto.listarProductos();

    }

    private void thenMeTraeLaListaDeProductos() {
        assertThat(mav.getModel().get("listaProductos")).isEqualTo(this.servicioProducto.listarProductos());
        assertThat(mav.getViewName()).isEqualTo("productos");
    }


}
