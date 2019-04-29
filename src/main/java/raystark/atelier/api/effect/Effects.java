package raystark.atelier.api.effect;

import raystark.atelier.common.effect.EffectDiamondMiningLevel;
import raystark.atelier.common.effect.EffectIronMiningLevel;
import raystark.atelier.common.effect.EffectStoneMiningLevel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Effects {
    private static final Effects INSTANCE = new Effects();
    private static final Map<String, IEffect> EFFECTS = new HashMap<>();

    public static final IEffect STONE_MINING_LEVEL = new EffectStoneMiningLevel();
    public static final IEffect IRON_MINING_LEVEL = new EffectIronMiningLevel();
    public static final IEffect DIAMOND_MINING_LEVEL = new EffectDiamondMiningLevel();

    private Effects() {}

    public static void init() {
        registerEffect(STONE_MINING_LEVEL);
        registerEffect(IRON_MINING_LEVEL);
        registerEffect(DIAMOND_MINING_LEVEL);
    }

    public static void registerEffect(IEffect effect) {
        EFFECTS.put(effect.getName(), effect);
    }

    public static IEffect getEffects(String effectName) {
        return EFFECTS.get(effectName);
    }
}
