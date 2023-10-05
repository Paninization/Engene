package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.ScriptComponent;
import org.gattolfo.engen.components.SpriteComponent;
import org.gattolfo.engen.components.TransformComponent;

public class UpdateSystem extends IteratingSystem {

    private ComponentMapper<ScriptComponent> scMapper = ComponentMapper.getFor(ScriptComponent.class);

    private Array<Entity> updateQueue;
    public UpdateSystem() {
        super(Family.all(ScriptComponent.class).get(), Priority.UPDATE);

        updateQueue = new Array<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        updateQueue.add(entity);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity e : updateQueue){

            scMapper.get(e).script.update(deltaTime);
        }

        updateQueue.clear();
    }
}
