package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RepositorioReservaImpl implements RepositorioReserva{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioReservaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Long guardarReserva(Reserva reserva) {
        return  (Long)this.sessionFactory.getCurrentSession().save(reserva);

    }

    @Override
    public Reserva buscarPorId(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Reserva reserva = (Reserva) session.createCriteria(Reserva.class).add(Restrictions.eq("id",id)).uniqueResult();
        return reserva;
    }

    @Override
    public Long obtenerMesasReservadasPor(Date fecha, String hora) {
        Session session = this.sessionFactory.getCurrentSession();
        Long mesasReservadas = (Long)session.createCriteria(Reserva.class).add(Restrictions.eq("fecha", fecha)).add(Restrictions.eq("hora",hora)).setProjection(Projections.sum("mesas")).uniqueResult();
        return mesasReservadas;
    }
}
