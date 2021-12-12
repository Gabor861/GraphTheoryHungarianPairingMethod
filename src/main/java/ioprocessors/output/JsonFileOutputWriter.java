package ioprocessors.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioprocessors.input.enums.TestInputFiles;
import ioprocessors.output.exceptions.OutputFailedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonFileOutputWriter
{
    private static final String OUPUT_FILE_EXTENSION = ".json";

    private static final String OUPUT_FILE_DATETIME_FORMAT = "yyyyMMdd_HHmmss";

    public <T> void pullToOutput(T object, TestInputFiles testInputFiles)
    {
        try {
            new ObjectMapper().writeValue(getFile(object, testInputFiles), object);
        } catch (IOException ioException) {
            throw new OutputFailedException("A kiírás nem sikerült!", ioException);
        }
    }

    private <T> File getFile(T object, TestInputFiles testInputFiles)
    {
        return
            Paths.get(
                System.getProperty("user.dir"),
                getOutputFileName(
                    object.getClass().getName(),
                    testInputFiles
                )
            ).toFile();
    }

    private String getOutputFileName(String className, TestInputFiles testInputFiles)
    {
        return
            getOutputFileDatetime()
            + "-" + testInputFiles.name()
            + "_" + className
            // + "_" + UUID.randomUUID()
            + OUPUT_FILE_EXTENSION;
    }

    private String getOutputFileDatetime()
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(OUPUT_FILE_DATETIME_FORMAT));
    }
}
