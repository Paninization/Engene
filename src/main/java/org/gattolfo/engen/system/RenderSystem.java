package org.gattolfo.engen.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.component.RenderComponent;
import org.gattolfo.engen.component.TransformComponent;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private final Array<Entity> sortedEntities = new Array<>();
    private final OrthographicCamera camera;
    private final SpriteBatch batch;

    private final ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);
    private final ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    public RenderSystem(final OrthographicCamera camera, final SpriteBatch batch) {
        super(Priority.RENDER); // puoi aggiungere priorità qui se vuoi
        this.batch = batch;
        this.camera = camera;
    }

    public RenderSystem(final SpriteBatch batch, final OrthographicCamera camera, int priority) {
        super(priority); // puoi aggiungere priorità qui se vuoi
        this.batch = batch;
        this.camera = camera;
    }
    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(RenderComponent.class, TransformComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        //camera.updafte();

        sortedEntities.clear();
        sortedEntities.addAll(entities.toArray(Entity.class));
        sortedEntities.sort((a, b) -> {
            TransformComponent ta = tm.get(a);
            TransformComponent tb = tm.get(b);

            ta.updateWorldTransformIfNeeded();
            tb.updateWorldTransformIfNeeded();

            float za = ta.getWorldPosition().z;
            float zb = tb.getWorldPosition().z;

            return Float.compare(za, zb); // low Z = behind, high Z = front
        });
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (Entity entity : sortedEntities) {
            RenderComponent render = rm.get(entity);
            TransformComponent transform = tm.get(entity);

            transform.updateWorldTransformIfNeeded(); // already done, but safe

            render.renderable.render(batch,transform,deltaTime);
        }

        batch.end();
    }
}
