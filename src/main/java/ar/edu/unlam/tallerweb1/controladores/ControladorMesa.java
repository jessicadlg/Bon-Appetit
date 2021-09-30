package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ListaDeMesasNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Mesa;
import ar.edu.unlam.tallerweb1.servicios.ServicioMesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
@Controller
public class ControladorMesa {

    private ServicioMesa servicioMesa;

    @Autowired
    public ControladorMesa(ServicioMesa servicioMesa){
        this.servicioMesa = servicioMesa;
    }

    public ModelAndView getMesas() {
        ModelMap modelMap = new ModelMap();
        try {
            ArrayList<Mesa> mesas = servicioMesa.getMesas();
            modelMap.put("mesas", mesas);
        } catch (ListaDeMesasNoEncontrada e) {
            modelMap.put("error","No Hay Mesas Cargadas.");
        }
        return new ModelAndView("mesas", modelMap);
    }
}
