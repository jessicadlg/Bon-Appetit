package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Plato;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorPedidoTest {

    private ModelAndView mav;
    private ServicioPedido servicioPedido;
    private ControladorPedido controladorPedido;


    @Before
    public void init() {
        servicioPedido = mock(ServicioPedido.class);
        controladorPedido = new ControladorPedido(servicioPedido);
    }

    @Test
    public void queSePuedaAgregarPlatosAUnPedidoYQueSeCalculeElTotal(){

        givenQueExisteUnPedidoSinPlatos();

        whenAgregoUnPlato();

        thenMeDevuelveElPedidoConSusPlatos();

    }

    private void givenQueExisteUnPedidoSinPlatos() {
        when(servicioPedido.agregarPlatoAlPedido(1L,2L)).thenReturn(new Pedido());
    }

    private void whenAgregoUnPlato() {
        mav = controladorPedido.agregarPlatoAlPedido(1L,2L);
    }

    private void thenMeDevuelveElPedidoConSusPlatos() {
        assertThat(mav.getViewName()).isEqualTo("listaProductos");
        assertThat(mav.getModel().get("pedido")).isEqualTo(servicioPedido.agregarPlatoAlPedido(1L,2L));
    }

}
