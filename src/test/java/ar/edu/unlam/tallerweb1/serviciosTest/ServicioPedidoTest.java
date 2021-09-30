package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaPedidosException;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoNoCreadoException;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ServicioPedidoTest {

    private RepositorioPedido repositorioPedido = mock(RepositorioPedido.class);
    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
    private ServicioPedidoImpl servicioPedido = new ServicioPedidoImpl(repositorioPedido, repositorioProducto);
    private static final Pedido PEDIDO_NUEVO = new Pedido();
    private static final Pedido PEDIDO_CON_PRODUCTO_CARGADO = new Pedido();

    @Test
    public void testQuePruebaQueCuandoObtenemosLosPedidos() throws ListaPedidosException {
        givenUnaListaDePedidos();
        ArrayList<Pedido> pedidos = whenPidoLaListaDePedidos();
        thenObtengoUnaListaDePedidosConPedidos(pedidos);
    }

    @Test(expected = ListaPedidosException.class)
    public void testQuePruebaQueCuandoNoHayPedidosCargadosNosDevuelvaUnaExcepcion() throws ListaPedidosException {
        givenUnaListaDePedidosVacia();
        ArrayList<Pedido> pedidos = whenPidoLaListaDePedidos();
    }

    @Test
    public void testQuePruebaQueSeCreaUnPedidoConExito(){
        givenSeQuiereCrearUnPedidoNuevo();
        Pedido pedido = whenSeCreaElPedido();
        thenMeDevuelveElNumeroDePedido(pedido);
    }

    @Test(expected = PedidoNoCreadoException.class)
    public void testQuePruebaQueNoSeHaPodidoCrearElPedido(){
        givenSeQuiereCrearUnPedidoNuevoSinExito();
        whenSeCreaElPedido();
    }
    
    @Test
    public void testQuePruebaQueSePuedeAgregarUnProductoAunPedido(){
        givenUnPedidoSinProductosCargados();
        Pedido pedido= whenSeAgregaElProducto();
        thenElPedidoTieneUnPedidoCargado(pedido);
    }

    private void givenUnPedidoSinProductosCargados() {
        PEDIDO_CON_PRODUCTO_CARGADO.getProductosPedidos().add(new Producto());
        when(repositorioPedido.agregarProducto(anyLong(), anyInt())).thenReturn(PEDIDO_CON_PRODUCTO_CARGADO);
    }

    private Pedido whenSeAgregaElProducto() {
        return servicioPedido.agregarProducto(1L,1);
    }

    private void thenElPedidoTieneUnPedidoCargado(Pedido pedido) {
        assertThat(pedido.getProductosPedidos()).hasSize(1);
    }

    private void givenSeQuiereCrearUnPedidoNuevoSinExito() {
        when(repositorioPedido.crearPedido()).thenReturn(new Pedido());
    }

    private void givenSeQuiereCrearUnPedidoNuevo() {
        PEDIDO_NUEVO.setId(1L);
        when(repositorioPedido.crearPedido()).thenReturn(PEDIDO_NUEVO);
    }

    private Pedido whenSeCreaElPedido() {
        return servicioPedido.crearPedido();
    }

    private void thenMeDevuelveElNumeroDePedido(Pedido pedido) {
        assertThat(pedido.getId()).isNotNull();
    }

    private void givenUnaListaDePedidosVacia() {
        when(repositorioPedido.getPedidos()).thenReturn(new ArrayList<Pedido>());
    }

    private void givenUnaListaDePedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        pedidos.add(p1);
        pedidos.add(p2);
        when(repositorioPedido.getPedidos()).thenReturn(pedidos);
    }

    private ArrayList<Pedido> whenPidoLaListaDePedidos() throws ListaPedidosException {
        return servicioPedido.getPedidos();
    }

    private void thenObtengoUnaListaDePedidosConPedidos(ArrayList<Pedido> pedidos) {
        assertThat(pedidos.size()).isGreaterThan(0);
    }
}
