package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorPedidoTest {

    private ModelAndView mav;
    private ServicioPedido servicioPedido;
    private ServicioProducto servicioProducto;
    private ServicioCategoria servicioCategoria;
    private ControladorPedido controladorPedido;
    private Long idProducto = 1L;
    private Long idPedido = 2L;


    @Before
    public void init() {
        servicioPedido = mock(ServicioPedido.class);
        servicioProducto = mock(ServicioProducto.class);
        servicioCategoria= mock(ServicioCategoria.class);
        controladorPedido = new ControladorPedido(servicioPedido,servicioProducto,servicioCategoria);
    }

    @Test
    public void queSePuedaGenerarUnPedido(){

        givenUnPedidoNuevo();

        whenQuieroGenerarElPedido();

        thenObtengoElIdDelPedidoGenerado();

    }

    private void givenUnPedidoNuevo() {
        when(servicioPedido.generarPedido()).thenReturn(1L);
    }

    private void whenQuieroGenerarElPedido() {
        mav = controladorPedido.generarPedido();
    }

    private void thenObtengoElIdDelPedidoGenerado() {
        assertThat(mav.getViewName()).isEqualTo("productos");
        assertThat(mav.getModel().get("idPedido")).isEqualTo(servicioPedido.generarPedido());

    }

    @Test
    public void queSePuedaAgregarUnProductoAUnPedidoYQueSeCalculeElTotal(){

        givenQueExisteUnPedidoSinProductos();

        whenAgregoUnProducto();

        thenMeDevuelveElPedidoConSusProductos();

    }

    private void givenQueExisteUnPedidoSinProductos() {
        when(servicioPedido.agregarProductoAlPedido(idProducto,idPedido)).thenReturn(new Pedido());
    }

    private void whenAgregoUnProducto() {

        mav = controladorPedido.agregarProductoAlPedido(idProducto,idPedido);
    }

    private void thenMeDevuelveElPedidoConSusProductos() {
        assertThat(mav.getViewName()).isEqualTo("redirect:carrito?idPedido=2");
    }








}
