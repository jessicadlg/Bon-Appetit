package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.modelo.Bebida;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioPedidoTest {

    private RepositorioPedido repositorioPedido = mock(RepositorioPedido.class);
    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
    private ServicioPedido servicioPedido = new ServicioPedidoImpl(repositorioPedido,repositorioProducto);
    Pedido pedido;
    private Long idProducto = 2L;
    private Long idPedido = 1L;

    @Test
    public void queSePuedaGenerarUnPedido(){

        givenUnPedidoNuevo();

        Long idPedido = whenQuieroGenerarElPedido();

        thenObtengoElIdDelPedidoGenerado(idPedido);

    }

    @Test
    public void queSePuedaAgregarUnaComidaAUnPedidoYQueSeCalculeElTotalYElTiempoDeCoccion(){

        givenQueExisteUnPedidoSinComidas();

        whenAgregoUnaComida();

        thenMeDevuelveElPedidoConSusProductos();

    }

    @Test
    public void queSePuedaAgregarUnaBebidaAUnPedidoYQueSeCalculeElTotal(){

     givenQueExisteUnPedidoSinBebidas();

     whenAgregoUnaBebida();

     thenMeDevuelveElPedido();

    }

    private void givenQueExisteUnPedidoSinBebidas() {
        Pedido pedido = new Pedido();
        Bebida producto = new Bebida();
        ItemPedido itemPedido = new ItemPedido();
        producto.setPrecio(50.0);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(producto);
        when(repositorioPedido.obtenerItemPedido(anyLong(),anyLong())).thenReturn(itemPedido);

    }

    private void whenAgregoUnaBebida() {
        pedido = servicioPedido.agregarProductoAlPedido(idProducto,idPedido);
    }

    private void thenMeDevuelveElPedido() {
        assertThat(pedido).isNotNull();
        assertThat(pedido.getTotal()).isEqualTo(50.0);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(0.0);
    }

    private void givenQueExisteUnPedidoSinComidas() {
        Pedido pedido = new Pedido();
        Comida producto = new Comida();
        ItemPedido itemPedido = new ItemPedido();
        producto.setPrecio(50.0);
        producto.setTiempoDeCoccion(100.0);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(producto);
        when(repositorioPedido.obtenerItemPedido(anyLong(),anyLong())).thenReturn(itemPedido);
    }

    private void whenAgregoUnaComida() {
        pedido = servicioPedido.agregarProductoAlPedido(idProducto,idPedido);
    }

    private void thenMeDevuelveElPedidoConSusProductos() {
        assertThat(pedido).isNotNull();
        assertThat(pedido.getTotal()).isEqualTo(50.0);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(100.0);
      //  verify(repositorioPedido.actualizarPedido(anyObject(),times(1).equals(1));
    }

    private void givenUnPedidoNuevo() {
        when(repositorioPedido.generarPedido(anyObject())).thenReturn(1L);
    }

    private Long whenQuieroGenerarElPedido() {
        return servicioPedido.generarPedido();
    }

    private void thenObtengoElIdDelPedidoGenerado(Long idPedido) {
        assertThat(idPedido).isEqualTo(1L);
    }

}
