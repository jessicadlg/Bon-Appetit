package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class ControladorPedido {

    public static ModelAndView agregarProducto(Pedido pedido, Producto producto) {
        pedido.agregarProducto(producto);
        ModelMap model = new ModelMap();
        model.put("productosPedidos",pedido.getProductosPedidos());
        return new ModelAndView("cargar-pedido",model);
    }

    public static ModelAndView EliminarProductoDelPedido(Pedido pedido, String codigoProducto) {
        ArrayList<Producto> productosPedidos = pedido.eliminarProducto(codigoProducto);
        ModelMap model = new ModelMap();
        model.put("productosPedidos", productosPedidos);
        return new ModelAndView("cargar-pedido", model);
    }
}
