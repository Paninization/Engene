package org.gattolfo.engen.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ZActor extends Actor implements Ztable{
    private int z = 0;

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
