package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.gattolfo.engen.components.AnimationComponent;
import org.gattolfo.engen.components.StateComponent;
import org.gattolfo.engen.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {


    ComponentMapper<TextureComponent> tm;
    ComponentMapper<AnimationComponent> am;
    ComponentMapper<StateComponent> sm;
    public AnimationSystem() {
        super(Family.all(TextureComponent.class, StateComponent.class).get());


        tm = ComponentMapper.getFor(TextureComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        AnimationComponent ani = am.get(entity);
        StateComponent state = sm.get(entity);

        if(ani.animations.containsKey(state.state)){
            TextureComponent tex = tm.get(entity);
            tex.region = (TextureRegion) ani.animations.get(state.state).getKeyFrame(ani.time, ani.isLooping);
            tex.region.flip(tex.flipped[0],tex.flipped[1]);
        }

        ani.time += deltaTime;
    }
}
