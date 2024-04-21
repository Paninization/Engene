package org.gattolfo.engen.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ZActor extends Actor implements Ztable{
    private float z = 0;

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
