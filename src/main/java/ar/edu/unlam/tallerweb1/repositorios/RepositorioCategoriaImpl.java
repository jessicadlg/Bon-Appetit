package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioCategoriaImpl implements RepositorioCategoria{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCategoriaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Categoria> listarCategorias() {
        final Session session = this.sessionFactory.getCurrentSession();
        List<Categoria>listaCategoriasObtenidas = session.createCriteria(Categoria.class).list();
        // .createSQLQuery("select * from Categoria c join Producto p on c.ID_CATEGORIA=p.categoria_ID_CATEGORIA").list();
        //List<Categoria>listaCategoriasObtenidas2 = session.createCriteria(Categoria.class)
        //                      .createAlias("categoria.ID_CATEGORIA","join").list();

        return listaCategoriasObtenidas;
    }
}
