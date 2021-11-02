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
    public void queSePuedaAgregarComidaAUnPedidoYQueSeCalculeElTotal(){

        givenQueExisteUnPedidoSinComidas();

        whenAgregoUnaComida();

        thenMeDevuelveElPedidoConSusComidas();

    }

    private void givenQueExisteUnPedidoSinComidas() {
        when(servicioPedido.agregarComidaAlPedido(idProducto,idPedido)).thenReturn(new Pedido());
    }

    private void whenAgregoUnaComida() {
        mav = controladorPedido.agregarComidaAlPedido(idProducto,idPedido);
    }

    private void thenMeDevuelveElPedidoConSusComidas() {
        assertThat(mav.getViewName()).isEqualTo("productos");
        assertThat(mav.getModel().get("pedido")).isEqualTo(servicioPedido.agregarComidaAlPedido(idProducto,idPedido));
    }

}
