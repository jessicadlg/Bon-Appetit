package ar.edu.unlam.tallerweb1.Excepciones;

public class ProductoNoEncontrado extends RuntimeException{

    public ProductoNoEncontrado(String mensaje){
        super(mensaje);
    }

    public ProductoNoEncontrado(){
        super("No se encontro el producto buscado");
    }
}
