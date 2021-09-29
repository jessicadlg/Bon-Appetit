package ar.edu.unlam.tallerweb1.Excepciones;

public class PedidoNoCreadoException extends RuntimeException {

    public PedidoNoCreadoException(){
        super("No se ha podido crear el Pedido.");
    }
}
