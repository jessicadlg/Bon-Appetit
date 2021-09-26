package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedido{
    @Override
    public ArrayList<Pedido> getPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido p1 = new Pedido(1);
        Pedido p2 = new Pedido(2);
        Pedido p3 = new Pedido(3);
        Pedido p4 = new Pedido(4);
        Pedido p5 = new Pedido(5);
        pedidos.add(p1);
        pedidos.add(p2);
        pedidos.add(p3);
        pedidos.add(p4);
        pedidos.add(p5);
        return pedidos;
    }

    @Override
    public Pedido crearPedido() {
        return new Pedido(1);
    }

    @Override
    public Pedido agregarProducto(Integer idPedido, Integer idProducto) {
        Pedido pedido = new Pedido(1);
        Producto p1  = new Producto(1L,"Cafe Con Leche", 150.00);
        pedido.getProductosPedidos().add(p1);
        return pedido;
    }
}
