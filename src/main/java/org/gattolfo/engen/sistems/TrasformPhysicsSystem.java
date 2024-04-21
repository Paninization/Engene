package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.B2dBodyComponent;
import org.gattolfo.engen.components.ActorComponent;
import org.jetbrains.annotations.NotNull;

public class TrasformPhysicsSystem extends IteratingSystem {

    private ComponentMapper<B2dBodyComponent> cm;

    private Array<Entity> bodiesQueue;

    private ComponentMapper<B2dBodyComponent> bm = ComponentMapper.getFor(B2dBodyComponent.class);
    private ComponentMapper<ActorComponent> tm = ComponentMapper.getFor(ActorComponent.class);
    @NotNull
    World world;
    public TrasformPhysicsSystem(@NotNull World world){
        super(Family.all(B2dBodyComponent.class, ActorComponent.class).get(), Priority.UPDATE_PHYSICS);
        this.world = world;
        bodiesQueue = new Array<>();

    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodiesQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        world.step(deltaTime,6,2);
        ActorComponent transformComponent;
        B2dBodyComponent bodyComponent;
        for(Entity entity : bodiesQueue){
            transformComponent = tm.get(entity);
            bodyComponent = bm.get(entity);
            Vector2 position = bodyComponent.body.getPosition();
            transformComponent.setWorldPosition(position.x,position.y);
        }
        bodiesQueue.clear();
    }


    public void setWorld(@NotNull World world) {
        this.world = world;
    }
}
