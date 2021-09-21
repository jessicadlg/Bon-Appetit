package ar.edu.unlam.tallerweb1.Excepciones;

public class ListaNoEncontrada extends RuntimeException{

    public ListaNoEncontrada(String mensaje){
        super(mensaje);
    }

    public ListaNoEncontrada(){
        super("No se pudo encontrar la lista de productos");
    }
}
