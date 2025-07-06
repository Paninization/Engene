package org.gattolfo.engen.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import org.gattolfo.engen.component.TransformComponent;

public class TransformSystem extends EntitySystem {


    private ImmutableArray<Entity> entities;

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public TransformSystem() {
        super(SystemPriorities.TRANSFORM);
    }

    public TransformSystem(int priority) {
        super(priority);
    }
    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            TransformComponent transform = tm.get(entities.get(i));
            transform.updateWorldTransformIfNeeded();
        }
    }
}
