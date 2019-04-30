package raystark.atelier.api.effect;

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
