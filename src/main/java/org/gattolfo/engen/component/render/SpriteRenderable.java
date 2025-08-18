package org.gattolfo.engen.component.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import org.gattolfo.engen.component.TransformComponent;

public class SpriteRenderable implements Renderable {
    private final Sprite sprite;

    public SpriteRenderable(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void render(SpriteBatch batch, TransformComponent transform, float deltaTime) {
        transform.updateWorldTransformIfNeeded();
        sprite.setOriginCenter();
        sprite.setCenter(transform.getWorldPosition().x, transform.getWorldPosition().y);
        sprite.setPosition(transform.getWorldPosition().x, transform.getWorldPosition().y);
        sprite.setRotation(transform.getWorldRotation().getAngleAround(Vector3.Z));
        sprite.setScale(transform.getWorldScale().x, transform.getWorldScale().y);

        sprite.draw(batch);
    }
}
