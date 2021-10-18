package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.PortUnreachableException;


@Controller
public class ControladorReserva {
    private ServicioReserva servicioReserva;

    @Autowired
    public ControladorReserva(ServicioReserva servicioReserva) {
        this.servicioReserva = servicioReserva;
    }

    @RequestMapping(path ="/irAReservar",method = RequestMethod.GET)
    public ModelAndView irAFormularioDeReserva(){
        ModelMap model = new ModelMap();
        model.put("datosReserva",new DatosReserva());
        return new ModelAndView("reservaMesa",model);
    }



}
