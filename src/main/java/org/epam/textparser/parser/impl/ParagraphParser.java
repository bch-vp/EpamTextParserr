package org.epam.textparser.parser.impl;

import org.epam.textparser.composite.text.Component;
import org.epam.textparser.composite.text.ComponentType;
import org.epam.textparser.composite.text.impl.Composite;
import org.epam.textparser.parser.BaseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends BaseParser {
    private static final ParagraphParser instance = new ParagraphParser();
    public static final String SENTENCE_REGEX = "[^.!?]+[.!?]{1,3}";

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        return instance;
    }

    @Override
    public Component parse(String text) {
        setNext(SentenceParser.getInstance());

        Component paragraphComposite = new Composite(ComponentType.PARAGRAPH);
        Component sentenceComposite;
        Matcher matcher = Pattern.compile(SENTENCE_REGEX).matcher(text);
        List<String> sentences = new ArrayList<>();
        while (matcher.find()) {
            String sentenceString = matcher.group();
            String sentenceStringTrimedd = sentenceString.trim();
            sentences.add(sentenceStringTrimedd);
        }
        for (String sentence : sentences) {
            sentenceComposite = parseNext(sentence);
            paragraphComposite.add(sentenceComposite);
        }
        return paragraphComposite;
    }
}
