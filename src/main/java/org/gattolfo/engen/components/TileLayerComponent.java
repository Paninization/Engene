package org.gattolfo.engen.components;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

public class TileLayerComponent extends DrawablePattern{

    TiledMapTileLayer layer;

    OrthogonalTiledMapRenderer renderer;

    public TileLayerComponent(TiledMapTileLayer layer, OrthogonalTiledMapRenderer renderer){
        this.layer = layer;
        this.renderer = renderer;
    }
    @Override
    public void draw(SpriteBatch batch, OrthographicCamera matrix4) {
        renderer.setView(matrix4);
        renderer.getBatch().begin();
        renderer.renderTileLayer(layer);
        renderer.getBatch().end();

    }
}
