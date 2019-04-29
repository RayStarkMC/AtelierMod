package raystark.atelier.api.effect;

public abstract class AbstractEffectMiningLevel extends AbstractEffect implements IEffectMiningLevel {

    protected AbstractEffectMiningLevel(){}

    @Override
    public String getNameOnAlchemy() {
        return "Sharpness";
    }
}
