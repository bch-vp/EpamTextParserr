package org.epam.textparser.parser.impl;

import org.epam.textparser.composite.text.Component;
import org.epam.textparser.composite.text.ComponentType;
import org.epam.textparser.composite.text.impl.Composite;
import org.epam.textparser.composite.text.impl.Leaf;
import org.epam.textparser.parser.BaseParser;

import java.util.regex.Pattern;

public class LexemeParser extends BaseParser {
    private static final LexemeParser instance = new LexemeParser();
    private static final String LETTER_REGEX = "";
    private static final String PUNCTUATION_REGEX = "\\.{3}|[\\.,?!]";

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        return instance;
    }

    @Override
    public Component parse(String text) {
        Component lexemeComposite = new Composite(ComponentType.LEXEME);
        Leaf symbolLeaf;

        String[] symbols = text.split(LETTER_REGEX);
        for (String element : symbols) {
            if (Pattern.matches(PUNCTUATION_REGEX, element)) {
                symbolLeaf = new Leaf(element, Leaf.Type.PUNCTUATION);
            } else {
                symbolLeaf = new Leaf(element, Leaf.Type.CHARACTER);
            }
            lexemeComposite.add(symbolLeaf);
        }

        return lexemeComposite;
    }
}
