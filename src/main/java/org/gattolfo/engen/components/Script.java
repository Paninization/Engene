package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Entity;

public abstract class Script {

    private Entity entity;

    public Script(Entity entity){
        this.entity = entity;
    }


    public abstract void update(float delta);

}
