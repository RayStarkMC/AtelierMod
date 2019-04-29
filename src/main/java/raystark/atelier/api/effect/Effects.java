package raystark.atelier.api.effect;

import raystark.atelier.common.effect.EffectDiamondMiningLevel;
import raystark.atelier.common.effect.EffectIronMiningLevel;
import raystark.atelier.common.effect.EffectStoneMiningLevel;

import java.util.HashMap;
import java.util.Map;

/**
 * 効果一覧を表すライブラリクラス
 *
 * 効果はMapに保存されており、getEffectsを通じて取得することが出来る。
 */
public final class Effects {
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

    /**
     * ライブラリにエフェクトを登録します。
     *
     * @param effect 登録するエフェクト
     * @return 以前に登録されていたエフェクトがあった場合かつその時に限りtrue
     */
    public static boolean registerEffect(IEffect effect) {
        IEffect lastValue = EFFECTS.put(effect.getName(), effect);
        return lastValue == null;
    }

    /**
     * 引数で渡された文字列を名前に持つエフェクトを返します。
     *
     * @param effectName
     * @return 名前に対応するエフェクト、又は存在しなければnull
     */
    public static IEffect getEffects(String effectName) {
        return EFFECTS.getOrDefault(effectName, null);
    }
}
