package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
