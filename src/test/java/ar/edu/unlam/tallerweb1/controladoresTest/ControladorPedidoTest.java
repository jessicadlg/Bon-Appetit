package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorPedidoTest {

    private ServicioPedido servicioPedido = mock(ServicioPedido.class);
    private ServicioProducto servicioProducto = mock(ServicioProducto.class);
    private ControladorPedido controladorPedido = new ControladorPedido(servicioPedido, servicioProducto);

    @Test
    public void testQuePruebaVerPedidos(){
        ModelAndView modelAndView = whenSeQuiereVerLosPedidos();
        thenSeObtieneListaDePedidosYReDirigeAPedidos(modelAndView);
    }

    @Test
    public void testQuePruebaQueSeGeneraUnNuevoPedido(){
        giveQueSeCreaUnPedidoNuevo();
        ModelAndView modelAndView = whenSeGeneraUnNuevoPedido();
        thenSeObtieneUnNuevoPedidoLaListaDeProductosYRedirigeACargarPedido(modelAndView);
    }

    @Test
    public void testQuePruebaQueSeAgregaUnProductoAUnPedido(){
        givenQueSeAgregaUnProductoAUnPedido();
        ModelAndView modelAndView = whenCuandoLeAgregoUnProducto();
        thenElPedidoTieneDosProductos(modelAndView);
    }

    private void givenQueSeAgregaUnProductoAUnPedido() {
        when(servicioPedido.agregarProducto(anyInt(), anyInt())).thenReturn(new Pedido());
        when(servicioProducto.getProductos()).thenReturn(new ArrayList<Producto>());
    }

    private void thenElPedidoTieneDosProductos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("nuevoPedido");
        assertThat(modelAndView.getModel().get("pedido")).isNotNull();
        assertThat(modelAndView.getModel().get("productos")).isNotNull();
    }

    private ModelAndView whenCuandoLeAgregoUnProducto() {
        return controladorPedido.agregarProducto(1,1);
    }

    private void giveQueSeCreaUnPedidoNuevo() {
        when(servicioPedido.crearPedido()).thenReturn(new Pedido());
    }

    private ModelAndView whenSeGeneraUnNuevoPedido() {
        return controladorPedido.crearPedido();
    }

    private void thenSeObtieneUnNuevoPedidoLaListaDeProductosYRedirigeACargarPedido(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("nuevoPedido");
        assertThat(modelAndView.getModel().get("pedido")).isNotNull();
        assertThat(modelAndView.getModel().get("productos")).isNotNull();
    }

    private ModelAndView whenSeQuiereVerLosPedidos() {
        return controladorPedido.verPedidos();
    }

    private void thenSeObtieneListaDePedidosYReDirigeAPedidos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("pedidos");
        assertThat(modelAndView.getModel().get("pedidos")).isNotNull();
    }
}
