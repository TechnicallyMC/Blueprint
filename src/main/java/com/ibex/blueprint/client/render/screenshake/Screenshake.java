package com.ibex.blueprint.client.render.screenshake;

import com.ibex.blueprint.client.render.easing.EasingStyle;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.random.Random;

public class Screenshake {
    public int progress;
    public final int duration;
    public float intensity;
    public EasingStyle intensityEasing = EasingStyle.LINEAR, intensityEasingEnd = EasingStyle.LINEAR;

    public Screenshake(int duration) {
        this.duration = duration;
    }

    public Screenshake setIntensity(float intensity) {
        this.intensity = intensity;
        return this;
    }

    public Screenshake setEasingStyle(EasingStyle style) {
        this.intensityEasing = style;
        this.intensityEasingEnd = style;
        return this;
    }

    public double update(Camera cam, Random rand) {
        progress++;
        System.out.println("Screenshake progress: " + progress + ", Intensity: " + intensityEasing.ease(progress, 0, intensity, duration));
        return intensityEasing.ease(progress, 0, intensity, duration);
    }
}
