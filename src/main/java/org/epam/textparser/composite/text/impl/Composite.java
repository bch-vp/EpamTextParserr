package org.epam.textparser.composite.text.impl;

import org.epam.textparser.composite.text.Component;
import org.epam.textparser.composite.text.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private static final String PARAGRAPH = "\n\t";
    private static final String TABULATION = "\t";
    private static final String SPACE = " ";
    private final ComponentType type;
    private final List<Component> components = new ArrayList<>();

    public Composite(ComponentType textComponentType) {
        this.type = textComponentType;
    }

    @Override
    public void add(Component component) {
        this.components.add(component);
    }

    @Override
    public void remove(Component component) {
        this.components.remove(component);
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public Component getComponent(int index) {
        return this.components.get(index);
    }

    @Override
    public ComponentType getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Composite that = (Composite) o;

        if (type != that.type) {
            return false;
        }
        return components != null ? components.equals(that.components) : that.components == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean isFirstSentence = true;

        for (Component component : components) {
            if(isFirstSentence && ComponentType.PARAGRAPH.equals(component.getType())){
                builder.append(TABULATION);
                isFirstSentence=false;
            } else if (ComponentType.PARAGRAPH.equals(component.getType())) {
                builder.append(PARAGRAPH);
            }else if (ComponentType.SENTENCE.equals(component.getType())) {
                builder.append(SPACE);
            } else if (ComponentType.LEXEME.equals(component.getType())) {
                builder.append(SPACE);
            }
            isFirstSentence = false;
            builder.append(component.toString().trim());
        }
        return builder.toString();
    }
}
