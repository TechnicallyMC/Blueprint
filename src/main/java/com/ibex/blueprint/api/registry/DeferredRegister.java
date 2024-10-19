package com.ibex.blueprint.api.registry;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DeferredRegister<T> {
    private final String modId;
    private final Registry<T> registry;
    private final List<DeferredSupplier<T>> suppliers = new ArrayList<>();

    public DeferredRegister(String modId, Registry<T> registry) {
        this.modId = modId;
        this.registry = registry;
    }

    public DeferredSupplier<T> register(String name, Supplier<T> supplier) {
        Identifier id = new Identifier(modId, name);
        DeferredSupplier<T> deferredSupplier = new DeferredSupplier<>(id, supplier);
        suppliers.add(deferredSupplier);
        return deferredSupplier;
    }

    public void register() {
        for (DeferredSupplier<T> deferredSupplier : suppliers) {
            T object = deferredSupplier.get();
            Registry.register(registry, deferredSupplier.getId(), object);
        }
    }

    public static <T> DeferredRegister<T> create(String modId, Registry<T> registry) {
        return new DeferredRegister<>(modId, registry);
    }
}
