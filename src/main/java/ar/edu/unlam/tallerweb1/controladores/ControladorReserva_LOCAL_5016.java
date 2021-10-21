package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class ControladorReserva {

    private ServicioReserva servicioReserva;

    @Autowired
    public ControladorReserva(ServicioReserva servicioReserva) {
        this.servicioReserva = servicioReserva;
    }

    @RequestMapping("/confirmarReserva")
    public ModelAndView confirmarReserva() {
        ModelMap modelMap = new ModelMap();
        try {
            Reserva reserva = new Reserva();
            servicioReserva.confirmarReserva(reserva);
            modelMap.put("mnsj","La Reserva se ha realizado con Exito!");
        } catch (ReservaException e) {
            modelMap.put("mnsj","No se ha podido realizar la Reserva.");
        }
        return new ModelAndView("reserva", modelMap);
    }
}
