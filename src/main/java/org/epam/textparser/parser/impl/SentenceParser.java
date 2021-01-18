package org.epam.textparser.parser.impl;

import org.epam.textparser.composite.text.Component;
import org.epam.textparser.composite.text.ComponentType;
import org.epam.textparser.composite.text.impl.Composite;
import org.epam.textparser.interpreter.TextInterpreter;
import org.epam.textparser.parser.BaseParser;

import java.util.regex.Pattern;

public class SentenceParser extends BaseParser {
    public static final SentenceParser instance = new SentenceParser();

    private static final String LEXEME_REGEX = " ";
    private static final String CONDITION_CALCULATION_REGEX = "\\p{N}+";

    private SentenceParser(){

    }

    public static SentenceParser getInstance(){
        return instance;
    }

    @Override
    public Component parse(String text) {
        setNext(LexemeParser.getInstance());

        Component sentenceComposite = new Composite(ComponentType.SENTENCE);
        Component lexemeComposite;
        String[] lexemes = text.split(LEXEME_REGEX);

        Pattern pattern = Pattern.compile(CONDITION_CALCULATION_REGEX);
        for (int i = 0; i < lexemes.length; i++) {
            if (pattern.matcher(lexemes[i]).find()) {
                lexemes[i] = TextInterpreter.convertExpression(lexemes[i]);
            }
        }

        for(String lexeme:lexemes){
            lexemeComposite = parseNext(lexeme);
            sentenceComposite.add(lexemeComposite);
        }

        return sentenceComposite;
    }
}
