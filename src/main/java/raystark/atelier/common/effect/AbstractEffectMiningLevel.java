package raystark.atelier.common.effect;

import raystark.atelier.api.effect.IEffectMiningLevel;

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
