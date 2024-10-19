package com.ibex.blueprint.client;

import net.fabricmc.api.ClientModInitializer;

public class BlueprintClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        new BlueprintClientLibrary();
    }
}
