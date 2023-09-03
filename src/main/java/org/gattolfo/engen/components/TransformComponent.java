package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


/**
 * The transform component adds the following properties, position, size, rotation, if it is hidden, it is necessary if you want the entity to be rendered by RenderingSystem
 */
public class TransformComponent implements Component {

    public final Vector3 position = new Vector3();
    public final Vector2 size = new Vector2(1.0f, 1.0f);
    
    public float rotation = 0.0f;

    /**
     * if true, the entity will not be rendered
     */
    public boolean isHidden = false;


}
