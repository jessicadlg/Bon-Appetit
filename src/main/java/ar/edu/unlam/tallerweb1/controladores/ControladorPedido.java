package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPedido {

    private ServicioPedido servicioPedido;

    @Autowired
    public ControladorPedido(ServicioPedido servicioPedido){
        this.servicioPedido = servicioPedido;
    }


    public ModelAndView agregarPlatoAlPedido(@RequestParam Long idProducto, @RequestParam Long idPedido) {

        ModelMap model = new ModelMap();

        try{
            Pedido pedido = this.servicioPedido.agregarPlatoAlPedido(idProducto,idPedido);
            model.put("pedido",pedido);
        }catch (Exception e){

        }
        return new ModelAndView("listaProductos",model);
    }
}
