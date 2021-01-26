package org.epam.textparser.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.textparser.comparator.QuantitySentencesComparator;
import org.epam.textparser.composite.text.Component;
import org.epam.textparser.service.TextService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextServiceImpl implements TextService {
    private static final TextServiceImpl instance = new TextServiceImpl();

    private static final Logger logger = LogManager.getLogger();

    private static final String PUNCTUATION_REPLACE_REGEX = "[^1-9a-zA-Z-_]";

    private TextServiceImpl() {
    }

    public static TextServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Component sortParagraphsByQuantitySentences(Component textComposite) {
        logger.info("Start to sort paragraphs by quantity of sentences : \n {}", textComposite);
        List<Component> paragraphs = textComposite.getComponents();
        paragraphs.sort(new QuantitySentencesComparator());
        logger.info("Sorted paragraphs by quantity of sentences : \n {}", textComposite);
        return textComposite;
    }

    @Override
    public Component findSentenceContainsLongestWord(Component textComposite) {
        logger.info("Start to find sentence contain longgest word : \n {}", textComposite);
        Component resultSentence = null;
        String longestWord = "";
        for (Component paragraphs : textComposite.getComponents()) {
            for (Component sentence : paragraphs.getComponents()) {
                for (Component lexeme : sentence.getComponents()) {
                    String lexemeWord = lexeme.toString();
                    String lexemeWordPunctuationReplaced =
                            lexemeWord.replaceAll(PUNCTUATION_REPLACE_REGEX, "");
                    if (longestWord.length() < lexemeWordPunctuationReplaced.length()) {
                        longestWord = lexemeWordPunctuationReplaced;
                        resultSentence = sentence;
                    }
                }
            }
        }
        logger.info("Found sentence contain longgest word : {}", resultSentence);
        return resultSentence;
    }

    @Override
    public Component removeSentencesContainWordsLessNumber(Component textComposite, int number) {
        logger.info("Start to remove sentence, which contain words less number : {} : \n {}",
                number,
                textComposite);
        for (Component paragraph : textComposite.getComponents()) {
            List<Component> sentences = paragraph.getComponents();
            for (int i = 0; i < paragraph.getComponents().size(); i++) {
                if (sentences.get(i).getComponents().size() < number) {
                    paragraph.remove(sentences.get(i));
                    i--;
                }
            }
        }
        logger.info("Removed sentence, which contain words less number : {} : {}",number, textComposite);
        return textComposite;
    }

    @Override
    public Map<String, Integer> findQuantitySameWordsWithoutRegister(Component textComposite) {
        logger.info("Start to find quantity same words without register : \n {}", textComposite);
        Map<String, Integer> result = new HashMap<>();
        Integer quantity = 0;

        for (Component paragraph : textComposite.getComponents()) {
            for (Component sentence : paragraph.getComponents()) {
                for (Component lexeme : sentence.getComponents()) {
                    String lexemeWord = lexeme.toString();
                    String lexemeWordPunctuationReplaced =
                            lexemeWord.replaceAll(PUNCTUATION_REPLACE_REGEX, "");
                    quantity = 0;
                    if (!result.containsKey(lexemeWordPunctuationReplaced)) {
                        for (Component paragraphN : textComposite.getComponents()) {
                            for (Component sentenceN : paragraphN.getComponents()) {
                                for (Component lexemeN : sentenceN.getComponents()) {
                                    String lexemeWordN = lexemeN.toString();
                                    String lexemeWordPunctuationReplacedN =
                                            lexemeWordN.replaceAll(PUNCTUATION_REPLACE_REGEX, "");

                                    if (lexemeWordPunctuationReplacedN.equalsIgnoreCase(lexemeWordPunctuationReplaced)) {
                                        quantity++;
                                    }
                                }
                            }
                        }
                        if (quantity > 1) {
                            result.put(lexemeWordPunctuationReplaced, quantity);
                        }
                    }
                }
            }
        }
        logger.info("Found quantity same words without register : {}", result);
        return result;
    }
}
