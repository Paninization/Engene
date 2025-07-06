package org.gattolfo.engen.system.helper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gattolfo.engen.component.TransformComponent;

public interface Renderable {
    void render(SpriteBatch batch, TransformComponent transform, float deltaTime);
}
