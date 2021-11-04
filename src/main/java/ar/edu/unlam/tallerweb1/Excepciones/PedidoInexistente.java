package ar.edu.unlam.tallerweb1.Excepciones;

public class PedidoInexistente extends RuntimeException{

    public PedidoInexistente(String mensaje){
        super(mensaje);
    }

    public PedidoInexistente(){
        super("Este pedido no existe");
    }

}
