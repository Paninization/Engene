package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public abstract class DrawablePattern implements Component {

    public abstract void draw(SpriteBatch batch, OrthographicCamera matrix4);
}
