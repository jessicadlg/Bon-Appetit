package ar.edu.unlam.tallerweb1.Excepciones;

public class ReservaException extends RuntimeException {

    public ReservaException(String mensaje){
        super(mensaje);
    }

    public ReservaException(){
        super("No se ha podido realizar la Reserva.");
    }
}
