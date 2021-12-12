package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pairing.exceptions.GraphTheoryException;

import java.io.IOException;

public class ObjectCloneUtil
{
    private ObjectCloneUtil() {}

    public static <T> T getDeepCopy(T pm)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return (T) objectMapper
                .readValue(
                        objectMapper.writeValueAsString(pm),
                        pm.getClass()
                );
        } catch (IOException ioException) {
            throw new GraphTheoryException("DeepCopy készítés közben hiba történt!", ioException);
        }
    }
}
