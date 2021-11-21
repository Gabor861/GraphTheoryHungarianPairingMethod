package ioprocessors.output;

import pairing.exceptions.GraphTheoryException;

public class OutputFailedException extends GraphTheoryException
{
    public OutputFailedException(String message) {
        super(message);
    }

    public OutputFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
