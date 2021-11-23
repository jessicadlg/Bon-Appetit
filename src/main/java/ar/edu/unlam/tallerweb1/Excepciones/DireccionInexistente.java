package ar.edu.unlam.tallerweb1.Excepciones;

public class DireccionInexistente extends Throwable{

    public DireccionInexistente(String message) {
        super(message);
    }

    public DireccionInexistente(){
        super("Direccion inexistente");
    }
}
