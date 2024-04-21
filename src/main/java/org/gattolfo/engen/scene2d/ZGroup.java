package org.gattolfo.engen.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

import java.util.Comparator;

public class ZGroup extends Group implements Ztable{

    int z = 0;
    @Override
    public SnapshotArray<Actor> getChildren() {
        super.getChildren().sort(new ActorComparator());
        return super.getChildren();
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void setZ(int z) {
        this.z = z;
    }

    private static class ActorComparator implements Comparator<Actor> {
        @Override
        public int compare(Actor actor1, Actor actor2) {
            return (actor1 instanceof Ztable && actor2 instanceof Ztable) ? ((Ztable) actor1).getZ() - ((Ztable) actor2).getZ() : actor1.getZIndex() - actor2.getZIndex();
        }
    }
}
