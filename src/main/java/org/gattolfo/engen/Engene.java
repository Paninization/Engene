package org.gattolfo.engen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import org.gattolfo.engen.sistems.AnimationSystem;
import org.gattolfo.engen.sistems.PhysicsSystem;
import org.gattolfo.engen.sistems.RenderingSystem;
import org.jetbrains.annotations.NotNull;


/**
 * Engene class, this is where it all starts
 */
public class Engene {



    @NotNull
    private Engine engine;

    /**
     *  Set the engine that will be used, Engene is not initialized
     * @param engine the engine used
     */
    public Engene(@NotNull Engine engine){
        this.engine = engine;
    }

    /**
     * Create a new Engine for Engene, Engene doesn't initialize
     */
    public Engene(){
        engine = new PooledEngine();
    }

    /**
     * This constructor given a non-null Engine, initializes it for Engene use
     *
     * @param engine Engine ashley, which will be used for entity management, systems etc
     * @param batch SpriteBatch that will be used for rendering
     * @param camera Camera that will be used for the render
     * @param world  The physical world that will be used for entity collisions
     */
    public Engene(@NotNull Engine engine, @NotNull SpriteBatch batch, @NotNull OrthographicCamera camera, @NotNull World world){
        this.engine = engine;
        initializateEngene(batch,camera,world);
    }

    /**
     *  This constructor creates a new Engine and initializes it for Engene use
     *
     * @param batch SpriteBatch that will be used for rendering
     * @param camera Camera that will be used for the render
     * @param world The physical world that will be used for entity collisions
     */
    public Engene(@NotNull SpriteBatch batch,@NotNull OrthographicCamera camera,@NotNull World world){
        engine = new PooledEngine();
        initializateEngene(batch,camera,world);
    }

    /**
     * This method initializes the library's base systems, you don't have to run it
     * @param batch SpriteBatch that will be used for rendering
     * @param camera Camera that will be used for the render
     * @param world The physical world that will be used for entity collisions
     */
    public void initializateEngene(@NotNull SpriteBatch batch, @NotNull OrthographicCamera camera,@NotNull World world) {

        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem(batch,camera));
        engine.addSystem(new PhysicsSystem(world));

    }

    /**
     *
     * @return the Engine used by Engene
     */
    public @NotNull Engine getEngine(){
        return engine;
    }

    /**
     *
     * @param system the system that you want remove
     */
    public  void removeSystem(@NotNull EntitySystem system){
        engine.removeSystem(system);

    }





}
