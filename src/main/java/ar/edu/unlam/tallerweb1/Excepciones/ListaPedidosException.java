package ar.edu.unlam.tallerweb1.Excepciones;

public class ListaPedidosException extends Throwable {

    public ListaPedidosException(){
        super("No se pudo encontrar la lista de productos");
    }
}
