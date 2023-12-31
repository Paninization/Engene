package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


/**
 * The transform component adds the following properties, position, size, rotation, if it is hidden, it is necessary if you want the entity to be rendered by RenderingSystem
 */
public class TransformComponent implements Component {

    public final Vector3 position = new Vector3(0,0,0);
    public final Vector2 size = new Vector2(1.0f, 1.0f);

    public int layer = 0;

    public float rotation = 0.0f;

    /**
     * if true, the entity will not be rendered
     */
    public boolean isHidden = false;

    public TransformComponent(){

    }

    public TransformComponent(Vector2 size,Vector3 position){
        this.size.set(size);
        this.position.set(position);
    }
    public TransformComponent(Vector3 position,int layer){
        this.layer = layer;
        this.position.set(position);
    }

    public TransformComponent(Vector2 size,Vector3 position,int layer){
        this.size.set(size);
        this.position.set(position);
        this.layer = layer;
    }
    public TransformComponent(int layer){

        this.layer = layer;
    }

    public TransformComponent(Vector2 size){
        this.size.set(size);
    }


}
