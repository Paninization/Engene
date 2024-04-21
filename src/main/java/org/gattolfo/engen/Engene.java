package org.gattolfo.engen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.components.*;
import org.gattolfo.engen.sistems.*;
import org.jetbrains.annotations.NotNull;


/**
 * Engene class, this is where it all starts
 */
public class Engene {



    private final int numLayer;



    @NotNull
    private Engine engine;

    @NotNull
    private OrthographicCamera camera;

    @NotNull
    private SpriteBatch batch;

    @NotNull
    private World world;


    /**
     *  Set the engine that will be used, Engene is not initialized
     * @param engine the engine used
     */
    public Engene(@NotNull Engine engine,int numLayer){
        this.engine = engine;
        this.numLayer = numLayer;
    }

    /**
     * Create a new Engine for Engene, Engene doesn't initialize
     */
    public Engene(int numLayer){
        engine = new PooledEngine();
        this.numLayer = numLayer;
    }

    /**
     * This constructor given a non-null Engine, initializes it for Engene use
     *
     * @param engine Engine ashley, which will be used for entity management, systems etc
     * @param batch SpriteBatch that will be used for rendering
     * @param camera Camera that will be used for the render
     * @param world  The physical world that will be used for entity collisions
     */
    public Engene(@NotNull Engine engine, @NotNull SpriteBatch batch, @NotNull OrthographicCamera camera, @NotNull World world,int numLayer){
        this.engine = engine;
        this.camera = camera;
        this.world = world;
        this.batch = batch;
        this.numLayer = numLayer;
        initializateEngene(batch,camera,world);

    }

    /**
     *  This constructor creates a new Engine and initializes it for Engene use
     *
     * @param batch SpriteBatch that will be used for rendering
     * @param camera Camera that will be used for the render
     * @param world The physical world that will be used for entity collisions
     */
    public Engene(@NotNull SpriteBatch batch,@NotNull OrthographicCamera camera,@NotNull World world,int numLayer){
        engine = new PooledEngine();
        this.camera = camera;
        this.batch = batch;
        this.world = world;
        this.numLayer = numLayer;
        initializateEngene(batch,camera,world);
    }

    /**
     * This method initializes the library's base systems, you don't have to run it
     * @param batch SpriteBatch that will be used for rendering
     * @param camera Camera that will be used for the render
     * @param world The physical world that will be used for entity collisions
     */
    public void initializateEngene(@NotNull SpriteBatch batch, @NotNull OrthographicCamera camera,@NotNull World world) {

        this.camera = camera;
        engine.addSystem(new UpdateSystem());
        engine.addSystem(new TrasformPhysicsSystem(world));


    }

    public AdvancedRenderingSystem setUpRenderSystem(SpriteBatch batch,OrthographicCamera camera, Array<Stage> layers){
        AdvancedRenderingSystem ad = new AdvancedRenderingSystem(layers,batch,camera);
        engine.addSystem(ad);
        return ad;
    }
    public AdvancedRenderingSystem setUpRenderSystem( Array<Stage> layers){
        return setUpRenderSystem(batch,camera,layers);
    }

    /**
     *
     * @return the Engine used by Engene
     */
    public @NotNull Engine getEngine(){
        return engine;
    }

    public void update(float delta){
        engine.update(delta);
    }
    /**
     *
     * @param system the system that you want remove
     */
    public  void removeSystem(@NotNull EntitySystem system){
        engine.removeSystem(system);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public World getWorld() {
        return world;
    }

    public Entity createReadyEntity(Body body, Vector2 size, TextureRegion texture){
        Entity e = new Entity();
        e.add(new B2dBodyComponent(body));
        return e;
    }

}
