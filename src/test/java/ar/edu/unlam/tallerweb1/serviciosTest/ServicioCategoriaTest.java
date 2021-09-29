package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaCategoriaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoriaImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioCategoriaTest {

    private RepositorioCategoria repositorioCategoria = mock(RepositorioCategoria.class);
    private ServicioCategoria servicioCategoria = new ServicioCategoriaImpl(repositorioCategoria);
    List<Categoria> listaCategoria = new ArrayList<>();

    @Test
    public void queSePuedanListarLasCategorias(){

        givenQueExisteUnaListaDeCategorias();

        whenListoLasCategorias();

        thenMeDevuelveLaListaDeCategorias();

    }

    @Test(expected = ListaCategoriaNoEncontrada.class)
    public void queCuandoNoExisteUnaListaDeCategoriasMeLanzeUnaCategoriaNoEncontradaException(){

        givenQueExisteUnaListaDeCategoriasVacia();
        
        whenListoLasCategorias();
        
    }

    private void givenQueExisteUnaListaDeCategoriasVacia() {
        when(repositorioCategoria.listarCategorias()).thenReturn(new ArrayList<Categoria>());
    }

    private void givenQueExisteUnaListaDeCategorias() {
        List<Categoria>categoriasObtenidas = new ArrayList<>();
        Categoria c1 = new Categoria();
        Categoria c2 = new Categoria();
        Categoria c3 = new Categoria();
        Categoria c4 = new Categoria();
        categoriasObtenidas.add(c1);
        categoriasObtenidas.add(c2);
        categoriasObtenidas.add(c3);
        categoriasObtenidas.add(c4);
        when(repositorioCategoria.listarCategorias()).thenReturn(categoriasObtenidas);
    }

    private void whenListoLasCategorias() {
        listaCategoria = servicioCategoria.listarCategorias();
    }

    private void thenMeDevuelveLaListaDeCategorias() {
        assertThat(listaCategoria).isNotNull();
        assertThat(listaCategoria).hasSize(4);
    }


}
