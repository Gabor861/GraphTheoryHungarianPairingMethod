package ioprocessors.input.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioprocessors.input.entities.GraphInputFile;
import pairing.exceptions.GraphTheoryException;

import java.io.File;
import java.io.IOException;

public class JsonGraphInputFileReader
{
    public GraphInputFile readFile(String filename)
    {
        return getInputGraph(new File(filename));
    }

    private GraphInputFile getInputGraph(File jsonFileContent){
        try {
            return new ObjectMapper().readValue(jsonFileContent, GraphInputFile.class);
        } catch (IOException ioException) {
            throw new GraphTheoryException("Input file mapping failed!", ioException);
        }
    }
}
