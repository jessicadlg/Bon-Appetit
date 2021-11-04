package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.Excepciones.PedidoInexistente;
import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
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
    public void queSePuedaAgregarComidaAUnPedidoYQueSeCalculeElTotal(){

        givenQueExisteUnPedidoSinComidas();

        whenAgregoUnaComida();

        thenMeDevuelveElPedidoConSusComidas();

    }

    @Test
    public void queSePuedaConfirmarUnPedido(){
        givenQueExisteUnPedidoConComidas();

        whenQuieroConfirmarElPedido();

        thenMeLlevaAlFormularioParaConfirmarElPedido();

    }

    @Test
    public void queSiSeConfirmaUnPedidoInexistenteNotifique(){

        givenQueNoExisteUnPedido();

        whenQuieroConfirmarElPedido();

        thenMeDevuelveElMensajeDePedidoInexistente();


    }

    private void givenQueNoExisteUnPedido() {
        when(servicioPedido.obtenerPedido(1L)).thenThrow(PedidoInexistente.class);


    }

    private void thenMeDevuelveElMensajeDePedidoInexistente() {
        assertThat(mav.getViewName()).isEqualTo("formularioPedido");
        assertThat(mav.getModel().get("pedidoError")).isEqualTo("Este pedido no existe");
    }

    private void givenQueExisteUnPedidoConComidas() {

        Pedido p1 = new Pedido();
        Producto pr1 = new Producto();
        Producto pr2 = new Producto();
        Producto pr3 = new Producto();
        p1.getListaProductos().add(pr1);
        p1.getListaProductos().add(pr2);
        p1.getListaProductos().add(pr3);
        when(servicioPedido.obtenerPedido(1L)).thenReturn(p1);
    }

    private void whenQuieroConfirmarElPedido() {
        mav = controladorPedido.procesarPedido(1L);
    }

    private void thenMeLlevaAlFormularioParaConfirmarElPedido() {
        assertThat(mav.getViewName()).isEqualTo("formularioPedido");
        assertThat(mav.getModel().get("pedido")).isEqualTo(servicioPedido.obtenerPedido(1L));
    }

    private void givenQueExisteUnPedidoSinComidas() {
        when(servicioPedido.agregarComidaAlPedido(idProducto,idPedido)).thenReturn(new Pedido());
    }

    private void whenAgregoUnaComida() {
        mav = controladorPedido.agregarProductoAlPedido(idProducto,idPedido);
    }

    private void thenMeDevuelveElPedidoConSusComidas() {
        assertThat(mav.getViewName()).isEqualTo("productos");
        assertThat(mav.getModel().get("pedido")).isEqualTo(servicioPedido.agregarComidaAlPedido(idProducto,idPedido));
    }



}
