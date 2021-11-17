package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioPedidoTest extends SpringTest {

    @Autowired
    private RepositorioPedido repositorioPedido;
    private Ubicacion ubicacion;

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerUnPedidoPorId(){

        Long idPedido = givenQueExisteUnPedido();

        Pedido pedidoObtenido = whenBuscoUnPedidoPorId(idPedido);

        thenMeDevuelveElPedidoBuscado(idPedido, pedidoObtenido);

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaObtenerLatitudYLongitud(){

        whenBuscoLaLatidudYLongitud();

        thenMeDevuelveLaUbicacion();

    }

    private void whenBuscoLaLatidudYLongitud() {
        ubicacion = repositorioPedido.obtenerLatitudLongitud("Jose Ignacio Rucci","592");
    }

    private void thenMeDevuelveLaUbicacion() {
        assertThat(ubicacion).isNotNull();
        assertThat(ubicacion.getLat()).isEqualTo(-34.68837769963411);
        assertThat(ubicacion.getLon()).isEqualTo(-58.596913157319044);
    }

    private Long givenQueExisteUnPedido() {
        Pedido p1 = new Pedido();
        return (Long) session().save(p1);
    }

    private Pedido whenBuscoUnPedidoPorId(Long idPedido) {
        return repositorioPedido.obtenerPedido(idPedido);
    }

    private void thenMeDevuelveElPedidoBuscado(Long idPedido, Pedido pedidoObtenido) {
        assertThat(pedidoObtenido).isNotNull();
        assertThat(idPedido).isEqualTo(pedidoObtenido.getId());
    }

}
