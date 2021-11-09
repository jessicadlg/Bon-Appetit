package ar.edu.unlam.tallerweb1.Excepciones;

public class PedidoVacio extends RuntimeException{

    public PedidoVacio(String mensaje){
        super(mensaje);
    }

    public PedidoVacio(){
        super("El pedido se encuentra vacio");
    }
}
