package raystark.atelier.common.alchemy.effect;

import raystark.atelier.api.alchemy.effect.IEffectMiningLevel;

/**
 * IEffectMiningLevelの骨格実装
 */
public abstract class AbstractEffectMiningLevel implements IEffectMiningLevel {

    protected AbstractEffectMiningLevel(){}

    @Override
    public String getNameOnAlchemy() {
        return "Sharpness";
    }
}
