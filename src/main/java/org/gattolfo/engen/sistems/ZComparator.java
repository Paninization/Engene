package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import org.gattolfo.engen.components.TransformComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    private ComponentMapper<TransformComponent> cmTrans;

    public ZComparator(){
        cmTrans= ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        float az = cmTrans.get(entityA).position.z;
        float bz = cmTrans.get(entityB).position.z;
        return Float.compare(az,bz);
    }
}
