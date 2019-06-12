package raystark.atelier.common.alchemy.effect;

import raystark.atelier.api.alchemy.effect.IEffect;

public abstract class SimpleEffect implements IEffect {
    private final String prefix;
    private final String strength;

    protected SimpleEffect(String prefix, String strength) {
        this.prefix = prefix;
        this.strength = strength;
    }

    @Override
    public String getName() {
        return prefix + strength;
    }

    @Override
    public String getToolTipText() {
        return prefix + ":" + strength;
    }
}
