package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioProductoImpl implements ServicioProducto {


    RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioProductoImpl(RepositorioProducto repositorioProducto){
        this.repositorioProducto = repositorioProducto;
    }


    @Override
    public List<Producto> listarProductos()  {

        if(this.repositorioProducto.listarProductos().size()>0){
            return this.repositorioProducto.listarProductos();
        }else{
            throw new ListaNoEncontrada();
        }
    }

    @Override
    public List<Producto> listarProductosActivos() throws ListaNoEncontrada {

        if(this.repositorioProducto.listarProductosActivos().size()>0){
            return this.repositorioProducto.listarProductosActivos();
        }else{
            throw new ListaNoEncontrada();
        }

    }
}
