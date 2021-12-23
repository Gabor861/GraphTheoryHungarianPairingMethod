package pairing.exceptions;

public class GraphTheoryException extends RuntimeException {
    public GraphTheoryException(String message) {
        super(message);
    }

    public GraphTheoryException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
