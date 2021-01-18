package org.epam.textparser.reader;


import org.epam.textparser.exception.ProjectException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataReader {
    private static final String DEFAULT_PATH = "data/text.txt";

    public String readAll(String filePath) throws ProjectException {
        if (filePath == null || !Files.exists(Paths.get(filePath))) {
            filePath = DEFAULT_PATH;
        }
        String text;
        try {
            text = Files.readString(Paths.get(filePath));
        } catch (IOException exp) {
            throw new ProjectException("Error while opening file: " + filePath, exp);
        }
        return text;
    }
}
