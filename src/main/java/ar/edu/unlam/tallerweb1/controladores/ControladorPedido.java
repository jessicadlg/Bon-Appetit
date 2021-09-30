package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ListaPedidosException;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPedido {

    private ServicioPedido servicioPedido;
    private ServicioProducto servicioProducto;

    @Autowired
    public ControladorPedido(ServicioPedido servicioPedido, ServicioProducto servicioProducto) {
        this.servicioPedido = servicioPedido;
        this.servicioProducto = servicioProducto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pedidos")
    public ModelAndView verPedidos(){
        ModelMap modelMap = new ModelMap();
        ArrayList<Pedido> pedidos = null;
        try {
            pedidos = servicioPedido.getPedidos();
        } catch (ListaPedidosException e) {
            e.printStackTrace();
        }
        modelMap.put("pedidos", pedidos);
        return new ModelAndView("pedidos", modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/nuevoPedido")
    public ModelAndView crearPedido() {
        ModelMap modelMap = new ModelMap();
        Pedido pedidoNuevo = servicioPedido.crearPedido();
        ArrayList<Producto> productos = servicioProducto.getProductos();
        modelMap.put("pedido", pedidoNuevo);
        modelMap.put("productos", productos);
        return new ModelAndView("nuevoPedido", modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pedido")
    public ModelAndView agregarProducto(Long pedido, Integer producto) {
        ModelMap modelMap = new ModelMap();
        Pedido pedidoConProductoCargado = servicioPedido.agregarProducto(pedido, producto);
        ArrayList<Producto> productos = servicioProducto.getProductos();
        modelMap.put("pedido", pedidoConProductoCargado);
        modelMap.put("productos", productos);
        return new ModelAndView("nuevoPedido", modelMap);
    }
}
