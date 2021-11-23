package ioprocessors.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioprocessors.input.enums.TestInputFiles;
import pairing.entities.PairGraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class JsonFileOutputWriter
{
    public <T> void pullToOutput(T object)
    {
        pullToOutput(object, TestInputFiles.KIVALASZTOTT);
    }

    public <T> void pullToOutput(T object, TestInputFiles testInputFiles)
    {
        try {
            File file = Paths.get(
                System.getProperty("user.dir"),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                    + "-"
                    + testInputFiles.name()
                    + "_"
                    + object.getClass().getName()
                            + "_"
                    + UUID.randomUUID()
                    + ".json"
            ).toFile();

            new ObjectMapper().writeValue(file, object);
        } catch (IOException ioException) {
            throw new OutputFailedException("A kiírás nem sikerült!", ioException);
        }
    }
}
