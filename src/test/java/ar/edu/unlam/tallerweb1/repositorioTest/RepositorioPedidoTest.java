package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioPedidoTest extends SpringTest {

    @Autowired
    private RepositorioPedido repositorioPedido;

    @Test
    @Rollback @Transactional
    public void crearPedidoExitoso(){
        Pedido pedido = whenCreoUnPedidoNuevo();
        thenObtengoELId(pedido);
    }

    private Pedido whenCreoUnPedidoNuevo() {
        return repositorioPedido.crearPedido();
    }

    private void thenObtengoELId(Pedido pedido) {
        assertThat(pedido.getId()).isNotNull();
    }
}
