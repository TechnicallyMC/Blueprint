package com.ibex.blueprint;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.Identifier;

public class BlueprintLibrary {
    public BlueprintLibrary() {
        CommandRegistrationCallback.EVENT.register(BlueprintLibraryCommands::registerCommands);
        ServerLifecycleEvents.SERVER_STARTED.register(this::serverStarted);
        ServerLifecycleEvents.SERVER_STARTED.register(this::serverStopped);

        ServerPlayConnectionEvents.JOIN.register(this::playerJoined);

        Registry.register(Registries.ITEM, id("debug_item"), new DebugItem(new FabricItemSettings().maxCount(1)));
    }

    private void playerJoined(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
    }

    private void serverStopped(MinecraftServer minecraftServer) {
    }

    private void serverStarted(MinecraftServer minecraftServer) {
    }

    public static Identifier id(String path) {
        return Identifier.of(Constants.MOD_ID, path);
    }
}