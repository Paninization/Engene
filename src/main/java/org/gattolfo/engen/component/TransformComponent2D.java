package org.gattolfo.engen.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TransformComponent2D implements Component {
    public final Vector2 localPosition = new Vector2();
    public float localRotation = 0f;
    public final Vector2 localScale = new Vector2(1f, 1f);

    // Globale (calcolato)
    public final Vector2 worldPosition = new Vector2();
    public float worldRotation = 0f;
    public final Vector2 worldScale = new Vector2(1f, 1f);

    // Gerarchia
    public Entity parent = null;
    public final Array<Entity> children = new Array<>();

    public TransformComponent2D setLocalPosition(float x, float y) {
        localPosition.set(x, y);
        return this;
    }

    public TransformComponent2D setLocalRotation(float degrees) {
        localRotation = degrees;
        return this;
    }

    public TransformComponent2D setLocalScale(float x, float y) {
        localScale.set(x, y);
        return this;
    }

    public TransformComponent2D setParent(Entity newParent) {
        if (this.parent != null) {
            TransformComponent2D oldParentTransform = this.parent.getComponent(TransformComponent2D.class);
            if (oldParentTransform != null) {
                oldParentTransform.children.removeValue(thisEntity(), true);
            }
        }
        this.parent = newParent;
        if (newParent != null) {
            TransformComponent2D parentTransform = newParent.getComponent(TransformComponent2D.class);
            if (parentTransform != null) {
                parentTransform.children.add(thisEntity());
            }
        }
        return this;
    }

    // Serve perché Ashley non passa l'entità al componente di default.
    private Entity attachedEntity = null;

    public void attachTo(Entity e) {
        this.attachedEntity = e;
    }

    public Entity thisEntity() {
        return attachedEntity;
    }


}
