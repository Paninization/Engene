package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import org.gattolfo.engen.components.TransformComponent;

import java.util.Comparator;

public class zScomparator implements Comparator<Entity> {

    ComponentMapper<TransformComponent> tranformMapper;

    public zScomparator(){
        tranformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity e1, Entity e2) {
        return -1 * Float.compare(tranformMapper.get(e1).position.z,tranformMapper.get(e2).position.z);
    }
}
