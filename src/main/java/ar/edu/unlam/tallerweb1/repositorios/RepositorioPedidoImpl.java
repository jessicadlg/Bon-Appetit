package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPedidoImpl implements RepositorioPedido{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPedidoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pedido obtenerPedido(Long idPedido) {
        final Session session = this.sessionFactory.getCurrentSession();
        Pedido pedido = (Pedido) session.createCriteria(Pedido.class)
                        .add(Restrictions.eq("id",idPedido)).uniqueResult();

        return pedido;
    }

    @Override
    public void actualizarPedido(Pedido pedido) {
        sessionFactory.getCurrentSession().save(pedido);
    }

    @Override
    public Long generarPedido(Pedido pedido) {
        return (Long) sessionFactory.getCurrentSession().save(pedido);
    }

    @Override
    public ItemPedido obtenerItemPedido(Long idPedido, Long idProducto) {
        final Session session = this.sessionFactory.getCurrentSession();
        ItemPedido itemPedido = (ItemPedido) session.createCriteria(ItemPedido.class)
                .add(Restrictions.eq("producto.id",idProducto))
                .add(Restrictions.eq("pedido.id",idPedido))
                .uniqueResult();

        return itemPedido;
    }

    @Override
    public void guardarItemPedido(ItemPedido itemPedido) {
        sessionFactory.getCurrentSession().save(itemPedido);
    }

    @Override
    public List<ItemPedido> obtenerItemsPedido(Long idPedido) {
        final Session session = this.sessionFactory.getCurrentSession();
        List<ItemPedido>itemPedidos = session.createCriteria(ItemPedido.class)
                .add(Restrictions.eq("pedido.id",idPedido)).list();
        return itemPedidos;
    }

    public void eliminarItemPedido(ItemPedido itemPedido){
        final Session session = this.sessionFactory.getCurrentSession();
        session.delete("ItemPedido",itemPedido);
    }

}
