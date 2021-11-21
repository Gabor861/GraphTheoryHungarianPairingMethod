package ioprocessors.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import pairing.entities.PairGraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class PairGraphJsonFileOutputWriter
{
    public void pullToOutput(PairGraph pairGraph)
    {
        try {
            File file = Paths.get(System.getProperty("user.dir"), UUID.randomUUID() + ".json").toFile();

            new ObjectMapper().writeValue(file, pairGraph);
        } catch (IOException ioException) {
            throw new OutputFailedException("A kiírás nem sikerült!", ioException);
        }
    }
}
