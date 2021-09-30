package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mesa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class RepositorioMesaImpl implements RepositorioMesa{
    @Override
    public ArrayList<Mesa> getMesas() {
        Mesa mesa = new Mesa(1L ,"Ocupada");
        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(mesa);
        return mesas;
    }
}
