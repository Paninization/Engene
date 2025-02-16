package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.components.*;

import java.util.*;

public class AdvancedRenderingSystem extends IteratingSystem{


    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<TextureComponent> textureComponent;


    private SpriteBatch batch;
    private OrthographicCamera camera;

    public AdvancedRenderingSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), Priority.RENDER);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        textureComponent = ComponentMapper.getFor(TextureComponent.class);
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        //super.update(deltaTime);
        Entity[] entities = this.getEntities().toArray(Entity.class);
        Arrays.sort(entities, new Zcomparator());
        batch.begin();
        for(Entity entity : entities){
            TransformComponent transformComponent = transformMapper.get(entity);
            TextureComponent textureComponent = this.textureComponent.get(entity);
            batch.draw(
                    textureComponent.getTexture(),
                    transformComponent.getWorldPosition().x,
                    transformComponent.getWorldPosition().y,
                    textureComponent.getTexture().getWidth() * transformComponent.getScale().x,
                    textureComponent.getTexture().getHeight() * transformComponent.getScale().y
            );
        }
        batch.end();
    }



    @Override
    protected void processEntity(Entity entity, float v) {


    }

    class Zcomparator implements Comparator<Entity> {

        @Override
        public int compare(Entity entity, Entity t1) {
            TransformComponent transformComponent1 = transformMapper.get(entity);
            TransformComponent transformComponent2 = transformMapper.get(t1);
            return Float.compare(transformComponent1.getPosition().z,transformComponent2.getPosition().z);
        }
    }

}

