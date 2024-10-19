package com.ibex.blueprint.mixin.client;


import com.ibex.blueprint.Constants;
import com.ibex.blueprint.client.render.screenshake.ScreenshakeHandler;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.text.html.parser.Entity;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void blueprintScreenshake(CallbackInfo ci) {
        ScreenshakeHandler.cameraTick((Camera) (Object) this, Constants.RANDOM);
    }
}
