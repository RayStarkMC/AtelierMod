package raystark.atelier.api.alchemy.effect;

/**
 * 採掘レベルの効果を表すインターフェース
 */
public interface IEffectMiningLevel extends IEffect {
    /**
     * この効果の採掘レベル
     * @return 採掘レベル
     */
    int getMiningLevel();
}
