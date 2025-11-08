package Excepciones;

public class NoHayStockEnTiendaException extends RuntimeException {
    public NoHayStockEnTiendaException(String message) {
        super(message);
    }
}
