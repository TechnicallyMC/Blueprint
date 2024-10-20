package com.ibex.blueprint.client;

import com.ibex.blueprint.client.render.screenshake.ScreenshakeHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;

import static com.ibex.blueprint.Constants.RANDOM;

public class BlueprintClientLibrary {
    public BlueprintClientLibrary() {
        if (MinecraftClient.getInstance() == null) {
            return;
        }

        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (minecraftClient.world != null) {
                Camera camera = minecraftClient.gameRenderer.getCamera();
                ScreenshakeHandler.clientTick(camera, RANDOM);
            }
        });
    }
}