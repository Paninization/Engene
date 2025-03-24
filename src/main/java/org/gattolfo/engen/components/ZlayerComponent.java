package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;

public class ZlayerComponent implements Component {

    private float z;
    public ZlayerComponent() {
        z = 0;
    }
    public ZlayerComponent(final float z) {
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    public void setZ(final float z) {
        this.z = z;
    }
}
