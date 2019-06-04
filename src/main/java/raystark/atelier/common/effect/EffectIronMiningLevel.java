package raystark.atelier.common.effect;

import raystark.atelier.api.alchemy.effect.IEffectMiningLevel;

public final class EffectIronMiningLevel extends AbstractEffectMiningLevel implements IEffectMiningLevel {

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public String getName() {
        return "MediumMiningLevel";
    }

    @Override
    public String getToolTipText() {
        return "MiningLevel:Medium";
    }
}
