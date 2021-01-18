package org.epam.textparser.parser;

import org.epam.textparser.exception.ProjectException;
import org.epam.textparser.parser.impl.ParagraphParser;
import org.epam.textparser.reader.DataReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ParagraphParserTest {
    private ParagraphParser parser;
    private String currentText;
    private final static String FILEPATH = "data/test/parser/paragraph.txt";

    @BeforeClass
    public void setUp() throws ProjectException {
        parser = ParagraphParser.getInstance();
        currentText = new DataReader().readAll(FILEPATH);
    }

    @Test
    public void parseTestValid() {
        int expectedSentences = 2;
        int actualSentences = parser.parse(currentText).getComponents().size();
        assertEquals(actualSentences, expectedSentences);
    }

    @Test
    public void parseTestInvalid() {
        int expectedSentences = 7;
        int actualSentences = parser.parse(currentText).getComponents().size();
        assertNotEquals(actualSentences, expectedSentences);
    }

    @AfterClass
    public void tierDown() {
        parser = null;
    }
}
