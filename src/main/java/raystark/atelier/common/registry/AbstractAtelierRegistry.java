package raystark.atelier.common.registry;

import raystark.atelier.api.registry.IAtelierRegistry;
import raystark.atelier.api.registry.IEffectRegistry;
import raystark.atelier.api.registry.IMaterialRegistry;
import raystark.atelier.api.registry.IPotentialAbilityRegistry;

public abstract class AbstractAtelierRegistry<I, B> implements IAtelierRegistry<I, B> {
    private IEffectRegistry effectRegistry;
    private IPotentialAbilityRegistry abilityRegistry;
    private IMaterialRegistry<I, B> materialRegistry;

    protected AbstractAtelierRegistry() {
        effectRegistry = new EffectRegistry();
        abilityRegistry = new PotentialAbilityRegistry();
        materialRegistry = new MaterialRegistry<>();
    }

    @Override
    public IEffectRegistry getEffectRegistry() {
        return effectRegistry;
    }

    @Override
    public IPotentialAbilityRegistry getPotentialAbilityRegistry() {
        return abilityRegistry;
    }

    @Override
    public IMaterialRegistry<I, B> getMaterialRegistry() {
        return materialRegistry;
    }
}
