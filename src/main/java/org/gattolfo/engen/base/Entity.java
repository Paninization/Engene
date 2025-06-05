package org.gattolfo.engen.base;

import org.gattolfo.engen.tools.EntityFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Entity {
    private final int id;
    private final HashMap<Class<? extends Component>, Component> components;
    private final List<EntityListener> listeners;

    public Entity() {
        id = EntityFactory.generateId();
        this.components = new HashMap<>();
        listeners = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addComponent(Component component) {
        Class<? extends Component> clazz = component.getClass();
        components.put(clazz, component);
        for (EntityListener listener : listeners) {
            listener.onComponentAdded(this, clazz);
        }
    }

    public <T extends Component> void removeComponent(Class<T> clazz) {
        if (components.remove(clazz) != null) {
            for (EntityListener listener : listeners) {
                listener.onComponentRemoved(this, clazz);
            }
        }
    }

    public <T extends Component> T getComponent(Class<T> type) {
        Component component = components.get(type);
        if (component == null) return null;

        return type.cast(component);
    }



    public boolean hasComponent(Class<? extends Component> type) {
        return components.containsKey(type);
    }

    public void addListener(EntityListener listener) {
        listeners.add(listener);
    }

    public void removeListener(EntityListener listener) {
        listeners.remove(listener);
    }


}
