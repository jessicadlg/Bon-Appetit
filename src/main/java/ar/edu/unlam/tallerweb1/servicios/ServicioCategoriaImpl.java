package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaCategoriaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioCategoriaImpl implements ServicioCategoria{

    private RepositorioCategoria repositorioCategoria;

    @Autowired
    public ServicioCategoriaImpl(RepositorioCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
    }

    @Override
    public List<Categoria> listarCategorias() {

        if(this.repositorioCategoria.listarCategorias().size()<1){
            throw new ListaCategoriaNoEncontrada();
        }


        return this.repositorioCategoria.listarCategorias();
    }
}
