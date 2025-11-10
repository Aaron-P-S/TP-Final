package Excepciones;

public class NoTieneElItemException extends RuntimeException {
    public NoTieneElItemException(String message) {
        super(message);
    }
}
