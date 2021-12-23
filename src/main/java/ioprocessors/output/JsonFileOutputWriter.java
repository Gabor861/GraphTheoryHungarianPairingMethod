package ioprocessors.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioprocessors.output.exceptions.OutputFailedException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonFileOutputWriter {
    private static final String OUPUT_FILE_EXTENSION = ".json";

    private static final String OUPUT_FILE_DATETIME_FORMAT = "yyyyMMdd_HHmmss";

    public <T> void pullToOutput(T object, Path inputFilePath) {
        try {
            File outputFile = getFile(object, inputFilePath);
            new File(outputFile.getParent().toString()).mkdirs();
            new ObjectMapper().writeValue(outputFile, object);
        } catch (IOException ioException) {
            throw new OutputFailedException("A kiírás nem sikerült!", ioException);
        }
    }

    private <T> File getFile(T object, Path inputFilePath) {
        return Paths.get(
                        System.getProperty("user.dir"),
                        "output",
                        getOutputFileName(object.getClass().getName(), inputFilePath))
                .toFile();
    }

    private String getOutputFileName(String className, Path inputFilePath) {
        return getOutputFileDatetime()
                + "-"
                + inputFilePath.getFileName().toString()
                + "_"
                + className
                // + "_" + UUID.randomUUID()
                + OUPUT_FILE_EXTENSION;
    }

    private String getOutputFileDatetime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(OUPUT_FILE_DATETIME_FORMAT));
    }
}
