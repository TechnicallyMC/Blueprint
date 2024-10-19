package com.ibex.blueprint.client.render.easing;

import java.util.HashMap;

public abstract class EasingStyle {
    public static final HashMap<String, EasingStyle> EASINGS = new HashMap<>();
    public final String name;

    public EasingStyle(String name) {
        this.name = name;
        EASINGS.put(name, this);
    }

    public static EasingStyle valueOf(String name) {
        return EASINGS.get(name);
    }

    public abstract float ease(float time, float start, float changed, float duration);

    public static final EasingStyle LINEAR = new EasingStyle("linear") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            return changed * time / duration + start;
        }
    };

    public static final EasingStyle QUAD_IN = new EasingStyle("quadIn") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            return changed * (time /= duration) * time + start;
        }
    };

    public static final EasingStyle QUINT_IN = new EasingStyle("quintIn") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            return changed * (time /= duration) * time * time * time * time + start;
        }
    };

    public static final EasingStyle QUINT_OUT = new EasingStyle("quintOut") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            time = time / duration - 1;
            return changed * (time * time * time * time * time + 1) + start;
        }
    };

    public static final EasingStyle QUINT_IN_OUT = new EasingStyle("quintInOut") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            time /= duration / 2;
            if (time < 1) return changed / 2 * time * time * time * time * time + start;
            time -= 2;
            return changed / 2 * (time * time * time * time * time + 2) + start;
        }
    };

    public static final EasingStyle BOUNCE_OUT = new EasingStyle("bounceOut") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            time /= duration;
            if (time < (1 / 2.75)) {
                return changed * (7.5625f * time * time) + start;
            } else if (time < (2 / 2.75)) {
                time -= (float) (1.5 / 2.75);
                return changed * (7.5625f * (time) * time + 0.75f) + start;
            } else if (time < (2.5 / 2.75)) {
                time -= (float) (2.25 / 2.75);
                return changed * (7.5625f * (time) * time + 0.9375f) + start;
            } else {
                time -= (float) (2.625 / 2.75);
                return changed * (7.5625f * (time) * time + 0.984375f) + start;
            }
        }
    };

    public static final EasingStyle BOUNCE_IN = new EasingStyle("bounceIn") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            return changed - BOUNCE_OUT.ease(duration - time, 0, changed, duration) + start;
        }
    };

    public static final EasingStyle BOUNCE_IN_OUT = new EasingStyle("bounceInOut") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            if (time < duration / 2) {
                return BOUNCE_IN.ease(time * 2, 0, changed, duration) * 0.5f + start;
            } else {
                return BOUNCE_OUT.ease(time * 2 - duration, 0, changed, duration) * 0.5f + changed * 0.5f + start;
            }
        }
    };

    public static final EasingStyle ELASTIC_OUT = new EasingStyle("elasticOut") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            if (time == 0) return start;
            if ((time /= duration) == 1) return start + changed;
            float p = duration * 0.3f;
            float s = p / 4;
            return (changed * (float) Math.pow(2, -10 * time) * (float) Math.sin((time * duration - s) * (2 * Math.PI) / p) + changed + start);
        }
    };

    public static final EasingStyle ELASTIC_IN = new EasingStyle("elasticIn") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            if (time == 0) return start;
            if ((time /= duration) == 1) return start + changed;
            float p = duration * 0.3f;
            float s = p / 4;
            return -(changed * (float) Math.pow(2, 10 * (time -= 1)) * (float) Math.sin((time * duration - s) * (2 * Math.PI) / p)) + start;
        }
    };

    public static final EasingStyle ELASTIC_IN_OUT = new EasingStyle("elasticInOut") {
        @Override
        public float ease(float time, float start, float changed, float duration) {
            if (time == 0) return start;
            if ((time /= duration / 2) == 2) return start + changed;
            float p = duration * (0.3f * 1.5f);
            float s = p / 4;
            if (time < 1) {
                return -0.5f * (changed * (float) Math.pow(2, 10 * (time -= 1)) * (float) Math.sin((time * duration - s) * (2 * Math.PI) / p)) + start;
            }
            return changed * (float) Math.pow(2, -10 * (time -= 1)) * (float) Math.sin((time * duration - s) * (2 * Math.PI) / p) * 0.5f + changed + start;
        }
    };
}
