package com.ibex.blueprint.client.render.screenshake;

import com.ibex.blueprint.mixin.client.CameraAccessor;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;

public class ScreenshakeHandler {
    public static final ArrayList<Screenshake> SCREENSHAKES = new ArrayList<>();
    public static float intensity;
    public static float yawChange;
    public static float pitchChange;

    public static void cameraTick(Camera camera, Random random) {
        if (intensity >= 0.1) {
            yawChange = randomize(random);
            pitchChange = randomize(random);
            ((CameraAccessor) camera).invokeSetRotation(camera.getYaw() + yawChange, camera.getPitch() + pitchChange);
        }
    }

    public static void clientTick(Camera camera, Random random) {
        if (!SCREENSHAKES.isEmpty()) {
            double sum = SCREENSHAKES.stream().mapToDouble(i1 -> i1.update(camera, random)).sum();
            intensity = (float) Math.sqrt(sum);
            SCREENSHAKES.removeIf(Screenshake::isComplete);
        } else {
            intensity = 0;
        }
    }

    public static void addScreenshake(Screenshake instance) {
        SCREENSHAKES.add(instance);
    }

    public static float randomize(Random random) {
        return (random.nextFloat() - 0.5f) * intensity * 2;
    }
}
