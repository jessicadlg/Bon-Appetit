package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaPedidosException;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class RepositorioPedidoImpl implements RepositorioPedido{


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPedidoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<Pedido> getPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        Pedido p3 = new Pedido();
        Pedido p4 = new Pedido();
        Pedido p5 = new Pedido();
        pedidos.add(p1);
        pedidos.add(p2);
        pedidos.add(p3);
        pedidos.add(p4);
        pedidos.add(p5);
        return pedidos;
    }

    @Override
    public Pedido crearPedido() {
        Pedido pedidoNuevo = new Pedido();
        this.sessionFactory.getCurrentSession().save(pedidoNuevo);
        //falta traer el pedido recien guardado para poder pasar el id
        return null;
    }

    @Override
    public Pedido agregarProducto(Integer idPedido, Integer idProducto) {
        return new Pedido();
    }
}
//“SELECT MAX(id) AS id FROM tabla”

