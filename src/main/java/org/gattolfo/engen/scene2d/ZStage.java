package org.gattolfo.engen.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class ZStage extends Stage {
    @Override
    public Array<Actor> getActors() {
        super.getActors().sort(new ZActorComparator());
        return super.getActors();
    }

}
