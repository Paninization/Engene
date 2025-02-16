package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class TransformComponent implements Component {

    private Vector3 position;
    private Vector2 parentPosition;
    private Vector2 scale;
    private float rotation;

    private ArrayList<Entity> children;

    private boolean hidden = false;

    public TransformComponent(){
        this(new Vector3(), new Vector2(1,1), 0);
    }

    public TransformComponent(Vector3 position, Vector2 scale, float rotation){
        this(new Vector3(),new Vector2(), new Vector2(1,1), 0);
    }

    public TransformComponent(Vector3 position,Vector2 parentPosition, Vector2 scale, float rotation){
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        children = new ArrayList<>();
    }
    public Vector3 getPosition() {
        return position;
    }
    public Vector2 getScale() {
        return scale;
    }
    public float getRotation() {
        return rotation;
    }
    public void setPosition(Vector3 position) {
        this.position = position;
        for(Entity child : children){
            TransformComponent tr = child.getComponent(TransformComponent.class);
            if(tr!=null){
                tr.updateParentPosition(position);
            }
        }
    }
    public void setScale(Vector2 scale) {
        this.scale = scale;
    }
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public boolean isHidden() {
        return hidden;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public Vector2 getWorldPosition() {
        return new Vector2(parentPosition.x + position.x, parentPosition.y+position.y);
    }

    private void updateParentPosition(Vector2 parentPosition) {
        this.parentPosition = parentPosition;
    }

    private void updateParentPosition(Vector3 parentPosition) {
        this.parentPosition.x = parentPosition.x;
        this.parentPosition.y = parentPosition.y;
    }
}
