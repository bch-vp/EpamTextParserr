package org.epam.textparser.parser;

import org.epam.textparser.exception.ProjectException;
import org.epam.textparser.parser.impl.ParagraphParser;
import org.epam.textparser.parser.impl.TextParser;
import org.epam.textparser.reader.DataReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class TextParserTest {
    private TextParser parser;
    private String currentText;
    private final static String FILEPATH = "data/test/parser/text.txt";

    @BeforeClass
    public void setUp() throws ProjectException {
        parser = TextParser.getInstance();
        currentText = new DataReader().readAll(FILEPATH);
    }

    @Test
    public void parseTestValid() {
        int expectedParagraphs = 4;
        int actualParagraphs = parser.parse(currentText).getComponents().size();
        assertEquals(actualParagraphs, expectedParagraphs);
    }

    @Test
    public void parseTestInvalid() {
        int expectedParagraphs = 2;
        int actualParagraphs = parser.parse(currentText).getComponents().size();
        assertNotEquals(actualParagraphs, expectedParagraphs);
    }

    @AfterClass
    public void tierDown() {
        parser = null;
    }
}
