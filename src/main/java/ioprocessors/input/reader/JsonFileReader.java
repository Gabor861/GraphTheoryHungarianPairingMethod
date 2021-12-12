package ioprocessors.input.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import pairing.exceptions.GraphTheoryException;

import java.io.File;
import java.io.IOException;

public class JsonFileReader<T>
{
    public T readFile(String filename, Class<T> asClass)
    {
        return getInput(new File(filename), asClass);
    }

    private T getInput(File jsonFileContent, Class<T> asClass)
    {
        try {
            return new ObjectMapper().readValue(jsonFileContent, asClass);
        } catch (IOException ioException) {
            throw new GraphTheoryException(jsonFileContent.getName() + " input file olvasása közben hiba történt!", ioException);
        }
    }
}
