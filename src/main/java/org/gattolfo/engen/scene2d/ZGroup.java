package org.gattolfo.engen.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

import java.util.Comparator;

public class ZGroup extends Group implements Ztable{

    private float z = 0;
    @Override
    public SnapshotArray<Actor> getChildren() {
        super.getChildren().sort(new ZActorComparator());
        return super.getChildren();
    }

    @Override
    public float getZ() {
        return 0;
    }

    @Override
    public void setZ(float z) {
        this.z = z;
    }

}
