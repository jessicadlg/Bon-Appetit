package ar.edu.unlam.tallerweb1.controladores;

<<<<<<< HEAD
import ar.edu.unlam.tallerweb1.Excepciones.ReservaException;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
=======
import ar.edu.unlam.tallerweb1.AttributeModel.DatosReserva;
>>>>>>> a9c1b7364ba8c61f0677cb7c486f5d1b35ab1434
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class ControladorReserva {

=======
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.PortUnreachableException;


@Controller
public class ControladorReserva {
>>>>>>> a9c1b7364ba8c61f0677cb7c486f5d1b35ab1434
    private ServicioReserva servicioReserva;

    @Autowired
    public ControladorReserva(ServicioReserva servicioReserva) {
        this.servicioReserva = servicioReserva;
    }

<<<<<<< HEAD
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
=======
    @RequestMapping(path ="/irAReservar",method = RequestMethod.GET)
    public ModelAndView irAFormularioDeReserva(){
        ModelMap model = new ModelMap();
        model.put("datosReserva",new DatosReserva());
        return new ModelAndView("reservaMesa",model);
    }



>>>>>>> a9c1b7364ba8c61f0677cb7c486f5d1b35ab1434
}
