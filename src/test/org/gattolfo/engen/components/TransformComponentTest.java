package org.gattolfo.engen.components;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import org.gattolfo.engen.component.TransformComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformComponentTest {

    @Test
    void testParentChildWorldPosition() {
        TransformComponent parent = new TransformComponent();
        TransformComponent child = new TransformComponent();

        child.setParent(parent);
        parent.setLocalPosition(new Vector3(10, 0, 0));
        child.setLocalPosition(new Vector3(1, 2, 3));

        Vector3 childWorld = child.getWorldPosition();
        assertEquals(new Vector3(11, 2, 3), childWorld);

        // Ora ruoto il padre di 180° intorno a Z
        parent.setLocalRotation(new Quaternion().setFromAxis(0, 0, 1, 180));
        Vector3 cw2 = child.getWorldPosition();

        // Posizione invertita su X: (10 -1)
        assertEquals(new Vector3(9, -2, 3), cw2);
    }

    @Test
    void testDeepHierarchyTransforms() {
        TransformComponent root = new TransformComponent();
        TransformComponent mid = new TransformComponent();
        TransformComponent leaf = new TransformComponent();

        mid.setParent(root);
        leaf.setParent(mid);

        root.setLocalPosition(new Vector3(5, 0, 0));
        mid.setLocalPosition(new Vector3(0, -5, 0));
        leaf.setLocalPosition(new Vector3(0, 0, 5));

        Vector3 expected = new Vector3(5, -5, 5);
        assertEquals(expected, leaf.getWorldPosition());
    }
}