package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ControladorPedidoTest {

    @Test
    public void queSePuedaAgregarUnProductoAUnPedido(){
        Pedido pedido = givenUnPedido();
        ModelAndView modelAndView = whenSeAgregaUnProductoAlPedido(pedido);
        thenElPedidoNoEstaVacio(modelAndView);
    }

    @Test
    public void queSePuedaEliminarUnProductoDeUnPedido(){
        Pedido pedido = givenUnPedidoConUnProductoCargado();
        ModelAndView modelAndView = whenSeEliminaUnProductoDelPedido(pedido);
        thenElPedidoEstaVacio(modelAndView);
    }

    private Pedido givenUnPedidoConUnProductoCargado() {
        Producto producto = new Producto();
        producto.setCodigo("CoCa-Cola");
        Pedido pedido = new Pedido();
        pedido.agregarProducto(producto);
        return pedido;
    }

    private ModelAndView whenSeEliminaUnProductoDelPedido(Pedido pedido) {
        return ControladorPedido.EliminarProductoDelPedido(pedido, "CoCa-Cola");
    }

    private void thenElPedidoEstaVacio(ModelAndView modelAndView) {
    }

    private Pedido givenUnPedido() {
        return new Pedido();
    }

    private ModelAndView whenSeAgregaUnProductoAlPedido(Pedido pedido) {
        Producto producto = new Producto();
        return ControladorPedido.agregarProducto(pedido, producto);
    }

    private void thenElPedidoNoEstaVacio(ModelAndView modelAndView) {
        ArrayList<Producto> productosPedidos = (ArrayList<Producto>) modelAndView.getModel().get("productosPedidos");
        assertThat(productosPedidos.size()).isEqualTo(1);
    }

}
