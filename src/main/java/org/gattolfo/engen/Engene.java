package org.gattolfo.engen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jetbrains.annotations.NotNull;


/**
 * Engene class, this is where it all starts
 */
public class Engene    {

    @NotNull
    private OrthographicCamera camera;

    @NotNull
    private SpriteBatch batch;

    private Engine engine;

    public Engene(){
        engine = new Engine();
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
    }

    public Engene(@NotNull OrthographicCamera camera, @NotNull SpriteBatch batch){
        engine = new Engine();
        this.camera = camera;
        this.batch = batch;
    }


    public @NotNull OrthographicCamera getCamera() {
        return camera;
    }

    public @NotNull SpriteBatch getBatch() {
        return batch;
    }

    public void addEntity(@NotNull Entity entity){
        engine.addEntity(entity);
    }

    public void removeEntity(@NotNull Entity entity){
        engine.removeEntity(entity);
    }

    public void addSystem(@NotNull EntitySystem system){
        engine.addSystem(system);
    }

    public void removeSystem(@NotNull EntitySystem system){
        engine.removeSystem(system);
    }

}
