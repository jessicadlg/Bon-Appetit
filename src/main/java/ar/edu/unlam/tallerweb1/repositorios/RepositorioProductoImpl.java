package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioProductoImpl implements RepositorioProducto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioProductoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Producto> listarProductos() {
        final Session session = this.sessionFactory.getCurrentSession();
        //select * from Producto;
        List<Producto>listaProducto = session.createCriteria(Producto.class).list();
        return listaProducto;
    }

    @Override
    public List<Producto> listarProductosActivos() {
        final Session session = this.sessionFactory.getCurrentSession();
        List<Producto>listaProductosActivos = session.createCriteria(Producto.class)
                .add(Restrictions.eq("activo",true)).list();
        return listaProductosActivos;
    }

    @Override
    public Producto buscarProductoPorNombre(String nombreProducto) {
        final Session session = this.sessionFactory.getCurrentSession();
        Producto productoBuscado = (Producto) session.createCriteria(Producto.class)
                            .add(Restrictions.eq("nombre",nombreProducto)).uniqueResult();
        return productoBuscado;
    }

    @Override
    public Producto getProductoById(Integer idPedido) {
        return null;
    }
}
