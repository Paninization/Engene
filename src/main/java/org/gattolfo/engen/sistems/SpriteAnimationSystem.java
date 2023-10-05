package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.AnimationComponent;
import org.gattolfo.engen.components.SpriteComponent;
import org.gattolfo.engen.components.StateComponent;

public class SpriteAnimationSystem extends IteratingSystem {

    ComponentMapper<SpriteComponent> spriteComponentComponentMapper;
    ComponentMapper<AnimationComponent> animationComponentComponentMapper;
    ComponentMapper<StateComponent> stateComponentComponentMapper;
    public SpriteAnimationSystem() {
        super(Family.all(SpriteComponent.class, AnimationComponent.class,StateComponent.class).get(), Priority.UPDATE_ANIM);


        spriteComponentComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
        animationComponentComponentMapper = ComponentMapper.getFor(AnimationComponent.class);
        stateComponentComponentMapper = ComponentMapper.getFor(StateComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = animationComponentComponentMapper.get(entity);
        int state = stateComponentComponentMapper.get(entity).state;

        if(animationComponent.animations.containsKey(state)){
            Sprite sprite = spriteComponentComponentMapper.get(entity).sprite;
            sprite.setRegion((TextureRegion) animationComponent.animations.get(state).getKeyFrame(animationComponent.time,animationComponent.isLooping));
        }

        animationComponent.time +=deltaTime;

    }
}
