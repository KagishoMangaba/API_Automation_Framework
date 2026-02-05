package org.kagisho.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kagisho.models.Place;

import java.io.IOException;
import java.io.InputStream;

public class JsonReaderUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonReaderUtil() {
        // private constructor to prevent instantiation
    }

    public static Place readJson(String fileName) throws IOException {
        // Load the file from classpath
        InputStream is = JsonReaderUtil.class.getClassLoader().getResourceAsStream(fileName);

        if (is == null) {
            throw new IOException("File not found in classpath: " + fileName);
        }

        // Convert JSON to Java object
        return mapper.readValue(is, Place.class);
    }
}
