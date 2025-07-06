package org.gattolfo.engen.component;

import com.badlogic.ashley.core.Component;
import org.gattolfo.engen.component.render.Renderable;

public class RenderComponent implements Component {
    public Renderable renderable;

    public RenderComponent(Renderable renderable) {
        this.renderable = renderable;
    }
}
