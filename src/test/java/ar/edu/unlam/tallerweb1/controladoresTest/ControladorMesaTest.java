package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaDeMesasNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.controladores.ControladorMesa;
import ar.edu.unlam.tallerweb1.servicios.ServicioMesa;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorMesaTest {

    private ServicioMesa servicioMesa = mock(ServicioMesa.class);
    private ControladorMesa controladorMesa = new ControladorMesa(servicioMesa);

    @Test
    public void testQueCuandoNoHallaMesasCargadasDevuelvaUnMensaje() throws ListaDeMesasNoEncontrada {
        givenUnaListaDeMesasVacia();
        ModelAndView modelAndView = whenQuieroVerLasMesas();
        thenMeRetornaMensaNoHayMesasCargadas(modelAndView);
    }
    
    @Test
    public void testQueCuandoHayMesasCargadasMeLasMuestra(){
        ModelAndView modelAndView = whenQuieroVerLasMesas();
        thenNoMeRetornaMensajeDeErrorMeDevuelveLaListaDeMesasYMeRedirijeALaVistaDeMesas(modelAndView);
    }

    private void thenNoMeRetornaMensajeDeErrorMeDevuelveLaListaDeMesasYMeRedirijeALaVistaDeMesas(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("mesas");
        assertThat(modelAndView.getModel().get("error")).isNull();
        assertThat(modelAndView.getModel().get("mesas")).isNotNull();
    }

    private void givenUnaListaDeMesasVacia() throws ListaDeMesasNoEncontrada {
        doThrow(ListaDeMesasNoEncontrada.class).when(servicioMesa).getMesas();
    }

    private ModelAndView whenQuieroVerLasMesas() {
        return controladorMesa.getMesas();

    }

    private void thenMeRetornaMensaNoHayMesasCargadas(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("mesas");
        assertThat(modelAndView.getModel().get("error")).isEqualTo("No Hay Mesas Cargadas.");
    }
}
