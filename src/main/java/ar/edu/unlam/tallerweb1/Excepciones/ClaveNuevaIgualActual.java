package ar.edu.unlam.tallerweb1.Excepciones;

public class ClaveNuevaIgualActual extends RuntimeException{
    public ClaveNuevaIgualActual(String mensaje){
        super(mensaje);
    }

    public ClaveNuevaIgualActual(){
        super("La clave nueva no puede ser igual a la actual");
    }
}

