package com.ibex.blueprint.api.registry;

import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class DeferredSupplier<T> {
    private final Identifier id;
    private final Supplier<T> supplier;

    public DeferredSupplier(Identifier id, Supplier<T> supplier) {
        this.id = id;
        this.supplier = supplier;
    }

    public Identifier getId() {
        return id;
    }

    public T get() {
        return supplier.get();
    }
}
