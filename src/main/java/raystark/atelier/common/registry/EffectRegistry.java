package raystark.atelier.common.registry;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.registry.IEffectRegistry;
import raystark.atelier.common.alchemy.effect.SimpleEffectInstantHeal;
import raystark.atelier.common.alchemy.effect.SimpleEffectMiningLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EffectRegistry implements IEffectRegistry {
    private boolean hasInit;

    public static final IEffect EFFECT_STONE_MINING = new SimpleEffectMiningLevel(1, "Weak");
    public static final IEffect EFFECT_IRON_MINING = new SimpleEffectMiningLevel(2, "Medium");
    public static final IEffect EFFECT_DIAMOND_MINING = new SimpleEffectMiningLevel(3, "Strong");
    public static final IEffect EFFECT_WEAK_HEAL = new SimpleEffectInstantHeal(1, "Weak");

    public EffectRegistry() {
        effects = new HashMap<>();
        hasInit = false;
    }

    public void init() {
        if(hasInit()) return;

        registerEffect(EFFECT_STONE_MINING);
        registerEffect(EFFECT_IRON_MINING);
        registerEffect(EFFECT_DIAMOND_MINING);
        registerEffect(EFFECT_WEAK_HEAL);

        hasInit = true;
    }

    @Override
    public boolean hasInit() {
        return hasInit;
    }

    private final Map<String, IEffect> effects;

    @Override
    public Optional<IEffect> registerEffect(IEffect effect) {
        return Optional.ofNullable(effects.put(effect.getName(), effect));
    }

    @Override
    public Optional<IEffect> getEffect(String effectName) {
        return Optional.ofNullable(effects.get(effectName));
    }
}
