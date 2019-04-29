package raystark.atelier.common.effect;

import raystark.atelier.api.effect.AbstractEffectMiningLevel;
import raystark.atelier.api.effect.IEffectMiningLevel;

public final class EffectStoneMiningLevel extends AbstractEffectMiningLevel implements IEffectMiningLevel {
    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return "WeakMiningLevel";
    }

    @Override
    public String getToolTipText() {
        return "MiningLevel:Weak";
    }
}
