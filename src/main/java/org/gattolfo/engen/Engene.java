package org.gattolfo.engen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gattolfo.engen.base.Component;
import org.gattolfo.engen.base.Entity;
import org.gattolfo.engen.base.EntityListener;
import org.gattolfo.engen.base.Family;
import org.jetbrains.annotations.NotNull;

import java.util.*;


/**
 * Engene class, this is where it all starts
 */
public class Engene  implements EntityListener {

    @NotNull
    private OrthographicCamera camera;

    @NotNull
    private SpriteBatch batch;

    private List<Entity> entities;
    private Set<Family> families;

    private Map<Family, List<Entity>> familyEntities;

    public Engene(){
        entities = new ArrayList<>();
        families = new HashSet<>();
        familyEntities = new HashMap<>();
    }


    public void addEntity(Entity entity){
        entities.add(entity);
        entity.addListener(this);  // per ricevere notifiche di cambi
        for (Family family : families) {
            if (family.matches(entity)) {
                familyEntities.computeIfAbsent(family, f -> new ArrayList<>()).add(entity);
            }
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        for (List<Entity> list : familyEntities.values()) {
            list.remove(entity);
        }
    }

    public List<Entity> getEntitiesForFamily(Family family) {
        families.add(family);
        return familyEntities.computeIfAbsent(family, f -> {
            List<Entity> matched = new ArrayList<>();
            for (Entity e : entities) {
                if (f.matches(e)) matched.add(e);
            }
            return matched;
        });
    }

    @Override
    public void onComponentAdded(Entity entity, Class<? extends Component> componentClass) {
        for (Family family : families) {
            boolean contains = familyEntities.getOrDefault(family, List.of()).contains(entity);
            boolean matches = family.matches(entity);
            if (!contains && matches) {
                familyEntities.get(family).add(entity);
            } else if (contains && !matches) {
                familyEntities.get(family).remove(entity);
            }
        }
    }

    @Override
    public void onComponentRemoved(Entity entity, Class<? extends Component> componentClass) {
        onComponentAdded(entity, componentClass);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

}
