package ioprocessors.output.exceptions;

import pairing.exceptions.GraphTheoryException;

public class OutputFailedException extends GraphTheoryException
{
    public OutputFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
