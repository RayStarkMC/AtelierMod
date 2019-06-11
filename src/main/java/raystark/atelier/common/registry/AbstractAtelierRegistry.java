package raystark.atelier.common.registry;

import raystark.atelier.api.registry.IAtelierRegistry;
import raystark.atelier.api.registry.IEffectRegistry;
import raystark.atelier.api.registry.IPotentialAbilityRegistry;

public abstract class AbstractAtelierRegistry<I, B> implements IAtelierRegistry<I, B> {
    protected IEffectRegistry effectRegistry;
    protected IPotentialAbilityRegistry abilityRegistry;

    protected AbstractAtelierRegistry() {
        effectRegistry = new EffectRegistry();
        abilityRegistry = new PotentialAbilityRegistry();
    }

    @Override
    public IEffectRegistry getEffectRegistry() {
        return effectRegistry;
    }

    @Override
    public IPotentialAbilityRegistry getPotentialAbilityRegistry() {
        return abilityRegistry;
    }
}
