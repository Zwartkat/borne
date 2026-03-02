package fr.zwartkat.services;

import java.util.*;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    public <T> void subscribe(Class<T> eventType, Consumer<T> listener){
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public <T> void publish(T event){
        List<Consumer<?>> subs = listeners.getOrDefault(event.getClass(), Collections.emptyList());
        for(Consumer<?> listener : subs){
            ((Consumer<T>)listener).accept(event);
        }
    }
}
