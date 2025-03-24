package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class TransformComponent implements Component {

    private Vector2 localPosition;
    private float localRotation; // Degrees
    private Vector2 localScale;

    private Vector2 worldPosition;
    private float worldRotation;
    private Vector2 worldScale;

    private TransformComponent parent;
    private List<TransformComponent> children;
    private List<Entity> childrenEntities;
    private Entity parentEntity;

    public TransformComponent() {
        this.localPosition = new Vector2(0, 0);
        this.localRotation = 0;
        this.localScale = new Vector2(1, 1);

        this.worldPosition = new Vector2(0, 0);
        this.worldRotation = 0;
        this.worldScale = new Vector2(1, 1);

        this.children = new ArrayList<>();
        this.childrenEntities = new ArrayList<>();
    }

    public void setLocalPosition(Vector2 position) {
        this.localPosition.set(position);
        updateTransform();
    }

    public void setLocalRotation(float rotation) {
        this.localRotation = rotation;
        updateTransform();
    }

    public void setLocalScale(Vector2 scale) {
        this.localScale.set(scale);
        updateTransform();
    }

    public void setParent(Entity parent) {
        if (!setParent(ComponentMapper.getFor(TransformComponent.class).get(parent))){
            return;
        }
        parentEntity = parent;
        updateTransform();
    }

    private boolean setParent(TransformComponent newParent) {
        if(newParent == null){
            return false;
        }

        if (this.parent != null) {
            this.parent.children.remove(this);
        }

        this.parent = newParent;
        newParent.children.add(this);

        return true;
    }

    private void updateTransform() {
        if (parent != null) {
            // Apply parent's transformations
            float radians = (float) Math.toRadians(parent.worldRotation);
            Vector2 rotatedPosition = localPosition.cpy().rotateRad(radians); // LibGDX rotates in radians
            worldPosition.set(parent.worldPosition).add(rotatedPosition);
            worldRotation = parent.worldRotation + localRotation;
            worldScale.set(parent.worldScale).scl(localScale);
        } else {
            // Root entity
            worldPosition.set(localPosition);
            worldRotation = localRotation;
            worldScale.set(localScale);
        }

        // Update all children recursively
        for (TransformComponent child : children) {
            child.updateTransform();
        }
    }

    public Vector2 getWorldPosition() {
        return worldPosition.cpy(); // Return a copy to avoid modification outside
    }

    public float getWorldRotation() {
        return worldRotation;
    }

    public Vector2 getWorldScale() {
        return worldScale.cpy();
    }

}
