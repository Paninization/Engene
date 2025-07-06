package org.gattolfo.engen.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class TransformComponent implements Component {

    // === Local Transform ===
    private final Vector3 localPosition = new Vector3();
    private final Quaternion localRotation = new Quaternion();
    private final Vector3 localScale = new Vector3(1, 1, 1);

    // === World Transform (calcolato) ===
    private final Vector3 worldPosition = new Vector3();
    private final Quaternion worldRotation = new Quaternion();
    private final Vector3 worldScale = new Vector3(1, 1, 1); // optional
    private final Matrix4 worldMatrix = new Matrix4();

    // === Gerarchia ===
    private TransformComponent parent = null;
    private final Array<TransformComponent> children = new Array<>();

    // === Stato ===
    private boolean isDirty = true;

    // -------------------------------
    // Accesso alle variabili locali
    // -------------------------------
    public Vector3 getLocalPosition() {
        return localPosition;
    }

    public Quaternion getLocalRotation() {
        return localRotation;
    }

    public Vector3 getLocalScale() {
        return localScale;
    }

    public void setLocalPosition(Vector3 position) {
        this.localPosition.set(position);
        markDirty();
    }

    public void setLocalRotation(Quaternion rotation) {
        this.localRotation.set(rotation);
        markDirty();
    }

    public void setLocalScale(Vector3 scale) {
        this.localScale.set(scale);
        markDirty();
    }

    // -------------------------------
    // Accesso alle variabili world
    // -------------------------------
    public Vector3 getWorldPosition() {
        updateWorldTransformIfNeeded();
        return worldPosition;
    }

    public Quaternion getWorldRotation() {
        updateWorldTransformIfNeeded();
        return worldRotation;
    }

    public Vector3 getWorldScale() {
        updateWorldTransformIfNeeded();
        return worldScale;
    }

    public Matrix4 getWorldMatrix() {
        updateWorldTransformIfNeeded();
        return worldMatrix;
    }

    // -------------------------------
    // Gestione gerarchia
    // -------------------------------
    public void setParent(TransformComponent newParent) {
        if (this.parent != null) {
            this.parent.children.removeValue(this, true);
        }
        this.parent = newParent;
        if (newParent != null) {
            newParent.children.add(this);
        }
        markDirty();
    }

    public TransformComponent getParent() {
        return parent;
    }

    public Array<TransformComponent> getChildren() {
        return children;
    }

    // -------------------------------
    // Dirty flag system
    // -------------------------------
    public void markDirty() {
        if (isDirty) return;
        isDirty = true;
        for (TransformComponent child : children) {
            child.markDirty();
        }
    }

    public void updateWorldTransformIfNeeded() {
        if (!isDirty) return;

        Matrix4 localMatrix = new Matrix4()
                .idt()
                .translate(localPosition)
                .rotate(localRotation)
                .scale(localScale.x, localScale.y, localScale.z);

        if (parent != null) {
            parent.updateWorldTransformIfNeeded();
            worldMatrix.set(parent.worldMatrix).mul(localMatrix);
        } else {
            worldMatrix.set(localMatrix);
        }

        // Estrai i valori
        worldMatrix.getTranslation(worldPosition);
        worldMatrix.getRotation(worldRotation, true);
        // worldScale (lossy) è facoltativo — si può calcolare se vuoi

        // Calcolo approssimato dello scale
        Vector3 lossyX = new Vector3(worldMatrix.val[Matrix4.M00], worldMatrix.val[Matrix4.M01], worldMatrix.val[Matrix4.M02]);
        Vector3 lossyY = new Vector3(worldMatrix.val[Matrix4.M10], worldMatrix.val[Matrix4.M11], worldMatrix.val[Matrix4.M12]);
        Vector3 lossyZ = new Vector3(worldMatrix.val[Matrix4.M20], worldMatrix.val[Matrix4.M21], worldMatrix.val[Matrix4.M22]);

        worldScale.set(lossyX.len(), lossyY.len(), lossyZ.len());
        
        isDirty = false;
    }

    public boolean isDirty() {
        return isDirty;
    }
}

