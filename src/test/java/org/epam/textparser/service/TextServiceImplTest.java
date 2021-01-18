package org.epam.textparser.service;

import org.epam.textparser.composite.text.Component;
import org.epam.textparser.exception.ProjectException;
import org.epam.textparser.parser.impl.TextParser;
import org.epam.textparser.reader.DataReader;
import org.epam.textparser.service.impl.TextServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

public class TextServiceImplTest {
    private TextService service;
    private String text;

    @BeforeClass
    public void setUp() throws ProjectException {
        service = TextServiceImpl.getInstance();
        text = new DataReader().readAll("data/test/service/text.txt");
    }

    @Test
    public void testValidSortSentencesByLengthLexeme() {
        String expected = "\tIt is a 0.0 established fact that a reader will be of a page when " +
                "looking at its layout.\n" +
                "\tBye.\n" +
                "\tIt has survived - not only (five) centuries, but also the leap into 16 electronic " +
                "typesetting, remaining 8 essentially -3 unchanged. It was popularised in the " +
                "125 with the release of Letraset sheets containing Lorem Ipsum " +
                "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                "versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a " +
                "page when looking at its layout. The point of using 350.0 Ipsum" +
                " is that it has a more-or-less normal distribution of letters, as opposed to using " +
                "(Content here), content here', making it look like readable English.";
        Component component = TextParser.getInstance().parse(text);
        String actual = service.sortParagraphsByQuantitySentences(component).toString();
        assertEquals(actual, expected);
    }

    @Test
    public void testValidFindSentenceContainsLongestWord() {
        String expected = "The point of using 350.0 Ipsum" +
                " is that it has a more-or-less normal distribution of letters, as opposed to using " +
                "(Content here), content here', making it look like readable English.";
        Component component = TextParser.getInstance().parse(text);
        String actual = service.findSentenceContainsLongestWord(component).toString().trim();
        assertEquals(actual, expected);
    }

    @Test
    public void testValidRemoveSentencesContainWordsLessNumber() {
        Integer expected = 0;
        Component component = TextParser.getInstance().parse(text);
        service.removeSentencesContainWordsLessNumber(component, 2);
        Integer actual = component.getComponent(3).getComponents().size();
        assertEquals(actual, expected);
    }

    @Test
    public void testValidFindQuantitySameWordsWithoutRegister() {
        String expected = "{here=2, fact=2, be=2, reader=2, It=6, when=2, content=3, The=5, that=3, of=6, has=2, " +
                "Ipsum=3, established=2, readable=2, a=7, using=2, like=2, will=2, its=2, is=3, it=6, the=5, " +
                "layout=2, with=2, at=2, Lorem=2, Content=3, looking=2, page=2}";
        Component component = TextParser.getInstance().parse(text);
        Map<String, Integer> actualMap = service.findQuantitySameWordsWithoutRegister(component);
        String actual = actualMap.toString();
        assertEquals(actual, expected);
    }
}