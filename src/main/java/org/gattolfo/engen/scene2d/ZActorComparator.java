package org.gattolfo.engen.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Comparator;

public class ZActorComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor actor1, Actor actor2) {
        if(actor1 instanceof Ztable && actor2 instanceof Ztable){
            if(((Ztable) actor1).getZ() < ((Ztable) actor2).getZ())
                return 1;
            else if(((Ztable) actor1).getZ() == ((Ztable) actor2).getZ())
                return 0;
            else
                return -1;
        }
        else
            return actor1.getZIndex() - actor2.getZIndex();
    }
}
