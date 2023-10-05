package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.AnimationComponent;
import org.gattolfo.engen.components.SpriteComponent;
import org.gattolfo.engen.components.StateComponent;

public class AnimationSystem extends IteratingSystem {


    ComponentMapper<SpriteComponent> tm;
    ComponentMapper<AnimationComponent> am;
    ComponentMapper<StateComponent> sm;
    public AnimationSystem() {
        super(Family.all(SpriteComponent.class, StateComponent.class).get(), Priority.UPDATE_ANIM);


        tm = ComponentMapper.getFor(SpriteComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        AnimationComponent ani = am.get(entity);
        StateComponent state = sm.get(entity);

        if(ani.animations.containsKey(state.state)){
            SpriteComponent tex = tm.get(entity);
            tex.sprite.setRegion((TextureRegion) ani.animations.get(state.state).getKeyFrame(ani.time, ani.isLooping));
        }
        ani.time += deltaTime;
    }
}
