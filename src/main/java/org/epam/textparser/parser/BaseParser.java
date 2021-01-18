package org.epam.textparser.parser;


import org.epam.textparser.composite.text.Component;

public abstract class BaseParser {
    private BaseParser next;

    public BaseParser setNext(BaseParser next) {
        this.next = next;
        return next;
    }

    public abstract Component parse(String text);

    protected Component parseNext(String text) {
        return next.parse(text);
    }
}
