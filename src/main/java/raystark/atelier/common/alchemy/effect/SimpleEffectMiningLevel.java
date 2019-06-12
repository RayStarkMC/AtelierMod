package raystark.atelier.common.alchemy.effect;

import raystark.atelier.api.alchemy.effect.IEffectMiningLevel;

public class SimpleEffectMiningLevel extends SimpleEffect implements IEffectMiningLevel {

    private static final String PREFIX = "MiningLevel";

    private final int miningLevel;

    public SimpleEffectMiningLevel(int miningLevel, String strength) {
        super(PREFIX, strength);
        this.miningLevel = miningLevel;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }
}
