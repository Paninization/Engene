package org.gattolfo.engen.base;

public interface EntityListener {
    void onComponentAdded(Entity entity, Class<? extends Component> componentClass);
    void onComponentRemoved(Entity entity, Class<? extends Component> componentClass);
}
