package ar.edu.unlam.tallerweb1.repositorioTest;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCategoria;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RepositorioCategoriaTest extends SpringTest {

    @Autowired
    private RepositorioCategoria repositorioCategoria;

    @Test
    @Transactional
    @Rollback
    public void queSePuedanListarTodasLasCategorias(){

        List<Categoria> categoriasEsperadas = givenQueExisteUnaListaDeCategorias();

        List<Categoria> categoriasObtenidas = whenListoLasCategorias();

        thenMeDevuelveLaListaDeCategorias(categoriasEsperadas,categoriasObtenidas);

    }

    private List<Categoria> givenQueExisteUnaListaDeCategorias() {
        List<Categoria>listaCategorias = new ArrayList<>();
        Categoria c1 = new Categoria();
        Categoria c2 = new Categoria();
        Categoria c3 = new Categoria();
        session().save(c1);
        session().save(c2);
        session().save(c3);
        listaCategorias.add(c1);
        listaCategorias.add(c2);
        listaCategorias.add(c3);
        return listaCategorias;
    }

    private List<Categoria> whenListoLasCategorias() {
        return repositorioCategoria.listarCategorias();
    }

    private void thenMeDevuelveLaListaDeCategorias(List<Categoria> categoriasEsperadas, List<Categoria> categoriasObtenidas) {
        assertThat(categoriasEsperadas).isEqualTo(categoriasObtenidas);
    }


}
