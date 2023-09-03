package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {

    public TextureRegion region = null;

    public boolean [] flipped = {false,false};

    public void setFlipped(boolean x, boolean y){
        flipped[0] = x;
        flipped[1] = y;
    }

}
