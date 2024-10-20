package com.ibex.blueprint.client.render.screenshake;

import com.ibex.blueprint.Constants;
import com.ibex.blueprint.client.render.easing.EasingStyle;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.random.Random;

public class Screenshake {
    public int progress;
    public final int duration;
    public float intensity;

    public Screenshake(int duration) {
        this.duration = duration;
    }

    public Screenshake setIntensity(float intensity) {
        this.intensity = intensity;
        return this;
    }

    public double update(Camera cam, Random rand) {
        progress++;
        return EasingStyle.LINEAR.ease(progress, 0, intensity, duration);
    }

    public boolean isComplete() {
        return this.progress >= this.duration;
    }
}
