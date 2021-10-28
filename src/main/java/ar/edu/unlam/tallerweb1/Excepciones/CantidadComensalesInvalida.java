package ar.edu.unlam.tallerweb1.Excepciones;

public class CantidadComensalesInvalida extends RuntimeException {
    public CantidadComensalesInvalida(){
        super("La cantidad de comensales debe ser mayor a cero.");
    }
}
