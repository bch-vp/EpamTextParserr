package org.epam.textparser.parser.impl;

import org.epam.textparser.composite.text.Component;
import org.epam.textparser.composite.text.ComponentType;
import org.epam.textparser.composite.text.impl.Composite;
import org.epam.textparser.parser.BaseParser;


public class TextParser extends BaseParser {
    private static final TextParser instance = new TextParser();

    private static final String TABULATION_REGEX = "\t";
    private static final String NEW_LINE_REGEX = "\\n";

    private TextParser() {
    }

    public static TextParser getInstance() {
        return instance;
    }

    @Override
    public Component parse(String text) {
        setNext(ParagraphParser.getInstance());

        Component paragraphComposite;
        Component textComposite = new Composite(ComponentType.TEXT);
        String[] paragraphs = text.split(NEW_LINE_REGEX);
        for (String element : paragraphs) {
            element = element.replace(TABULATION_REGEX, "");
            element = element.replace("    ", "");
            paragraphComposite = parseNext(element);
            textComposite.add(paragraphComposite);
        }
        return textComposite;
    }
}
