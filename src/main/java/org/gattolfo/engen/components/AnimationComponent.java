package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

public class AnimationComponent implements Component {
    public IntMap<Animation> animations = new IntMap<>();

    public boolean isLooping = false;

    public float time = 0.0f;

}
