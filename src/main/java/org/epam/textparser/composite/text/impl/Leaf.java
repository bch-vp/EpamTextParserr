package org.epam.textparser.composite.text.impl;

import org.epam.textparser.composite.text.Component;
import org.epam.textparser.composite.text.ComponentType;

import java.util.List;

public class Leaf implements Component {
    public enum Type {
        PUNCTUATION, CHARACTER;
    }

    private String content;
    private Type type;

    public Leaf(String symbol, Type type) {
        this.content = symbol;
        this.type = type;
    }

    @Override
    public ComponentType getType() {
        return ComponentType.SYMBOL;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public List<Component> getComponents() {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @Override
    public Component getComponent(int index) {
        throw new UnsupportedOperationException("Method is not supported");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Leaf symbolLeaf1 = (Leaf) o;

        if (content.equals(symbolLeaf1.content)) {
            return false;
        }
        return type == symbolLeaf1.type;
    }

    @Override
    public int hashCode() {
        int result = Integer.parseInt(content);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return content;
    }
}
