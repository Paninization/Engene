package org.gattolfo.engen.tools;

import java.util.concurrent.atomic.AtomicInteger;

public class EntityFactory {
    private static final AtomicInteger nextId = new AtomicInteger();

    public static int generateId() {
        return nextId.getAndIncrement();
    }
}
