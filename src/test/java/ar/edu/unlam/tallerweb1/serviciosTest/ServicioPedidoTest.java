package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioPedidoTest {

    private RepositorioPedido repositorioPedido = mock(RepositorioPedido.class);
    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
    private ServicioPedido servicioPedido = new ServicioPedidoImpl(repositorioPedido,repositorioProducto);
    Pedido pedido;
    private Long idProducto = 1L;
    private Long idPedido = 2L;


    @Test
    public void queSePuedaAgregarUnaComidaAUnPedido(){

        givenQueExisteUnPedidoSinComidas();

        whenAgregoUnaComidaAlPedido();

        thenMeDevuelveElPedidoConLosComidas();

    }

    @Test
    public void queSePuedaEliminarUnaComidaDeUnPedido(){

        givenQueExisteUnPedidoConComidas();

        whenQuieroEliminarUnaComida();

        thenMeEliminaLaComida();

    }

    private void givenQueExisteUnPedidoConComidas() {
        List<Producto> listaProductos = new ArrayList<>();
        Comida comida = new Comida();
        Comida comida2 = new Comida();
        comida.setPrecio(200.0);
        comida2.setPrecio(300.0);
        comida.setNombre("Pizza");
        comida2.setNombre("Pizza otra vez");
        listaProductos.add(comida);
        listaProductos.add(comida2);
        Pedido pedido = new Pedido();
        pedido.setListaProductos(listaProductos);
        pedido.setTotal(500.0);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(comida);
    }

    private void whenQuieroEliminarUnaComida() {
        pedido = servicioPedido.eliminarComidaDeUnPedido(idProducto,idPedido);
    }

    private void thenMeEliminaLaComida() {
        assertThat(pedido.getListaProductos()).hasSize(1);
        assertThat(pedido.getTotal()).isEqualTo(300.0);
    }

    private void givenQueExisteUnPedidoSinComidas() {
        Comida p1 = new Comida();
        p1.setPrecio(200.0);
        Pedido p2 = new Pedido();
        p2.setTotal(0.0);
        p2.setListaProductos(new ArrayList<>());
        when(repositorioPedido.obtenerPedido(1L)).thenReturn(p2);
        when(repositorioProducto.buscarProductoPorId(idProducto)).thenReturn(p1);
    }

    private void whenAgregoUnaComidaAlPedido() {
        pedido = servicioPedido.agregarComidaAlPedido(idProducto,idPedido);
    }

    private void thenMeDevuelveElPedidoConLosComidas() {
        assertThat(pedido.getListaProductos()).hasSize(1);
        assertThat(pedido.getTotal()).isEqualTo(200.0);
    }

}
