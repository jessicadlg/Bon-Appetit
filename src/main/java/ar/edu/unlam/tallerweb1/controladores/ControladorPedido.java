package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosConfirmacion;
import ar.edu.unlam.tallerweb1.Excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorPedido {

    private ServicioPedido servicioPedido;
    private ServicioProducto servicioProducto;
    private ServicioCategoria servicioCategoria;
    private Routes routes;

    @Autowired
    public ControladorPedido(ServicioPedido servicioPedido, ServicioProducto servicioProducto, ServicioCategoria servicioCategoria) {
        this.servicioPedido = servicioPedido;
        this.servicioProducto = servicioProducto;
        this.servicioCategoria = servicioCategoria;
    }

    @RequestMapping("generar-pedido")
    public ModelAndView generarPedido() {
        Long idPedido = servicioPedido.generarPedido(routes);
        return new ModelAndView("redirect:pedido?idPedido=" + idPedido);
    }

    @RequestMapping("pedido")
    public ModelAndView mostrarPedido(@RequestParam Long idPedido) {

        ModelMap model = new ModelMap();

        try {
            Pedido pedido = servicioPedido.obtenerPedido(idPedido);
            traerProductos(model);
            model.put("pedido", pedido);
            model.put("idPedido", idPedido);
            List<ItemPedido> itemsPedido = servicioPedido.obtenerItemsPedido(idPedido);
            model.put("itemsPedido", itemsPedido);
        }catch (Exception e) {
            model.put("msgError", e.getMessage());
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
            model.put("itemsPedido", itemsPedido);
        } catch (Exception e) {
            model.put("msgError", e.getMessage());
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
    public ModelAndView eliminarProductoDeUnPedido(@RequestParam Long idPedido, @RequestParam Long idProducto) {

        Pedido pedido = servicioPedido.eliminarComidaDeUnPedido(idProducto, idPedido);

        if (pedido.getTotal() <= 0.0) {
            return new ModelAndView("redirect:pedido?idPedido=" + idPedido);
        }

        return new ModelAndView("redirect:carrito?idPedido=" + idPedido);

    }

    @RequestMapping("consultar")
    public ModelAndView consultarFormulario() {
        return procesarConsultaRango(null);
    }

    @RequestMapping(path = "consultarRango")
    public ModelAndView consultarRango(String calle, String altura, String localidad) {
        ModelMap model = new ModelMap();

        Map<String, String> validarConsultaRango = validarConsultaRango(calle, altura, localidad);
        if (validarConsultaRango.size() > 0) {
            model.put("validacionesRango", validarConsultaRango);
            Calles calles = servicioPedido.listarCalles();
            model.put("calles", calles);
            return new ModelAndView("formularioConsultaRango", model);
        }
        try {
            routes = servicioPedido.consultarRango(calle, altura, localidad);
            return new ModelAndView("redirect:generar-pedido");
        } catch (RangoInvalido e) {
            return new ModelAndView("redirect:consultaRangoError");
        } catch (DireccionInexistente f) {
            return procesarConsultaRango("Esta dirección no existe, por favor vuelva a intentar!");
        }
    }


    @RequestMapping("consultaRangoError")
    public ModelAndView consultarRangoFallido() {
        return procesarConsultaRango("Lamentablemente no se encuentra dentro del rango de envios!");
    }

    private ModelAndView procesarConsultaRango(String mensajeConsulta) {
        ModelMap modelMap = new ModelMap();
        if (mensajeConsulta != null) {
            modelMap.put("errorConsultaRango", mensajeConsulta);
        }
        Calles calles = servicioPedido.listarCalles();
        modelMap.put("calles", calles);
        return new ModelAndView("formularioConsultaRango", modelMap);
    }

    private Map<String, String> validarConsultaRango(String calle, String altura, String localidad) {
        Map<String, String> validacionesRango = new HashMap<>();
        if (calle == null || calle == "") {
            validacionesRango.put("calleError", "Por favor, ingrese una calle.");
        } else if (altura == null || altura.trim() == "") {
            validacionesRango.put("alturaError", "Por favor, ingrese una altura.");
        } else if (Integer.parseInt(altura) < 1) {
            validacionesRango.put("alturaError", "La altura debe ser mayor a cero.");
        } else if (localidad == null || localidad == "") {
            validacionesRango.put("localidadError", "Por favor,ingrese una localidad.");
        }
        return validacionesRango;
    }

    @RequestMapping("confirmarPedido")
    public ModelAndView irAformularioConfirmacion(@RequestParam Long idPedido) {
        return redirigirCompra(null,idPedido);
    }

    @RequestMapping("procesarCompra")
    public ModelAndView procesarCompra(@ModelAttribute DatosConfirmacion datosConfirmacion){

        Map<String,String> validacionesCompra = validarCompra(datosConfirmacion);
        if(validacionesCompra.size()>2){
            return redirigirCompra(validacionesCompra,datosConfirmacion.getIdPedido());
        }
        servicioPedido.confirmarCompra(datosConfirmacion);

        return new ModelAndView("redirect:compra-exitosa");
    }

    @RequestMapping("compra-exitosa")
    public ModelAndView compraExitosa(){
        return new ModelAndView("compraExitosa");
    }


    private ModelAndView redirigirCompra(Map<String,String> validaciones,Long idPedido){

        ModelMap model = new ModelMap();
        model.put("viaje", routes);
        if(validaciones!=null){
            model.put("validacionesCompra",validaciones);
        }
        try{
            Pedido pedido = this.servicioPedido.obtenerPedido(idPedido);
            model.put("pedido", pedido);
            model.put("idPedido", idPedido);
            List<ItemPedido> itemsPedido = servicioPedido.obtenerItemsPedido(idPedido);
            model.put("itemsPedido", itemsPedido);
            model.put("datosConfirmacion",new DatosConfirmacion());
        } catch (PedidoInexistente f) {
            model.put("pedidoInexistente", "Este pedido no existe");
        }
        return new ModelAndView("formularioPedido",model);
    }

    private Map<String,String> validarCompra(DatosConfirmacion datosConfirmacion){
       Map<String,String> validacionesCompra = new HashMap<>();
       validacionesCompra.put("nombreDefault",datosConfirmacion.getNombre());
       validacionesCompra.put("telefonoDefault",datosConfirmacion.getTelefono());

       if(datosConfirmacion.getNombre()==null||datosConfirmacion.getNombre().trim().equals("")){
           validacionesCompra.put("nombreError","Por favor ingrese su nombre.");
       }
       if(datosConfirmacion.getTelefono()==null||datosConfirmacion.getTelefono().trim().equals("")){
           validacionesCompra.put("telefonoError","Por favor ingrese un número de telefono.");
       }
        return validacionesCompra;
    }

    @RequestMapping("lista-pedidos")
    public ModelAndView listarPedidos() {
        return redirigirListaPedido(null);
    }

    @RequestMapping(path = "lista-pedidos-filtro")
    public ModelAndView filtrarPedidoPorEstado(@RequestParam String filtro) {
        return redirigirListaPedido(filtro);
    }

    private ModelAndView redirigirListaPedido(String filtro){
        ModelMap model = new ModelMap();
        List<Pedido> listaPedidos;
        try{
            if(filtro!=null){
                listaPedidos = servicioPedido.listarPedidosPorEstado(filtro);
            }else{
                listaPedidos = servicioPedido.listarPedidos();
            }
            model.put("listaPedidos",listaPedidos);
        }catch (listaPedidosNoEncontrada e){
            model.put("listaPedidosVacia","No hay pedidos que listar");
        }
        return new ModelAndView("lista-pedidos",model);
    }
//@RequestMapping(path = "lista-pedido")
//public ModelAndView filtrarPedidoPorEstado(@RequestParam String filtro) {
//    return redirigirListaPedido(filtro);
//}
//
//    private ModelAndView redirigirListaPedido(String filtro){
//        ModelMap model = new ModelMap();
//        List<Pedido> listaPedidos;
//        try{
//            if(filtro!=null){
//                listaPedidos = servicioPedido.listarPedidosPorEstado(filtro);
//            }else{
//                listaPedidos = servicioPedido.listarPedidos();
//            }
//            model.put("listaPedidos",listaPedidos);
//        }catch (listaPedidosNoEncontrada e){
//            model.put("listaPedidosVacia","No hay pedidos que listar");
//        }
//        return new ModelAndView("lista-pedidos",model);
//    }

    @RequestMapping(value = "cambiar-estado")
    public ModelAndView cambiarEstadoDeUnPedido(@RequestParam Long idPedido,@RequestParam String estado) {

        ModelMap model = new ModelMap();

        try{
            servicioPedido.cambiarEstadoDeUnPedido(idPedido,estado);
        }catch (PedidoFinalizado e){
            model.put("pedidoError","No se puede cambiar el estado de un pedido finalizado");
            return new ModelAndView("lista-pedidos",model);
        }

        return new ModelAndView("redirect:lista-pedidos");
    }
}
