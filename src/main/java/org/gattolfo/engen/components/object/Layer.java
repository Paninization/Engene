package org.gattolfo.engen.components.object;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.components.RenderComponent;
import org.gattolfo.engen.sistems.ZComparator;


import java.util.Comparator;

public class Layer {

    private final Comparator<Entity> comparator;
    private final Array<Entity> renderQueue = new Array<>();

    public Layer(){
        comparator = new ZComparator();
    }

    public Layer(Comparator<Entity> comparator){
        this.comparator = comparator;
    }
    private final ComponentMapper<RenderComponent> renderMapper = ComponentMapper.getFor(RenderComponent.class);
    public void renderLayer(SpriteBatch batch, OrthographicCamera camera){
        renderQueue.sort(comparator);

        for(Entity e : renderQueue){
            renderMapper.get(e).drawable.draw(batch,camera);
        }

        renderQueue.clear();
    }

    public void addEntity(Entity e){
        renderQueue.add(e);
    }



}
