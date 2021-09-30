package ar.edu.unlam.tallerweb1.Excepciones;

public class ListaDeMesasNoEncontrada extends Throwable {

    public ListaDeMesasNoEncontrada(String msg){
        super(msg);
    }

    public ListaDeMesasNoEncontrada(){
        super("No Hay Mesas Cargadas.");
    }
}
