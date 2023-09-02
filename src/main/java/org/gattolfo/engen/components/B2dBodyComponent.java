package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import org.gattolfo.engen.events.CollisionListener;
import org.jetbrains.annotations.NotNull;

public class B2dBodyComponent implements Component {

    @NotNull
    public Body body;

    @NotNull
    public CollisionListener listener = new CollisionListener() {
        @Override
        public void onCollision(Fixture fixture) {

        }

        @Override
        public void onCollisionExit(Fixture fixture) {

        }

        @Override
        public void onEntityCollision(Entity entity) {

        }

        @Override
        public void onEntityCollisionExit(Entity entity) {

        }
    };


    public B2dBodyComponent(Body body) {
        this.body = body;
    }
}
