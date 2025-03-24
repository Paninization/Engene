package org.gattolfo.engen.components;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformComponentTest {

    @Test
    void setLocalPosition() {
        TransformComponent transform = new TransformComponent();
        transform.setLocalPosition(new Vector2(3, 4));

        assertEquals(new Vector2(3, 4), transform.getWorldPosition(), "Local position should match world position for a root entity.");
    }

    @Test
    void setLocalRotation() {
        TransformComponent transform = new TransformComponent();
        transform.setLocalRotation(45);

        assertEquals(45, transform.getWorldRotation(), 0.001, "World rotation should match local rotation for a root entity.");
    }

    @Test
    void setLocalScale() {
        TransformComponent transform = new TransformComponent();
        transform.setLocalScale(new Vector2(2, 3));

        assertEquals(new Vector2(2, 3), transform.getWorldScale(), "World scale should match local scale for a root entity.");
    }

    @Test
    void setParent() {
        TransformComponent parent = new TransformComponent();
        parent.setLocalPosition(new Vector2(5, 5));

        TransformComponent child = new TransformComponent();
        child.setLocalPosition(new Vector2(2, 2));
        child.setParent(parent);

        assertEquals(new Vector2(7, 7), child.getWorldPosition(), "Child world position should be parent position + local position.");
    }

    @Test
    void getWorldPosition() {
        TransformComponent parent = new TransformComponent();
        parent.setLocalPosition(new Vector2(10, 10));

        TransformComponent child = new TransformComponent();
        child.setLocalPosition(new Vector2(3, 0));
        child.setParent(parent);

        assertEquals(new Vector2(13, 10), child.getWorldPosition(), "Child world position should be correctly transformed.");
    }

    @Test
    void getWorldRotation() {
        TransformComponent parent = new TransformComponent();
        parent.setLocalRotation(30);

        TransformComponent child = new TransformComponent();
        child.setLocalRotation(15);
        child.setParent(parent);

        assertEquals(45, child.getWorldRotation(), 0.001, "Child world rotation should be sum of parent and local rotation.");
    }

    @Test
    void getWorldScale() {
        TransformComponent parent = new TransformComponent();
        parent.setLocalScale(new Vector2(2, 2));

        TransformComponent child = new TransformComponent();
        child.setLocalScale(new Vector2(0.5f, 1.5f));
        child.setParent(parent);

        assertEquals(new Vector2(1, 3), child.getWorldScale(), "Child world scale should be parent scale multiplied by local scale.");
    }
}