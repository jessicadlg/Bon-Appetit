package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaDeMesasNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Mesa;

import java.util.ArrayList;

public interface ServicioMesa {
    ArrayList<Mesa> getMesas() throws ListaDeMesasNoEncontrada;
}
