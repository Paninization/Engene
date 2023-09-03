package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;


public class AnimationComponent implements Component {

    /**
     * Animations mapped with an INT, the one that has the same index as the StateComponent will be chosen to render
     */
    public IntMap<Animation> animations = new IntMap<>();

    public boolean isLooping = false;

    public float time = 0.0f;

}
