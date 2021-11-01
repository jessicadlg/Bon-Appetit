package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPedidoImpl implements RepositorioPedido{

    private SessionFactory session;

    @Autowired
    public RepositorioPedidoImpl(SessionFactory sessionFactory){
        this.session = sessionFactory;
    }

    @Override
    public Pedido obtenerPedido(Long id) {
        return new Pedido();
    }

    @Override
    public void actualizarPedido(Pedido pedido) {
        session.getCurrentSession().save(pedido);
    }
}
