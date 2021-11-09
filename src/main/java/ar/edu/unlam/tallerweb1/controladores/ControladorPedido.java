package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ListaCategoriaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoInexistente;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoVacio;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.ItemPedido;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPedido {

    private ServicioPedido servicioPedido;
    private ServicioProducto servicioProducto;
    private ServicioCategoria servicioCategoria;

    @Autowired
    public ControladorPedido(ServicioPedido servicioPedido, ServicioProducto servicioProducto, ServicioCategoria servicioCategoria) {
        this.servicioPedido = servicioPedido;
        this.servicioProducto = servicioProducto;
        this.servicioCategoria = servicioCategoria;
    }

    @RequestMapping("generar-pedido")
    public ModelAndView generarPedido() {
        Long idPedido = servicioPedido.generarPedido();
        return new ModelAndView("redirect:pedido?idPedido=" + idPedido);
    }

    @RequestMapping("pedido")
    public ModelAndView mostrarPedido(@RequestParam Long idPedido) {

        ModelMap model = new ModelMap();

        try {
            Pedido pedido = servicioPedido.obtenerPedido(idPedido);
            traerProductos(model);
            model.put("pedido", pedido);
            model.put("idPedido",idPedido);
            List<ItemPedido> itemsPedido = servicioPedido.obtenerItemsPedido(idPedido);
            model.put("itemsPedido",itemsPedido);
        } catch (ListaNoEncontrada e) {
            model.put("msgError", "No hay productos");
        } catch (ListaCategoriaNoEncontrada f) {
            model.put("categoriasNoEncontradas", "No se encontro ninguna categoria por mostrar");
        } catch (PedidoInexistente g){
            model.put("pedidoInexistente","Este pedido no existe");
        } catch (PedidoVacio h){
            model.put("pedidoVacio","Su pedido está vacio");
        }

        return new ModelAndView("productos", model);
    }

    @RequestMapping(path = "agregar-producto")
    public ModelAndView agregarProductoAlPedido(@RequestParam Long idProducto, @RequestParam Long idPedido) {

        this.servicioPedido.agregarProductoAlPedido(idProducto, idPedido);

        return new ModelAndView("redirect:carrito?idPedido=" + idPedido);
    }

    @RequestMapping("carrito")
    public ModelAndView carrito(@RequestParam Long idPedido) {
        ModelMap model = new ModelMap();

        try {
            Pedido pedido = this.servicioPedido.obtenerPedido(idPedido);
            traerProductos(model);
            model.put("pedido", pedido);
            model.put("idPedido", idPedido);
            List<ItemPedido> itemsPedido = servicioPedido.obtenerItemsPedido(idPedido);
            model.put("itemsPedido",itemsPedido);
        } catch (ListaNoEncontrada e) {
            model.put("msgError", "No hay productos");
        } catch (ListaCategoriaNoEncontrada f) {
            model.put("categoriasNoEncontradas", "No se encontro ninguna categoria por mostrar");
        } catch (PedidoInexistente f){
            model.put("pedidoInexistente","Este pedido no existe");
        } catch (PedidoVacio h){
            model.put("pedidoVacio","Su pedido está vacio");
        }
        return new ModelAndView("productos", model);
    }

    private void traerProductos(ModelMap model) {
        List<Producto> listaProductos = this.servicioProducto.listarProductos();
        List<Categoria> listaCategorias = this.servicioCategoria.listarCategorias();
        List<Producto> destacados = this.servicioProducto.listarDestacados();
        model.put("listaProductos", listaProductos);
        model.put("listaCategorias", listaCategorias);
        model.put("destacados", destacados);
    }

    @RequestMapping("eliminar-producto")
    public ModelAndView eliminarProductoDeUnPedido(@RequestParam Long idPedido,@RequestParam Long idProducto){

        Pedido pedido = servicioPedido.eliminarComidaDeUnPedido(idProducto,idPedido);

        if(pedido.getTotal()<=0.0){
            return new ModelAndView("redirect:pedido?idPedido=" + idPedido);
        }

        return new ModelAndView("redirect:carrito?idPedido=" + idPedido);

    }


}
