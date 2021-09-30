package ar.edu.unlam.tallerweb1.Excepciones;

public class ClavesNoCoinciden extends RuntimeException {
    public ClavesNoCoinciden(String mensaje){
        super(mensaje);
    }

    public ClavesNoCoinciden(){
        super("Las claves no coinciden");
    }
}


