package ar.edu.unlam.tallerweb1.Excepciones;

public class listaPedidosNoEncontrada extends RuntimeException{

    public listaPedidosNoEncontrada() {
        super("No hay pedidos que listar");
    }

    public listaPedidosNoEncontrada(String message) {
        super(message);
    }
}
