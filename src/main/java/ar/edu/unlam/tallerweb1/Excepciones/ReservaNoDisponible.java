package ar.edu.unlam.tallerweb1.Excepciones;

public class ReservaNoDisponible extends RuntimeException{

    public ReservaNoDisponible(){
        super("No hay disponibilidad para la Fecha Especificada.");
    }
}
