package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.components.B2dBodyComponent;
import org.gattolfo.engen.components.TransformComponent;
import org.jetbrains.annotations.NotNull;

public class PhysicsSystem extends IteratingSystem implements ContactListener {

    private ComponentMapper<B2dBodyComponent> cm;

    private Array<Entity> bodiesQueue;

    private ComponentMapper<B2dBodyComponent> bm = ComponentMapper.getFor(B2dBodyComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    @NotNull
    World world;
    public PhysicsSystem(@NotNull World world){
        super(Family.all(B2dBodyComponent.class, TransformComponent.class).get());
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
        TransformComponent transformComponent;
        B2dBodyComponent bodyComponent;
        for(Entity entity : bodiesQueue){
            transformComponent = entity.getComponent(TransformComponent.class);
            bodyComponent = entity.getComponent(B2dBodyComponent.class);
            Vector2 position = bodyComponent.body.getPosition();
            transformComponent.position.x = position.x;
            transformComponent.position.y = position.y;
            transformComponent.rotation = bodyComponent.body.getAngle() * MathUtils.radiansToDegrees;

        }

        bodiesQueue.clear();


    }

    /**
     * Handles entity and fixture collisions
     *
     * @param contact box2d
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        Entity entity;
        if(fa.getBody().getUserData() instanceof Entity){
            entity = (Entity) fa.getBody().getUserData();
            entityCollision(entity,fb);
        }else if(fb.getBody().getUserData() instanceof Entity){
            entity = (Entity) fb.getBody().getUserData();
            entityCollision(entity,fa);
        }

    }


    private void entityCollision(Entity e, Fixture f){

        B2dBodyComponent entityB2component = e.getComponent(B2dBodyComponent.class);

        if(f.getBody().getUserData() instanceof Entity fEntity){
            entityB2component.listener.onEntityCollision(fEntity);
            fEntity.getComponent(B2dBodyComponent.class).listener.onEntityCollision(e);
        }else{
            entityB2component.listener.onCollision(f);
        }

    }

    private void entityCollisionExit(Entity e,Fixture f){
        B2dBodyComponent entityB2component = e.getComponent(B2dBodyComponent.class);

        if(f.getBody().getUserData() instanceof Entity fEntity){
            entityB2component.listener.onEntityCollisionExit(fEntity);
            fEntity.getComponent(B2dBodyComponent.class).listener.onEntityCollisionExit(e);
        }else{
            entityB2component.listener.onCollisionExit(f);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        Entity entity;
        if(fa.getBody().getUserData() instanceof Entity){
            entity = (Entity) fa.getBody().getUserData();
            entityCollisionExit(entity,fb);
        }else if(fb.getBody().getUserData() instanceof Entity){
            entity = (Entity) fb.getBody().getUserData();
            entityCollisionExit(entity,fa);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public @NotNull World getWorld() {
        return world;
    }

    public void setWorld(@NotNull World world) {
        this.world = world;
    }
}
