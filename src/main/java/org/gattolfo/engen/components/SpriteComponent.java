package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

import java.util.Map;

public class SpriteComponent extends DrawablePattern {

    public Sprite sprite;


    public SpriteComponent(TextureRegion textureRegion){
        sprite = new Sprite(textureRegion);


    }

    public SpriteComponent(TextureRegion textureRegion,int layer){
        sprite = new Sprite(textureRegion);
    }
    public SpriteComponent(Sprite sprite){
        this.sprite = sprite;
    }

    @Override
    public void draw(SpriteBatch batch, OrthographicCamera matrix4) {
        sprite.draw(batch);
    }
}
