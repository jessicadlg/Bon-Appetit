package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaPedidosException;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoNoCreadoException;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedido{

    private RepositorioPedido repositorioPedido;
    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioPedidoImpl(RepositorioPedido repositorioPedido, RepositorioProducto repositorioProducto){
        this.repositorioPedido = repositorioPedido;
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public ArrayList<Pedido> getPedidos() throws ListaPedidosException {
        ArrayList<Pedido> pedidos = repositorioPedido.getPedidos();
        if(pedidos.size() > 0){
            return pedidos;
        }else{
            throw new ListaPedidosException();
        }
    }

    @Override
    public Pedido crearPedido() throws PedidoNoCreadoException{
        Pedido pedido = repositorioPedido.crearPedido();
        if(pedido.getId() != null){
            return pedido;
        }else{
            throw new PedidoNoCreadoException();
        }

    }

    @Override
    public Pedido agregarProducto(Integer idPedido, Integer idProducto) {
        Pedido pedido = repositorioPedido.agregarProducto(idPedido, idProducto);
        return pedido;
    }
}
