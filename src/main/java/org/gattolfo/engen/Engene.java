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

    private Engine engine;

    public Engene(){
        engine = new Engine();
    }

    public Engene(@NotNull OrthographicCamera camera, @NotNull SpriteBatch batch){
        engine = new Engine();
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


    public void update(float delta){
        engine.update(delta);
    }
}
