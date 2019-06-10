package raystark.atelier.common.alchemy.effect;

import raystark.atelier.api.alchemy.effect.IEffectMiningLevel;

public final class EffectDiamondMiningLevel extends AbstractEffectMiningLevel implements IEffectMiningLevel {

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public String getName() {
        return "StrongMiningLevel";
    }

    @Override
    public String getToolTipText() {
        return "MiningLevel:Strong";
    }
}