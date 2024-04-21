package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.*;

public class AdvancedRenderingSystem extends IteratingSystem {


    private final ComponentMapper<ActorComponent> transformMapper;


    private SpriteBatch batch;
    private OrthographicCamera camera;

    private final Array<Stage> layerRender;

    public AdvancedRenderingSystem(Array<Stage> layerRender,SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(ActorComponent.class, RenderComponent.class).get(), Priority.RENDER);
        transformMapper = ComponentMapper.getFor(ActorComponent.class);
        this.layerRender = layerRender;
        this.batch = batch;
        this.camera = camera;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //layerRender[transformMapper.get(entity).].addEntity(entity);
    }



    private int i;

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        for (Stage stage : layerRender) {
            stage.draw();
        }
    }




}
