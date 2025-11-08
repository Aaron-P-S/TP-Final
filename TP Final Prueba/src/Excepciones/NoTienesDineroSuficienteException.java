package Excepciones;

public class NoTienesDineroSuficienteException extends RuntimeException {
    public NoTienesDineroSuficienteException(String message) {
        super(message);
    }
}
