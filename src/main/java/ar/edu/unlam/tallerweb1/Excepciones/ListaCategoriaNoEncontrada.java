package ar.edu.unlam.tallerweb1.Excepciones;

public class ListaCategoriaNoEncontrada extends RuntimeException{

    public ListaCategoriaNoEncontrada(String mensaje){
        super(mensaje);
    }

    public ListaCategoriaNoEncontrada(){
        super("No se pudo encontrar la lista de categorias");
    }


}
