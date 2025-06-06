package org.gattolfo.engen.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Family {

    private Set<Class<? extends Component>> all;
    private Set<Class<? extends Component>> exclude;

    private Family(Set<Class<? extends Component>> all, Set<Class<? extends Component>> exclude) {
        this.all = all;
        this.exclude = exclude;
    }

    public boolean matches(Entity entity) {
        // Controlla che l'entità abbia tutti i componenti richiesti
        for (Class<? extends Component> compClass : all) {
            if (entity.getComponent(compClass) == null) return false;
        }
        // Controlla che l'entità non abbia nessuno dei componenti esclusi
        for (Class<? extends Component> compClass : exclude) {
            if (entity.getComponent(compClass) != null) return false;
        }
        return true;
    }

    // Builder pattern per creare una Family più comodamente
    public static class Builder {
        private Set<Class<? extends Component>> all = new HashSet<>();
        private Set<Class<? extends Component>> exclude = new HashSet<>();

        @SafeVarargs
        public final Builder all(Class<? extends Component>... componentClasses) {
            all.addAll(Arrays.asList(componentClasses));
            return this;
        }

        @SafeVarargs
        public final Builder exclude(Class<? extends Component>... componentClasses) {
            exclude.addAll(Arrays.asList(componentClasses));
            return this;
        }

        public Family build() {
            return new Family(all, exclude);
        }
    }
}
