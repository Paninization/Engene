package org.gattolfo.engen.tools.maptools;

import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TileMapTools {
    public static TiledMap loadInternalTileMap(String internalPath){
        return new TmxMapLoader().load(internalPath);
    }

    public static TiledMap loadExternalTileMap(String externalPath){
        return new TmxMapLoader(new ExternalFileHandleResolver()).load(externalPath);
    }


}
