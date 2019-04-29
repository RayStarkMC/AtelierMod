package raystark.atelier.common.effect;

import raystark.atelier.api.effect.AbstractEffectMiningLevel;
import raystark.atelier.api.effect.IEffectMiningLevel;

public class EffectIronMiningLevel extends AbstractEffectMiningLevel implements IEffectMiningLevel {

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
