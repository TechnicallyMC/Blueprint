package com.ibex.blueprint.api.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Event<T extends IEvent> {
    private final List<T> listeners = new ArrayList<>();
    private final Function<List<T>, T> invoker;

    public Event(Function<List<T>, T> i) {
        this.invoker = i;
    }

    public void register(T l) {
        this.listeners.add(l);
    }

    public T post() {
        return this.invoker.apply(this.listeners);
    }

    public T pre() {
        for (T listener : this.listeners) {
            listener.preProcess();
        }
        return this.invoker.apply(this.listeners);
    }
}
