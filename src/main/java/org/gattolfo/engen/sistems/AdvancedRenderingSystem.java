package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.*;
import org.gattolfo.engen.components.object.Layer;

import java.util.Comparator;

public class AdvancedRenderingSystem extends IteratingSystem {


    private final ComponentMapper<TransformComponent> transformMapper;


    private SpriteBatch batch;
    private OrthographicCamera camera;

    private final Layer[] layerRender;

    public AdvancedRenderingSystem(Layer[] layerRender,SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(TransformComponent.class, RenderComponent.class).get(), Priority.RENDER);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        this.layerRender = layerRender;
        this.batch = batch;
        this.camera = camera;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        layerRender[transformMapper.get(entity).layer].addEntity(entity);
    }



    private int i;

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        camera.update();
        batch.setProjectionMatrix(camera.combined);


        batch.begin();

        for(i=0;i< layerRender.length;i++){
            layerRender[i].renderLayer(batch,camera);
        }

        batch.end();
    }



}
