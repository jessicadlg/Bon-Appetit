package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaDeMesasNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Mesa;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMesa;
import ar.edu.unlam.tallerweb1.servicios.ServicioMesa;
import ar.edu.unlam.tallerweb1.servicios.ServicioMesaImpl;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioMesaTest {

    private static final ArrayList<Mesa> LISTA_MESAS_CARGADA = new ArrayList<Mesa>();
    private RepositorioMesa repositorioMesa = mock(RepositorioMesa.class);
    ServicioMesa servicioMesa = new ServicioMesaImpl(repositorioMesa);

    @Test(expected = ListaDeMesasNoEncontrada.class)
    public void testQuePruebaQueCuandoNoHayMesasCargadasLanceException() throws ListaDeMesasNoEncontrada {
        whenPidoVerLasMesas();
    }

    @Test
    public void testQueCuandoHayMesasCargadasMeDevuelveUnaListaDeMesas() throws ListaDeMesasNoEncontrada {
        givenUnaListaDeMesasCargada();
        ArrayList<Mesa> mesas = whenPidoVerLasMesas();
        thenMeDevuelveUnaListaConMesas(mesas);
    }

    private void thenMeDevuelveUnaListaConMesas(ArrayList<Mesa> mesas) {
        assertThat(mesas).hasSize(2);
    }

    private void givenUnaListaDeMesasCargada() {
        LISTA_MESAS_CARGADA.add(new Mesa());
        LISTA_MESAS_CARGADA.add(new Mesa());
        when(repositorioMesa.getMesas()).thenReturn(LISTA_MESAS_CARGADA);
    }

    private ArrayList<Mesa> whenPidoVerLasMesas() throws ListaDeMesasNoEncontrada {
        return servicioMesa.getMesas();
    }
}
