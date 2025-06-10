package org.gattolfo.engen.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import org.gattolfo.engen.component.TransformComponent2D;

public class TransformSystem2D extends EntitySystem {

    private final ComponentMapper<TransformComponent2D> tm = ComponentMapper.getFor(TransformComponent2D.class);

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(TransformComponent2D.class).get());

        for (Entity entity : entities) {
            TransformComponent2D transform = tm.get(entity);
            transform.attachTo(entity); // serve per usare thisEntity()

            if (transform.parent == null) {
                transform.worldPosition.set(transform.localPosition);
                transform.worldRotation = transform.localRotation;
                transform.worldScale.set(transform.localScale);
            } else {
                TransformComponent2D parent = tm.get(transform.parent);
                if (parent != null) {
                    transform.worldPosition.set(parent.worldPosition).add(transform.localPosition);
                    transform.worldRotation = parent.worldRotation + transform.localRotation;
                    transform.worldScale.set(parent.worldScale).scl(transform.localScale);
                }
            }
        }
    }
}
