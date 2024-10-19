package com.ibex.blueprint;

import com.ibex.blueprint.client.render.screenshake.Screenshake;
import com.ibex.blueprint.client.render.screenshake.ScreenshakeHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.logging.Level;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getActiveItem();

        ScreenshakeHandler.addScreenshake(new Screenshake(20).setIntensity(5.0f));

        return TypedActionResult.success(itemStack.copy());
    }
}
