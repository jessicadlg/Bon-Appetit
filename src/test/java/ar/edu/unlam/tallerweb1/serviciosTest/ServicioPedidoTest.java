package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Plato;
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

    @Test
    public void queSePuedaAgregarUnPlatoAUnPedido(){

        givenQueExisteUnPedidoSinPlatos();

        whenAgregoUnPlatoAlPedido();

        thenMeDevuelveElPedidoConLosPlatos();

    }

    @Test
    public void queSePuedaEliminarUnPlatoDeUnPedido(){

        givenQueExisteUnPedidoConPlatos();

        whenQuieroEliminarUnPlato();

        thenMeEliminaElPlato();


    }

    private void givenQueExisteUnPedidoConPlatos() {
        List<Producto> listaProductos = new ArrayList<>();
        Plato plato = new Plato();
        Plato plato2 = new Plato();
        plato.setPrecio(200.0);
        plato2.setPrecio(300.0);
        plato.setNombre("Pizza");
        plato2.setNombre("Pizza otra vez");
        listaProductos.add(plato);
        listaProductos.add(plato2);
        Pedido pedido = new Pedido();
        pedido.setListaProductos(listaProductos);
        pedido.setTotal(500.0);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(plato);

    }

    private void whenQuieroEliminarUnPlato() {
        pedido = servicioPedido.eliminarPlatoDeUnPedido(1L,2L);
    }

    private void thenMeEliminaElPlato() {
        assertThat(pedido.getListaProductos()).hasSize(1);
        assertThat(pedido.getTotal()).isEqualTo(300.0);
    }

    private void givenQueExisteUnPedidoSinPlatos() {
        Plato p1 = new Plato();
        p1.setPrecio(200.0);
        Pedido p2 = new Pedido();
        p2.setTotal(0.0);
        p2.setListaProductos(new ArrayList<>());
        when(repositorioPedido.obtenerPedido(1L)).thenReturn(p2);
        when(repositorioProducto.buscarProductoPorId(1L)).thenReturn(p1);
    }

    private void whenAgregoUnPlatoAlPedido() {
        pedido = servicioPedido.agregarPlatoAlPedido(1L,1L);
    }

    private void thenMeDevuelveElPedidoConLosPlatos() {
        assertThat(pedido.getListaProductos()).hasSize(1);
        assertThat(pedido.getTotal()).isEqualTo(200.0);
    }

}
