package ar.edu.unlam.tallerweb1.Excepciones;

public class UsuarioExistente extends RuntimeException {
    public UsuarioExistente(String mensaje){
        super(mensaje);
    }

    public UsuarioExistente(){
        super("Usuario no disponible");
    }
}

