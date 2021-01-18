package org.epam.textparser.composite.text;

import java.util.List;

public interface Component {
    void add(Component component);
    void remove(Component component);
    List<Component> getComponents();
    Component getComponent(int index);
    ComponentType getType();
}
