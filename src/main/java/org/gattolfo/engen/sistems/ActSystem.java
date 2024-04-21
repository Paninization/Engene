package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.ActorComponent;
import org.gattolfo.engen.components.ScriptComponent;

public class ActSystem extends IteratingSystem {

    private ComponentMapper<ActorComponent> scMapper = ComponentMapper.getFor(ActorComponent.class);
    private Array<Entity> updateQueue;
    public ActSystem() {
        super(Family.all(ActorComponent.class).get(), Priority.UPDATE);
        updateQueue = new Array<>();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        updateQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for(Entity entity : updateQueue) {
            scMapper.get(entity).act(deltaTime);
        }
        updateQueue.clear();
    }
}
