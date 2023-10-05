package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;

public class RenderComponent implements Component {

    public DrawablePattern drawable;

    public RenderComponent(DrawablePattern drawable){
        this.drawable = drawable;
    }

}
