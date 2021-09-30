package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaDeMesasNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Mesa;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class ServicioMesaImpl implements ServicioMesa{

    private RepositorioMesa repositorioMesa;

    @Autowired
    public ServicioMesaImpl(RepositorioMesa repositorioMesa){
        this.repositorioMesa = repositorioMesa;
    }

    @Override
    public ArrayList<Mesa> getMesas() throws ListaDeMesasNoEncontrada {
        ArrayList<Mesa> mesas = repositorioMesa.getMesas();
        if(!(mesas.size() > 0)){
            throw new ListaDeMesasNoEncontrada();
        }
        return mesas;
    }
}
