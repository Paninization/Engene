package org.gattolfo.engen.events;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface CollisionListener {
    public void onCollision(Fixture fixture);
    public void onCollisionExit(Fixture fixture);
    public void onEntityCollision(Entity entity);
    public void onEntityCollisionExit(Entity entity);

}
