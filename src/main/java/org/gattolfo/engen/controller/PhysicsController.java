package org.gattolfo.engen.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import org.gattolfo.engen.components.B2dBodyComponent;

public class PhysicsController implements ContactListener {
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
}
