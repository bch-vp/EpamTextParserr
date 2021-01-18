package org.epam.textparser.parser;

import org.epam.textparser.exception.ProjectException;
import org.epam.textparser.parser.impl.LexemeParser;
import org.epam.textparser.parser.impl.SentenceParser;
import org.epam.textparser.reader.DataReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class SentenceParserTest {
    private SentenceParser parser;
    private String currentText;
    private final static String FILEPATH = "data/test/parser/sentence.txt";

    @BeforeClass
    public void setUp() throws ProjectException {
        parser = SentenceParser.getInstance();
        currentText = new DataReader().readAll(FILEPATH);
    }

    @Test
    public void parseTestValid() {
        int expectedLexeme = 21;
        int actualLexeme = parser.parse(currentText).getComponents().size();
        assertEquals(actualLexeme, expectedLexeme);
    }

    @Test
    public void parseTestInvalid() {
        int expectedLexeme = 7;
        int actualLexeme = parser.parse(currentText).getComponents().size();
        assertNotEquals(actualLexeme, expectedLexeme);
    }

    @AfterClass
    public void tierDown() {
        parser = null;
    }
}
