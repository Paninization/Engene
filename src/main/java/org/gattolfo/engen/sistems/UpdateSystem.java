package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.components.ScriptComponent;

public class UpdateSystem extends IteratingSystem {

    private ComponentMapper<ScriptComponent> scMapper;

    private Array<Entity> updateQueue;
    public UpdateSystem() {
        super(Family.all(ScriptComponent.class).get());

        scMapper = ComponentMapper.getFor(ScriptComponent.class);

        updateQueue = new Array<>();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        System.out.println("upadte");
        updateQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {

        for (Entity e : updateQueue){

            scMapper.get(e).script.update(deltaTime);
        }
    }
}
