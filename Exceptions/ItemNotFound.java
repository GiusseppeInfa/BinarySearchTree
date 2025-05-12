package Exceptions;

public class ItemNotFound extends Exception {
    public ItemNotFound(String message) {
        super(message);
    }

    public ItemNotFound() {
        super("El elemento no se encontró en la estructura de datos.");
    }
}