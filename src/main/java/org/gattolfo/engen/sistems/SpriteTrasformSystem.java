package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.B2dBodyComponent;
import org.gattolfo.engen.components.SpriteComponent;
import org.gattolfo.engen.components.TransformComponent;


public class SpriteTrasformSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> trasformComponentMapper;
    private ComponentMapper<SpriteComponent> spriteComponentComponentMapper;

    Array<Entity> updateQueue;
    public SpriteTrasformSystem() {
        super(Family.all(TransformComponent.class, SpriteComponent.class).get(), Priority.UPDATE_TRASFORM);

        trasformComponentMapper = ComponentMapper.getFor(TransformComponent.class);
        spriteComponentComponentMapper = ComponentMapper.getFor(SpriteComponent.class);

        updateQueue = new Array<>();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        updateQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        TransformComponent traform;
        Sprite sprite;

        for(Entity e : updateQueue){
            traform = trasformComponentMapper.get(e);
            sprite = spriteComponentComponentMapper.get(e).sprite;


            sprite.setPosition(traform.position.x - (traform.size.x/2),traform.position.y-(traform.size.y/2));
            sprite.setSize(traform.size.x,traform.size.y);
            sprite.setOriginCenter();
            sprite.setRotation(traform.rotation);



        }
    }
}
