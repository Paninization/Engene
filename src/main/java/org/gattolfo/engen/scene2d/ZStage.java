package org.gattolfo.engen.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class ZStage extends Stage {
    @Override
    public Array<Actor> getActors() {
        super.getActors().sort(new ActorComparator());
        return super.getActors();
    }

    private static class ActorComparator implements Comparator<Actor> {
        @Override
        public int compare(Actor actor1, Actor actor2) {
            return (actor1 instanceof Ztable && actor2 instanceof Ztable) ? ((Ztable) actor1).getZ() - ((Ztable) actor2).getZ() : actor1.getZIndex() - actor2.getZIndex();
        }
    }
}
