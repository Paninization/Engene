package org.gattolfo.engen.component.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gattolfo.engen.component.TransformComponent;

public interface Renderable {
    void render(SpriteBatch batch, TransformComponent transform, float deltaTime);
}
