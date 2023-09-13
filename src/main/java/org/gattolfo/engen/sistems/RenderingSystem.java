package org.gattolfo.engen.sistems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.components.TextureComponent;
import org.gattolfo.engen.components.TransformComponent;


import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    private SpriteBatch batch;
    private Array<Entity> renderQueue; //permette di renderizzare immagini una sull'altra
    private final Comparator<Entity> comparator = new ZComparator();; //permette di sortare le immagini in base alla loro Z position

    private OrthographicCamera camera;

    //mapper per riferirsi ai componenti delle entità
    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<TransformComponent> transformM;


    public RenderingSystem(SpriteBatch batch,OrthographicCamera camera) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        textureM = ComponentMapper.getFor(TextureComponent.class);
        transformM = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<Entity>();

        this.batch = batch;
        this.camera = camera;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        renderQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(comparator);

        camera.update();

        batch.setProjectionMatrix(camera.combined);


        batch.begin();

        for(Entity entity : renderQueue){
            TextureComponent tex = textureM.get(entity);
            TransformComponent t = transformM.get(entity);

            if (tex.region == null || t.isHidden) {
                continue;
            }

            batch.draw(tex.region,t.position.x - ( t.size.x/2),t.position.y - (t.size.y/2),t.position.x,t.position.y,t.size.x,t.size.y,1.0f,1.0f,t.rotation);

        }

        batch.end();

        renderQueue.clear();

    }


}
