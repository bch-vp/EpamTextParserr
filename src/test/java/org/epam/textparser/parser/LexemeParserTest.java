package org.epam.textparser.parser;

import org.epam.textparser.exception.ProjectException;
import org.epam.textparser.parser.impl.LexemeParser;
import org.epam.textparser.reader.DataReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class LexemeParserTest {
    private LexemeParser parser;
    private String currentText;
    private final static String FILEPATH = "data/test/parser/lexeme.txt";

    @BeforeClass
    public void setUp() throws ProjectException {
        parser = LexemeParser.getInstance();
        currentText = new DataReader().readAll(FILEPATH);
    }

    @Test
    public void parseTestValid() {
        int expectedSymbols = 5;
        int actualSymbols = parser.parse(currentText).getComponents().size();
        assertEquals(actualSymbols, expectedSymbols);
    }

    @Test
    public void parseTestInvalid() {
        int expectedSymbols = 7;
        int actualSymbols = parser.parse(currentText).getComponents().size();
        assertNotEquals(actualSymbols, expectedSymbols);
    }

    @AfterClass
    public void tierDown() {
        parser = null;
    }
}
