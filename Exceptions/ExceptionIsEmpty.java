package Exceptions;

public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String message) {
        super(message);
    }
    
    public ExceptionIsEmpty() {
    	super("La estructura del árbol está vacia");
    }
}