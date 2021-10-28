package ar.edu.unlam.tallerweb1.Excepciones;

public class FechaInvalida extends RuntimeException{

    public FechaInvalida(String mensaje){
        super(mensaje);
    }

    public FechaInvalida(){
        super("No se puede elegir una fecha pasada");
    }

}
