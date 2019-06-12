package raystark.atelier.common.alchemy.effect;

import raystark.atelier.api.alchemy.effect.IEffectInstantHeal;

public class SimpleEffectInstantHeal extends SimpleEffect implements IEffectInstantHeal {

    private static final String PREFIX = "InstantHeal";

    private final int amountHeal;

    protected SimpleEffectInstantHeal(int amountHeal, String strength) {
        super(PREFIX, strength);
        this.amountHeal = amountHeal;
    }

    @Override
    public int getAmountOfHeal() {
        return amountHeal;
    }
}
